package seedu.duke;

import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;

import java.util.ArrayList;

public class TestManager {
    private final ArrayList<Event> test;

    public TestManager(ArrayList<Event> inputList) {
        test = inputList;
    }

    public ArrayList<Event> getTestList() {
        return test;
    }

    public int getTestListSize() {
        return test.size();
    }

    public void addTest(String userInput) throws TestEmptyStringException, TestParamException {

        if ((!userInput.contains("/n")) || (!userInput.contains("/s"))
                || (!userInput.contains("/e"))) {
            throw new TestParamException();
        }

        userInput.replaceAll("\\s+","");
        final String[] testDetails = userInput.trim().split("\\/");

        String testDescription = testDetails[1].substring(1).trim();
        String testStartDate = testDetails[2].substring(1).trim();
        String testEndDate = testDetails[3].substring(1).trim();

        if (testDescription.equals("") || testStartDate.equals("")
                || testEndDate.equals("")) {
            throw new TestEmptyStringException();
        }

        test.add(new Test(testDescription, testStartDate, testEndDate));

        System.out.println("Got it. I've added this test:");
        System.out.println("  " + test.get(getTestListSize() - 1));
        getTaskStatement();
    }

    public void deleteTest(String[] userInput) throws IndexOutOfBoundsException {
        int testNumber = 0;

        try {
            testNumber = Integer.parseInt(userInput[2]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if ((testNumber <= 0) || (testNumber >= getTestListSize())) {
            throw new IndexOutOfBoundsException();
        }

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + test.get(testNumber - 1));

        test.remove(testNumber - 1);

        getTaskStatement();
    }

    public void getTaskStatement() {
        if ((getTestListSize() - 1 == 0) || (getTestListSize() == 0)) {
            System.out.println("Now you have " + getTestListSize() + " task in the list.");
        } else {
            System.out.println("Now you have " + getTestListSize() + " tasks in the list.");
        }
    }
}