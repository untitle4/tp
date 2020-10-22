package seedu.duke.model.event.cca;

import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.event.Event;
import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.model.event.EventDataManager;
import seedu.duke.ui.UserInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventCcaManager extends EventDataManager {
    private final ArrayList<Event> ccas;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();
    private final UserInterface userInterface;

    public EventCcaManager(ArrayList<Event> inputList) {
        ccas = inputList;
        userInterface = UserInterface.getInstance();
    }

    public ArrayList<Event> getCcas() {
        return ccas;
    }

    public int getCcaListSize() {
        assert ccas != null;
        return ccas.size();
    }

    @Override
    public void add(String userInput) throws MissingParameterException, EmptyParameterException {
        logger.log(Level.INFO, "initialising adding of a cca");

        if ((!userInput.contains("/n")) || (!userInput.contains("/s"))
                || (!userInput.contains("/e"))) {
            logger.log(Level.WARNING, "no param is entered");
            throw new MissingParameterException();
        }

        final String[] ccaDetails = userInput.trim().split("\\/");

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
            LocalDateTime.parse(ccaStartDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime.parse(ccaEndDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            ccas.add(new EventCca(ccaDescription, ccaStartDate, ccaEndDate));
        } catch (DateTimeParseException e) {
            logger.log(Level.WARNING, "date&time is not valid or in wrong format");
            userInterface.showToUser(Messages.MESSAGE_INVALID_DATE);
            return;
        }
        logger.log(Level.INFO, "added cca to ArrayList");


        userInterface.showToUser(Messages.MESSAGE_CCA_ADD_SUCCESS,
                ccas.get(getCcaListSize() - 1).toString());
        getCcaStatement();
    }

    @Override
    public void delete(String[] userInputs) throws IndexOutOfBoundsException {
        int ccaIndex = 0;

        try {
            ccaIndex = Integer.parseInt(userInputs[2]);
        } catch (NumberFormatException e) {
            userInterface.showToUser(Messages.MESSAGE_CCA_DELETE_ERROR_NON_NUMBER);
            return;
        } catch (IndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_CCA_DELETE_ERROR_NO_NUMBER_GIVEN);
            return;
        }

        if ((ccaIndex <= 0) || (ccaIndex > getCcaListSize())) {
            throw new IndexOutOfBoundsException();
        }

        userInterface.showToUser(Messages.MESSAGE_CCA_DELETE_SUCCESS,
                ccas.get(ccaIndex - 1).toString());

        ccas.remove(ccaIndex - 1);
        getCcaStatement();
    }

    @Override
    public void list() {

    }

    @Override
    public void find(String userInput) throws MissingParameterException {

    }

    @Override
    public void setDone(String[] userInputs) {
        int ccaIndex = 0;
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

    private void getCcaStatement() {
        String ccaStatement = getCcaListSize() <= 1 ? " cca" : " ccas";
        userInterface.showToUser("Now you have " + getCcaListSize() + ccaStatement + " in the list.");
    }
}