package seedu.duke.model.event.test;

import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.event.Event;
import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.model.event.EventDataManager;
import seedu.duke.model.event.classlesson.EventClassManager;
import seedu.duke.ui.UserInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventTestManager extends EventDataManager {
    private final ArrayList<Event> tests;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();
    private final UserInterface userInterface;

    public EventTestManager(ArrayList<Event> inputList) {
        tests = inputList;
        userInterface = UserInterface.getInstance();
    }

    public ArrayList<Event> getTests() {
        return tests;
    }

    /**
     * <h2>getTestListSize()</h2>
     * Attains the size of the user's tests ArrayList.
     * @return int - tests ArrayList size
     */
    public int getTestListSize() {
        assert tests != null;
        return tests.size();
    }

    /**
     * <h2>addTest()</h2>
     * Adds new test to tests ArrayList.
     * @param userInput To take in the String consisting of the test name, start date-time and end date-time.
     * @exception MissingParameterException exception thrown when parameter not entered
     * @exception EmptyParameterException exception thrown when description is empty
     * @see EventTestManager#getTaskStatement()
     */
    @Override
    public void add(String userInput) throws EmptyParameterException, MissingParameterException {
        logger.log(Level.INFO, "initialising adding of a test");

        if ((!userInput.contains("/n")) || (!userInput.contains("/s"))
                || (!userInput.contains("/e"))) {
            logger.log(Level.WARNING, "no param is entered");
            throw new MissingParameterException();
        }

        userInput.replaceAll("\\s+","");
        final String[] testDetails = userInput.trim().split("\\/");

        logger.log(Level.INFO, "splitting user input into description, start date and end date");
        String testDescription = testDetails[1].substring(1).trim().replaceAll("\\s+"," ");
        String testStartDate = testDetails[2].substring(1).trim();
        String testEndDate = testDetails[3].substring(1).trim();

        if (testDescription.equals("") || testStartDate.equals("")
                || testEndDate.equals("")) {
            logger.log(Level.WARNING, "description/start date/end date is empty");
            throw new EmptyParameterException();
        }

        try {
            DateTimeParser dateTimeParser = new DateTimeParser();
            Calendar startCalendar = dateTimeParser.convertStringToCalendar(testStartDate);
            Calendar endCalendar = dateTimeParser.convertStringToCalendar(testEndDate);
            tests.add(new EventTest(testDescription, startCalendar, endCalendar));
        } catch (DateTimeParseException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_DATE);
        }

        logger.log(Level.INFO, "added test to ArrayList");

        userInterface.showToUser(Messages.MESSAGE_TEST_ADD_SUCCESS,
                "  " + tests.get(getTestListSize() - 1));
        getTaskStatement();
    }

    /**
     * <h2>deleteTest()</h2>
     * Deletes a test from the tests ArrayList.
     * @param userInputs To take in the test index of the test to be deleted.
     * @exception IndexOutOfBoundsException exception thrown for invalid index
     * @exception NumberFormatException exception thrown for wrong number format
     * @exception ArrayIndexOutOfBoundsException exception thrown for empty description
     * @see EventTestManager#getTaskStatement()
     */
    @Override
    public void delete(String[] userInputs) throws IndexOutOfBoundsException {
        int testNumber = 0;
        logger.log(Level.INFO, "initialising deleting of a test");

        try {
            testNumber = Integer.parseInt(userInputs[2]);

            userInterface.showToUser(Messages.MESSAGE_TEST_DELETE_SUCCESS,
                    "  " + tests.get(testNumber - 1));

            tests.remove(testNumber - 1);
            logger.log(Level.INFO, "deleted test from ArrayList");

            getTaskStatement();
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "wrong number format entered");
            userInterface.showToUser(Messages.MESSAGE_TEST_DELETE_ERROR_NON_NUMBER);
        } catch (ArrayIndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_TEST_DELETE_ERROR_NO_NUMBER_GIVEN);
        } catch (IndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_TEST_INDEX);
        }
    }

    /**
     * <h2>getTaskStatement()</h2>
     * Prints statement to update the user once test has been added or deleted.
     */
    private void getTaskStatement() {
        if ((getTestListSize() - 1 == 0) || (getTestListSize() == 0)) {
            userInterface.showToUser("Now you have " + getTestListSize() + " test in the list.");
        } else {
            userInterface.showToUser("Now you have " + getTestListSize() + " tests in the list.");
        }
    }

    /**
     * <h2>setTestDone()</h2>
     * Sets test as done.
     * @param userInputs To take in the test index of the test to be set as done.
     * @exception IndexOutOfBoundsException when user input is an invalid test index integer.
     * @exception NumberFormatException exception thrown for wrong number format
     * @exception ArrayIndexOutOfBoundsException exception thrown for empty description
     * @see EventTestManager#getTaskStatement()
     */
    @Override
    public void setDone(String[] userInputs) throws IndexOutOfBoundsException {
        int testNumber = 0;
        logger.log(Level.INFO, "initialising setting test as done");

        try {
            testNumber = Integer.parseInt(userInputs[2]);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "wrong number format entered");
            userInterface.showToUser(Messages.MESSAGE_TEST_DONE_ERROR_NON_NUMBER);
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_TEST_DONE_ERROR_NO_NUMBER_GIVEN);
            return;
        }

        if ((testNumber <= 0) || (testNumber > getTestListSize())) {
            logger.log(Level.WARNING, "index entered is out of bounds");
            throw new IndexOutOfBoundsException();
        }

        tests.get(testNumber - 1).setDone();
        logger.log(Level.INFO, "set test as done from ArrayList");

        userInterface.showToUser(Messages.MESSAGE_TEST_DONE_SUCCESS,
                "  " + tests.get(testNumber - 1));

        getTaskStatement();
    }
}