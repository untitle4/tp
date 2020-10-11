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

    public ArrayList<String> getAllEventsPrinted() {
        ArrayList<String> printedEvents = new ArrayList<>();
        printedEvents.add("Classes:");
        printArrayPaddedNumbers(printedEvents, classes);
        printedEvents.add("CCAs:");
        printArrayPaddedNumbers(printedEvents, ccas);
        printedEvents.add("Tests:");
        printArrayPaddedNumbers(printedEvents, tests);

        return printedEvents;
    }

    private void printArrayPaddedNumbers(ArrayList<String> printedEvents, ArrayList<Event> eventArr) {
        for (int i = 0; i < eventArr.size(); i++) {
            printedEvents.add(i + 1 + ". " + eventArr.get(i));
        }
    }
}
