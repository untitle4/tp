package seedu.duke.model.event.cca;

import seedu.duke.Event;
import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;
import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.model.event.EventDataManager;
import seedu.duke.ui.UserInterface;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CcaManager extends EventDataManager {
    private final ArrayList<Event> cca;
    private static final Logger logger = LogManager.getLoggerInstance().getLogger();
    private UserInterface userInterface;

    public CcaManager(ArrayList<Event> inputList) {
        cca = inputList;
        userInterface = UserInterface.getInstance();
    }

    public ArrayList<Event> getCcaList() {
        return cca;
    }

    public int getCcaListSize() {
        assert cca != null;
        return cca.size();
    }

    @Override
    public void add(String userInput) throws CcaEmptyStringException, CcaParamException {
        logger.log(Level.INFO, "initialising adding of a cca");

        if ((!userInput.contains("/n")) || (!userInput.contains("/s"))
                || (!userInput.contains("/e"))) {
            logger.log(Level.WARNING, "no param is entered");
            throw new CcaParamException();
        }

        final String[] ccaDetails = userInput.trim().split("\\/");

        logger.log(Level.INFO, "splitting user input into description, start date and end date");
        String ccaDescription = ccaDetails[1].substring(1).trim();
        String ccaStartDate = ccaDetails[2].substring(1).trim();
        String ccaEndDate = ccaDetails[3].substring(1).trim();

        if (ccaDescription.equals("") || ccaStartDate.equals("")
                || ccaEndDate.equals("")) {
            logger.log(Level.WARNING, "description/start date/end date is empty");
            throw new CcaEmptyStringException();
        }

        try {
            String changedCcaStartDate = new DateTimeParser(ccaStartDate).changeDateTime();
            String changedCcaEndDate = new DateTimeParser(ccaEndDate).changeDateTime();

            cca.add(new Cca(ccaDescription, changedCcaStartDate, changedCcaEndDate));
        } catch (DateTimeParseException | StringIndexOutOfBoundsException
                | ArrayIndexOutOfBoundsException | ParseException e) {
            logger.log(Level.WARNING, "date&time is not valid or in wrong format");
            userInterface.showToUser(Messages.MESSAGE_INVALID_DATE);
            return;
        }
        logger.log(Level.INFO, "added cca to ArrayList");


        userInterface.showToUser(Messages.MESSAGE_CCA_ADD_SUCCESS,
                cca.get(getCcaListSize() - 1).toString());
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
                cca.get(ccaIndex - 1).toString());

        cca.remove(ccaIndex - 1);
        getCcaStatement();
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

        cca.get(ccaIndex - 1).setDone();
        logger.log(Level.INFO, "set cca as done from Arraylist");

        userInterface.showToUser(Messages.MESSAGE_CCA_DONE_SUCCESS,
                cca.get(ccaIndex - 1).toString());
    }

    private void getCcaStatement() {
        String ccaStatement = getCcaListSize() <= 1 ? " cca" : " ccas";
        userInterface.showToUser("Now you have " + getCcaListSize() + ccaStatement + " in the list.");
    }
}