package seedu.duke;

import java.util.ArrayList;

public class TestManager {
    private ArrayList<Event> test = new ArrayList<>();

    public TestManager(ArrayList<Event> inputList) {
        test = inputList;
    }

    public ArrayList<Event> getTestList() {
        return test;
    }

    private int getTestListSize() {
        return test.size();
    }

    public void addTest(String userInput) {
        final String[] testDetails = userInput.trim().split("\\/");

        String testDescription = testDetails[1].substring(2);
        String testStartDate = testDetails[2].substring(2);
        String testEndDate = testDetails[3].substring(2);

        test.add(new Test(testDescription, testStartDate, testEndDate));

        System.out.println("Got it. I've added this test:");
        System.out.println("  " + test.get(getTestListSize() - 1));
        getTaskStatement();
    }

    public void deleteTest(String[] userInput) {
        int testNumber = Integer.parseInt(userInput[1]);

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + test.get(testNumber - 1));

        test.remove(testNumber - 1);

        getTaskStatement();
    }

    private void getTaskStatement() {
        if ((getTestListSize() - 1 == 0) || (getTestListSize() == 0)) {
            System.out.println("Now you have " + getTestListSize() + " task in the list.");
        } else {
            System.out.println("Now you have " + getTestListSize() + " tasks in the list.");
        }
    }
}