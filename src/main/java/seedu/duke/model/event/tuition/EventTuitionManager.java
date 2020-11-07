package seedu.duke.model.event.tuition;

import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.InvalidDateException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.event.Event;
import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.model.event.EventDataManager;
import seedu.duke.model.event.EventManager;
import seedu.duke.ui.UserInterface;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author durianpancakes

/**
 * Manager class to manage EventTuition.
 *
 * <p>
 * Contain methods which allow:
 * <ul>
 *     <li>Obtaining EventTuition list size</li>
 *     <li>Adding new EventTuition into ArrayList</li>
 *     <li>Deleting EventTuition from ArrayList</li>
 * </ul>
 * </p>
 */
public class EventTuitionManager extends EventDataManager {
    private final ArrayList<Event> tuitions;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();
    private final UserInterface userInterface;
    private final EventManager eventManager;
    public static final int INDEX_OFFSET = 1;
    public static final int NO_EVENTS = 0;
    public static final int ONE_EVENT = 1;
    public static final int DELETE_INDEX = 1;

    /**
     * Constructs an EventTuitionManager.
     *
     * @param tuitions Contains a list of Event that are classified as EventTuition.
     * @param eventManager EventManager instance.
     */
    public EventTuitionManager(ArrayList<Event> tuitions, EventManager eventManager) {
        this.tuitions = tuitions;
        this.eventManager = eventManager;
        userInterface = UserInterface.getInstance();
    }

    /**
     * Obtain the EventTuition list.
     *
     * @return the list of EventTuition.
     */
    public ArrayList<Event> getTuitions() {
        return tuitions;
    }

    /**
     * Obtain the number of tuition in the list of EventTuition.
     *
     * @return the number of EventTuition in the list.
     */
    public int getTuitionListSize() {
        return tuitions.size();
    }

    /**
     * Method to add an EventTuition into the list of EventTuition.
     *
     * @param userInput The input entered by the user.
     * @throws MissingParameterException when any of the following prefixes are missing in the userInput:
     *     '/n', '/s', '/e', '/l'.
     * @throws EmptyParameterException when any of the following parameters are empty: '/n', '/s', '/e', '/l'.
     */
    @Override
    public void add(String userInput) throws MissingParameterException, EmptyParameterException {
        logger.log(Level.INFO, "Initializing adding of a tuition");
        final String descriptionPrefix = "/n";
        final String startPrefix = "/s";
        final String endPrefix = "/e";
        final String locationPrefix = "/l";

        if ((!userInput.contains(descriptionPrefix)) || (!userInput.contains(startPrefix))
                || (!userInput.contains(endPrefix)) || (!userInput.contains(locationPrefix))) {
            logger.log(Level.WARNING, "either class description, start date-time or end date-time parameter is"
                    + " missing");
            throw new MissingParameterException("'/n', '/s', '/e' and '/l'");
        }

        final int indexOfDescriptionPrefix = userInput.indexOf(descriptionPrefix);
        final int indexOfStartPrefix = userInput.indexOf(startPrefix);
        final int indexOfEndPrefix = userInput.indexOf(endPrefix);
        final int indexOfLocationPrefix = userInput.indexOf(locationPrefix);

        String description = userInput.substring(indexOfDescriptionPrefix, indexOfStartPrefix)
                .replace(descriptionPrefix, "").trim();
        String start = userInput.substring(indexOfStartPrefix, indexOfEndPrefix)
                .replace(startPrefix, "").trim();
        String end = userInput.substring(indexOfEndPrefix, indexOfLocationPrefix)
                .replace(endPrefix, "").trim();
        String location = userInput.substring(indexOfLocationPrefix)
                .replace(locationPrefix, "").trim();

        if (isEmptyString(description) || isEmptyString(start) || isEmptyString(end) || isEmptyString(location)) {
            throw new EmptyParameterException();
        }

        try {
            DateTimeParser dateTimeParser = new DateTimeParser();
            Calendar startCalendar = dateTimeParser.convertStringToCalendar(start);
            Calendar endCalendar = dateTimeParser.convertStringToCalendar(end);
            EventTuition eventTuition = new EventTuition(description, startCalendar,
                    endCalendar, location);

            eventManager.checkValidTimeGiven(eventTuition);

            // Checking if there are any events that clashes
            ArrayList<Event> clashedEvents = eventManager.checkEventClash(eventTuition);

            //If no events clash and the recommended time did not exceed, add tuition
            if (clashedEvents.size() == NO_EVENTS && !eventManager.didTimeExceed(eventTuition)) {
                tuitions.add(eventTuition);
                logger.log(Level.INFO, "Tuition added successfully");

                userInterface.showToUser(Messages.MESSAGE_TUITION_ADD_SUCCESS,
                        eventTuition.toString(),
                        getTuitionStatement(),
                        "Time left for this day: " + eventManager.getTimeLeft(eventTuition));

                sortList();
                logger.log(Level.INFO, "sorted Tuition ArrayList");

            //If events clashed, show the corresponding error message
            } else if (clashedEvents.size() > NO_EVENTS) {
                userInterface.showToUser("The tuition you were trying to add",
                        eventTuition.toString(),
                        "clashes with the following events in your list:");
                for (Event clashedEvent : clashedEvents) {
                    userInterface.showToUser(clashedEvent.toString());
                }
                userInterface.showToUser(Messages.MESSAGE_PROMPT_CHECK_START_END_INPUTS);

            //If the recommended time exceeded, show the corresponding error message
            } else if (eventManager.didTimeExceed(eventTuition)) {
                userInterface.showToUser(Messages.MESSAGE_RECOMMENDED_TIME_EXCEEDED + " Tuition is not added!");
            }
        } catch (DateTimeParseException | ParseException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_DATE);
        } catch (InvalidDateException e) {
            eventManager.processInvalidDateException(e.getErrorType());
        }
    }

    /**
     * Method to delete an EventTuition from the list of EventTuition based on the index given by the user.
     *
     * @param userInputs The input entered by the user.
     */
    @Override
    public void delete(String[] userInputs) {
        try {
            // Tries to convert classIndex user input into an integer
            int tuitionIndex = Integer.parseInt(userInputs[DELETE_INDEX]);

            // Just to test if class index is valid - for exception use only
            tuitions.get(tuitionIndex - INDEX_OFFSET);

            userInterface.showToUser(Messages.MESSAGE_TUITION_DELETE_SUCCESS,
                    tuitions.get(tuitionIndex - INDEX_OFFSET).toString());

            // Deletes class from classes ArrayList
            Event eventTuition = tuitions.get(tuitionIndex - INDEX_OFFSET);
            tuitions.remove(tuitionIndex - INDEX_OFFSET);
            logger.log(Level.INFO, "Deletion of tuition class from ArrayList");
            userInterface.showToUser(getTuitionStatement(),
                    Messages.MESSAGE_TIME_LEFT_HEADER + eventManager.getTimeLeft(eventTuition));
        } catch (ArrayIndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_TUITION_DELETE_ERROR_NO_NUMBER_GIVEN);
        } catch (IndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_TUITION_INDEX);
        } catch (NumberFormatException e) {
            userInterface.showToUser(Messages.MESSAGE_TUITION_DELETE_ERROR_NON_NUMBER);
        }
    }

    /**
     * Method to set an EventTuition to be DONE.
     *
     * @param userInputs The input entered by the user.
     * @throws IndexOutOfBoundsException when user gives an index that is smaller than or equals to 0, or
     *     when index is greater than the number of EventTuition in the list.
     */
    @Deprecated
    @Override
    public void setDone(String[] userInputs) throws IndexOutOfBoundsException {
        int tuitionNumber;
        logger.log(Level.INFO, "Initialising setting tuition as done");

        try {
            // Trying to convert user's input into an integer
            tuitionNumber = Integer.parseInt(userInputs[2]);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Wrong number format entered");
            userInterface.showToUser(Messages.MESSAGE_TUITION_DONE_ERROR_NON_NUMBER);
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_TUITION_DONE_ERROR_NO_NUMBER_GIVEN);
            return;
        }

        // Checking if user's input is a valid class index integer
        if ((tuitionNumber <= 0) || (tuitionNumber > getTuitionListSize())) {
            logger.log(Level.WARNING, "index entered is out of bounds");
            throw new IndexOutOfBoundsException();
        }

        // Sets class as done
        tuitions.get(tuitionNumber - INDEX_OFFSET).setDone();
        logger.log(Level.INFO, "set class as done from ArrayList");

        userInterface.showToUser(Messages.MESSAGE_TUITION_DONE_SUCCESS,
                "  " + tuitions.get(tuitionNumber - INDEX_OFFSET),
                getTuitionStatement());
    }

    private String getTuitionStatement() {
        String tuitionStatement = getTuitionListSize() == ONE_EVENT ? " class" : " classes";

        return "Now you have " + getTuitionListSize() + tuitionStatement + " in the list";
    }

    /**
     * Helper function to check if a string is empty.
     *
     * @param string String to be checked.
     * @return boolean, true if empty, false if not empty.
     */
    private boolean isEmptyString(String string) {
        return string.equals("");
    }

    /**
     * Sorts the list of EventTuition based on the start time of the Event.
     */
    private void sortList() {
        Collections.sort(tuitions);
    }
}
