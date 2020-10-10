package seedu.duke;

import java.util.ArrayList;

public class ListSchedule {
    private final ArrayList<Event> classes;
    private final ArrayList<Event> ccas;
    private final ArrayList<Event> tests;

    public ListSchedule(ArrayList<Event> classes, ArrayList<Event> ccas, ArrayList<Event> tests) {
        this.classes = classes;
        this.ccas = ccas;
        this.tests = tests;
    }

    public void printAllEvents() {
        System.out.println("Class:");
        printArrayPaddedNumbers(classes);
        System.out.println("CCA:");
        printArrayPaddedNumbers(ccas);
        System.out.println("Test:");
        printArrayPaddedNumbers(tests);
    }

    private void printArrayPaddedNumbers(ArrayList<Event> eventArr) {
        for (int i = 0; i < eventArr.size(); i++) {
            System.out.println(i + 1 + ". " + eventArr.get(i));
        }
    }
}
