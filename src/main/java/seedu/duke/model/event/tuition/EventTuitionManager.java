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
import seedu.duke.model.event.test.EventTest;
import seedu.duke.ui.UserInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author durianpancakes
public class EventTuitionManager extends EventDataManager {
    private final ArrayList<Event> tuitions;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();
    private final UserInterface userInterface;
    private EventManager eventManager;

    public EventTuitionManager(ArrayList<Event> tuitions, EventManager eventManager) {
        this.tuitions = tuitions;
        this.eventManager = eventManager;
        userInterface = UserInterface.getInstance();
    }

    public ArrayList<Event> getTuitions() {
        return tuitions;
    }

    public int getTuitionListSize() {
        return tuitions.size();
    }

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

            if (clashedEvents.size() == 0) {
                tuitions.add(eventTuition);
                logger.log(Level.INFO, "Tuition added successfully");

                sortList();
                logger.log(Level.INFO, "sorted Tuition ArrayList");

                userInterface.showToUser(Messages.MESSAGE_TUITION_ADD_SUCCESS,
                        eventTuition.toString(),
                        getTuitionStatement());
            } else {
                userInterface.showToUser("The tuition you were trying to add",
                        eventTuition.toString(),
                        "clashes with the following events in your list:");
                for (Event clashedEvent : clashedEvents) {
                    userInterface.showToUser(clashedEvent.toString());
                }
                userInterface.showToUser("Please check the start and end inputs again!");
            }
        } catch (DateTimeParseException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_DATE);
        } catch (InvalidDateException e) {
            eventManager.processInvalidDateException(e.getErrorType());
        } catch (ParseException e) {
            userInterface.showToUser("☹ OOPS!!! Please enter valid date "
                    + "and time in format yyyy-mm-dd!");
        }
    }

    @Override
    public void delete(String[] userInputs) {
        try {
            // Tries to convert classIndex user input into an integer
            int tuitionIndex = Integer.parseInt(userInputs[2]);

            // Just to test if class index is valid - for exception use only
            tuitions.get(tuitionIndex - 1);

            userInterface.showToUser("Noted. I've removed this tuition class: ",
                    tuitions.get(tuitionIndex - 1).toString());

            // Deletes class from classes ArrayList
            tuitions.remove(tuitionIndex - 1);
            logger.log(Level.INFO, "Deletion of tuition class from ArrayList");
            userInterface.showToUser(getTuitionStatement());
        } catch (ArrayIndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_TUITION_DELETE_ERROR_NO_NUMBER_GIVEN);
        } catch (IndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_TUITION_INDEX);
        } catch (NumberFormatException e) {
            userInterface.showToUser(Messages.MESSAGE_TUITION_DELETE_ERROR_NON_NUMBER);
        }
    }

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
        tuitions.get(tuitionNumber - 1).setDone();
        logger.log(Level.INFO, "set class as done from ArrayList");

        userInterface.showToUser(Messages.MESSAGE_TUITION_DONE_SUCCESS,
                "  " + tuitions.get(tuitionNumber - 1),
                getTuitionStatement());
    }

    private String getTuitionStatement() {
        String tuitionStatement = getTuitionListSize() == 1 ? " class" : " classes";

        return "Now you have " + getTuitionListSize() + tuitionStatement + " in the list";
    }

    private boolean isEmptyString(String string) {
        return string.equals("");
    }

    private void sortList() {
        Collections.sort(tuitions);
    }
}
