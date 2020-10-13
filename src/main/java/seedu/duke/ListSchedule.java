package seedu.duke;

import seedu.duke.exception.EmptyListException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListSchedule {
    private final ArrayList<Event> classes;
    private final ArrayList<Event> ccas;
    private final ArrayList<Event> tests;
    private static final Logger logger = Logger.getLogger("List");

    public ListSchedule(ArrayList<Event> classes, ArrayList<Event> ccas, ArrayList<Event> tests) {
        this.classes = classes;
        this.ccas = ccas;
        this.tests = tests;
    }

    /**
     * Returns an array list of strings of all the classes, ccas and tests.
     *
     * @return Array list of strings to be printed out.
     * @throws EmptyListException If there are no schedule at all.
     */
    public ArrayList<String> getAllEventsPrinted() throws EmptyListException {
        ArrayList<String> printedEvents = new ArrayList<>();
        logger.log(Level.INFO, "starting to convert events instance to strings");

        if (hasNoSchedule()) {
            logger.log(Level.WARNING, "schedule is empty");
            throw new EmptyListException();
        }

        if (haveClasses()) {
            logger.log(Level.INFO, "converting class events");
            printedEvents.add("Classes:");
            printArrayPaddedNumbers(printedEvents, classes);
        }
        if (haveCcas()) {
            logger.log(Level.INFO, "converting CCA events");
            printedEvents.add("CCAs:");
            printArrayPaddedNumbers(printedEvents, ccas);
        }
        if (haveTests()) {
            logger.log(Level.INFO, "converting test events");
            printedEvents.add("Tests:");
            printArrayPaddedNumbers(printedEvents, tests);
        }

        return printedEvents;
    }

    private boolean haveClasses() {
        return classes.size() != 0;
    }

    private boolean haveCcas() {
        return ccas.size() != 0;
    }

    private boolean haveTests() {
        return tests.size() != 0;
    }

    private boolean hasNoSchedule() {
        return (!haveClasses() && !haveCcas() && !haveTests());
    }

    /**
     * Converts event instances into strings representation padded with numbers.
     * PrintedEvents cannot be null.
     * EventArr cannot be empty or null.
     *
     * @param printedEvents Array list of strings to be printed.
     * @param eventArr Array list of event instances to be converted.
     */
    private void printArrayPaddedNumbers(ArrayList<String> printedEvents, ArrayList<Event> eventArr) {
        assert printedEvents != null;
        assert eventArr != null;
        assert eventArr.size() != 0;
        for (int i = 0; i < eventArr.size(); i++) {
            printedEvents.add(i + 1 + ". " + eventArr.get(i));
        }
    }
}
