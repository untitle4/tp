package seedu.duke;

import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;
import seedu.duke.parser.DateTimeParser;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CcaManager {
    private final ArrayList<Event> cca;
    private static final Logger logger = Logger.getLogger("CCA");

    public CcaManager(ArrayList<Event> inputList) {
        cca = inputList;
    }

    public ArrayList<Event> getCcaList() {
        return cca;
    }

    public int getCcaListSize() {
        assert cca != null;
        return cca.size();
    }

    public void addCca(String userInput) throws CcaEmptyStringException, CcaParamException {
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
            System.out.println("☹ OOPS!!! Please enter valid date and time in format yyyy-mm-dd HHMM!");
            return;
        }
        logger.log(Level.INFO, "added cca to ArrayList");

        System.out.println("Got it. I've added this cca: ");
        System.out.println(cca.get(getCcaListSize() - 1));
        getCcaStatement();
    }

    public void deleteCca(String[] userInput) throws IndexOutOfBoundsException {
        int ccaIndex = 0;

        try {
            ccaIndex = Integer.parseInt(userInput[2]);
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please indicate in NUMERALS, which cca you'd like to delete!");
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please indicate which cca you'd like to delete!");
            return;
        }

        if ((ccaIndex <= 0) || (ccaIndex > getCcaListSize())) {
            throw new IndexOutOfBoundsException();
        }

        System.out.println("Noted. I've removed this cca: ");
        System.out.println(cca.get(ccaIndex - 1));

        cca.remove(ccaIndex - 1);
        getCcaStatement();
    }

    public void setCcaDone(String[] userInput) {
        int ccaIndex = 0;
        logger.log(Level.INFO, "initialising setting cca as done");

        try {
            ccaIndex = Integer.parseInt(userInput[2]);
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please indicate in NUMERALS, which cca you'd like to delete!");
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please indicate which cca you'd like to delete!");
            return;
        }

        if ((ccaIndex <= 0) || (ccaIndex > getCcaListSize())) {
            logger.log(Level.WARNING, "index entered is out of bounds");
            throw new IndexOutOfBoundsException();
        }

        cca.get(ccaIndex - 1).setDone();
        logger.log(Level.INFO, "set cca as done from Arraylist");

        System.out.println("Nice! I've marked this cca as done:");
        System.out.println(cca.get(ccaIndex - 1));
    }

    private void getCcaStatement() {
        String ccaStatement = getCcaListSize() <= 1 ? " cca" : " ccas";
        System.out.println("Now you have " + getCcaListSize() + ccaStatement + " in the list.");
    }
}