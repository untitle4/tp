package seedu.duke;

import java.util.ArrayList;

public class ListSchedule {
    private final ArrayList<Event> cca;
    private final ArrayList<Event> test;

    public ListSchedule(ArrayList<Event> cca, ArrayList<Event> test) {
        this.cca = cca;
        this.test = test;
    }

    public void printAllEvents() {
        System.out.println("CCA:");
        printArrayPaddedNumbers(cca);
        System.out.println("Test:");
        printArrayPaddedNumbers(test);
    }

    private void printArrayPaddedNumbers(ArrayList<Event> eventArr) {
        for (int i = 0; i < eventArr.size(); i++) {
            System.out.println(i + 1 + ". " + eventArr.get(i));
        }
    }
}
