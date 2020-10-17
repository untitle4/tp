package seedu.duke;

import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;
import seedu.duke.parser.DateTimeParser;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestManager {
    private final ArrayList<Event> test;
    private static final Logger logger = LogManager.getLogger();

    public TestManager(ArrayList<Event> inputList) {
        test = inputList;
    }

    public ArrayList<Event> getTestList() {
        return test;
    }

    public int getTestListSize() {
        assert test != null;
        return test.size();
    }

    public void addTest(String userInput) throws TestEmptyStringException, TestParamException {
        logger.log(Level.INFO, "initialising adding of a test");

        if ((!userInput.contains("/n")) || (!userInput.contains("/s"))
                || (!userInput.contains("/e"))) {
            logger.log(Level.WARNING, "no param is entered");
            throw new TestParamException();
        }

        //userInput.replaceAll("\\s+","");
        final String[] testDetails = userInput.trim().split("\\/");

        logger.log(Level.INFO, "splitting user input into description, start date and end date");
        String testDescription = testDetails[1].substring(1).trim();
        String testStartDate = testDetails[2].substring(1).trim();
        String testEndDate = testDetails[3].substring(1).trim();

        if (testDescription.equals("") || testStartDate.equals("")
                || testEndDate.equals("")) {
            logger.log(Level.WARNING, "description/start date/end date is empty");
            throw new TestEmptyStringException();
        }

        try {
            String changedTestStartDate = new DateTimeParser(testStartDate).changeDateTime();
            String changedTestEndDate = new DateTimeParser(testEndDate).changeDateTime();

            test.add(new Test(testDescription, changedTestStartDate, changedTestEndDate));
        } catch (DateTimeParseException | StringIndexOutOfBoundsException
                | ArrayIndexOutOfBoundsException | ParseException e) {
            logger.log(Level.WARNING, "date&time is not valid or in wrong format");
            System.out.println("☹ OOPS!!! Please enter valid date and time in format yyyy-mm-dd HHMM!");
            return;
        }
        logger.log(Level.INFO, "added test to ArrayList");

        System.out.println("Got it. I've added this test:");
        System.out.println("  " + test.get(getTestListSize() - 1));
        getTaskStatement();
    }

    public void deleteTest(String[] userInput) throws IndexOutOfBoundsException {
        int testNumber = 0;
        logger.log(Level.INFO, "initialising deleting of a test");

        try {
            testNumber = Integer.parseInt(userInput[2]);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "wrong number format entered");
            System.out.println("☹ OOPS!!! Please indicate in NUMERALS, which test you'd like to delete!");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please indicate which test you'd like to delete!");
            return;
        }

        if ((testNumber <= 0) || (testNumber > getTestListSize())) {
            logger.log(Level.WARNING, "index entered is out of bounds");
            throw new IndexOutOfBoundsException();
        }

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + test.get(testNumber - 1));

        test.remove(testNumber - 1);
        logger.log(Level.INFO, "deleted test from ArrayList");

        getTaskStatement();
    }

    private void getTaskStatement() {
        if ((getTestListSize() - 1 == 0) || (getTestListSize() == 0)) {
            System.out.println("Now you have " + getTestListSize() + " task in the list.");
        } else {
            System.out.println("Now you have " + getTestListSize() + " tasks in the list.");
        }
    }

    public void setTestDone(String[] userInput) throws IndexOutOfBoundsException {
        int testNumber = 0;
        logger.log(Level.INFO, "initialising setting test as done");

        try {
            testNumber = Integer.parseInt(userInput[2]);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "wrong number format entered");
            System.out.println("☹ OOPS!!! Please indicate in NUMERALS, which test you'd like to set as Done!");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please indicate which test you'd like to set as Done!");
            return;
        }

        if ((testNumber <= 0) || (testNumber > getTestListSize())) {
            logger.log(Level.WARNING, "index entered is out of bounds");
            throw new IndexOutOfBoundsException();
        }

        test.get(testNumber - 1).setDone();
        logger.log(Level.INFO, "set test as done from ArrayList");

        System.out.println("Nice! I've marked this test as done:");
        System.out.println("  " + test.get(testNumber - 1));

        getTaskStatement();
    }
}