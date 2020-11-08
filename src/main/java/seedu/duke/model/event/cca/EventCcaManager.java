package seedu.duke.model.event.cca;

import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.InvalidDateException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.exception.SwappedParameterException;
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

//@@author untitle4
/**
 * A manager of cca that executes all the commands related to cca.
 */
public class EventCcaManager extends EventDataManager {
    private final ArrayList<Event> ccas;
    private final EventManager eventManager;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();
    private final UserInterface userInterface;

    public EventCcaManager(ArrayList<Event> inputList, EventManager eventManager) {
        ccas = inputList;
        this.eventManager = eventManager;
        userInterface = UserInterface.getInstance();
    }

    public ArrayList<Event> getCcas() {
        return ccas;
    }

    public int getCcaListSize() {
        assert ccas != null;
        return ccas.size();
    }

    /**
     * Add a cca from the user input.
     * Convert the day-time format to system-recognized.
     * @param userInput The input entered by the user.
     * @throws MissingParameterException if symbols of params are missing.
     * @throws EmptyParameterException if no parameters are provided.
     */
    @Override
    public void add(String userInput) throws MissingParameterException, EmptyParameterException,
            SwappedParameterException {
        logger.log(Level.INFO, "initialising adding of a cca");

        if ((!userInput.contains("/n")) || (!userInput.contains("/s"))
                || (!userInput.contains("/e"))) {
            logger.log(Level.WARNING, "no param is entered");
            throw new MissingParameterException("'/n', '/s' and '/e'");
        }

        final String[] ccaDetails = userInput.trim().split("/");

        validateSwappedParameters(ccaDetails);

        logger.log(Level.INFO, "splitting user input into description, start date and end date");
        String ccaDescription = ccaDetails[1].substring(1).trim();
        String ccaStartDate = ccaDetails[2].substring(1).trim();
        String ccaEndDate = ccaDetails[3].substring(1).trim();

        if (ccaDescription.equals("") || ccaStartDate.equals("")
                || ccaEndDate.equals("")) {
            logger.log(Level.WARNING, "description/start date/end date is empty");
            throw new EmptyParameterException();
        }

        try {
            DateTimeParser dateTimeParser = new DateTimeParser();
            Calendar startCalendar = dateTimeParser.convertStringToCalendar(ccaStartDate);
            Calendar endCalendar = dateTimeParser.convertStringToCalendar(ccaEndDate);
            EventCca cca = new EventCca(ccaDescription, startCalendar, endCalendar);

            eventManager.checkValidTimeGiven(cca);

            // Checking if there are any events that clashes
            ArrayList<Event> clashedEvents = eventManager.checkEventClash(cca);

            //If no events clash and the recommended time did not exceed, add cca
            if (clashedEvents.size() == 0 && !eventManager.didTimeExceed(cca)) {
                ccas.add(cca);
                logger.log(Level.INFO, "added cca to ArrayList");

                userInterface.showToUser(Messages.MESSAGE_CCA_ADD_SUCCESS,
                        ccas.get(getCcaListSize() - 1).toString());
                getCcaStatement(cca);

                sortList();
                logger.log(Level.INFO, "sorted CCA ArrayList");

            //If events clashed, show the corresponding error message
            } else if (clashedEvents.size() > 0) {
                userInterface.showToUser("The cca you were trying to add",
                        cca.toString(),
                        "clashes with the following events in your list:");
                for (Event clashedEvent : clashedEvents) {
                    userInterface.showToUser(clashedEvent.toString());
                }
                userInterface.showToUser(Messages.MESSAGE_PROMPT_CHECK_START_END_INPUTS);

            //If the recommended time exceeded, show the corresponding error message
            } else if (eventManager.didTimeExceed(cca)) {
                userInterface.showToUser(Messages.MESSAGE_RECOMMENDED_TIME_EXCEEDED + " CCA is not added!");
            }
        } catch (DateTimeParseException | ParseException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_DATE);
        } catch (InvalidDateException e) {
            eventManager.processInvalidDateException(e.getErrorType());
        }
    }

    /**
     * Deletes a cca with the input index in the event list.
     * @param userInputs The input entered by the user.
     * @throws IndexOutOfBoundsException if the index is out of bound of event list.
     */
    @Override
    public void delete(String[] userInputs) throws IndexOutOfBoundsException {
        int ccaIndex;

        try {
            ccaIndex = Integer.parseInt(userInputs[2]);
            userInterface.showToUser(Messages.MESSAGE_CCA_DELETE_SUCCESS,
                    ccas.get(ccaIndex - 1).toString());
            Event eventCca = ccas.get(ccaIndex - 1);
            ccas.remove(ccaIndex - 1);
            getCcaStatement(eventCca);
        } catch (ArrayIndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_CCA_DELETE_ERROR_NO_NUMBER_GIVEN);
            logger.log(Level.WARNING, "absence of class index for deletion");
        } catch (NumberFormatException e) {
            userInterface.showToUser(Messages.MESSAGE_CCA_DELETE_ERROR_NON_NUMBER);
        } catch (IndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_CCA_INDEX);
        }
    }

    @Override
    public void setDone(String[] userInputs) {
        int ccaIndex;
        logger.log(Level.INFO, "initialising setting cca as done");

        try {
            ccaIndex = Integer.parseInt(userInputs[2]);
        } catch (NumberFormatException e) {
            userInterface.showToUser(Messages.MESSAGE_CCA_DONE_ERROR_NON_NUMBER);
            return;
        } catch (IndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_CCA_DONE_ERROR_NO_NUMBER_GIVEN);
            return;
        }

        if ((ccaIndex <= 0) || (ccaIndex > getCcaListSize())) {
            logger.log(Level.WARNING, "index entered is out of bounds");
            throw new IndexOutOfBoundsException();
        }

        ccas.get(ccaIndex - 1).setDone();
        logger.log(Level.INFO, "set cca as done from Arraylist");

        userInterface.showToUser(Messages.MESSAGE_CCA_DONE_SUCCESS,
                ccas.get(ccaIndex - 1).toString());
    }

    private void getCcaStatement(Event event) {
        String ccaStatement = getCcaListSize() <= 1 ? " cca" : " ccas";
        userInterface.showToUser("Now you have " + getCcaListSize() + ccaStatement + " in the list.");
        userInterface.showToUser(Messages.MESSAGE_TIME_LEFT_HEADER + eventManager.getTimeLeft(event));
    }

    private void sortList() {
        Collections.sort(ccas);
    }
}