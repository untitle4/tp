package seedu.duke;

import seedu.duke.common.LogManager;
import seedu.duke.exception.EmptyListException;
import seedu.duke.parser.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListSchedule {
    private final ArrayList<Event> classes;
    private final ArrayList<Event> ccas;
    private final ArrayList<Event> tests;
    private final ArrayList<Event> tuitions;
    private static final String CATEGORY_TUITIONS = "Tuitions: ";
    private static final String CATEGORY_CLASSES = "Classes: ";
    private static final String CATEGORY_TESTS = "Tests: ";
    private static final String CATEGORY_CCAS = "CCAs: ";
    private DateTimeParser dateTimeParser = new DateTimeParser();
    private static final Logger logger = LogManager.getLogger();
    private static final Logger logger = LogManager.getLoggerInstance().getLogger();

    public ListSchedule(ArrayList<Event> classes, ArrayList<Event> ccas,
                        ArrayList<Event> tests, ArrayList<Event> tuitions) {
        this.classes = classes;
        this.ccas = ccas;
        this.tests = tests;
        this.tuitions = tuitions;
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
        if (haveTuitions()) {
            logger.log(Level.INFO, "converting tuition events");
            printedEvents.add("Tuitions:");
            printArrayPaddedNumbers(printedEvents, tuitions);
        }

        return printedEvents;
    }

    public ArrayList<String> getAllEventsPrintedDate(String userInput) throws EmptyListException {
        ArrayList<String> printedEvents = new ArrayList<>();
        ArrayList<String> printedClasses = new ArrayList<>();
        ArrayList<String> printedTests = new ArrayList<>();
        ArrayList<String> printedCcas = new ArrayList<>();
        ArrayList<String> printedTuitions = new ArrayList<>();
        logger.log(Level.INFO, "starting to convert events instance to strings");

        if (hasNoSchedule()) {
            logger.log(Level.WARNING, "schedule is empty");
            throw new EmptyListException();
        }

        if (haveClasses()) {
            logger.log(Level.INFO, "converting class events");
            printArrayPaddedNumbersDate(printedClasses, classes, userInput);
            listNotEmpty(printedClasses, printedEvents, CATEGORY_CLASSES);
        }
        if (haveCcas()) {
            logger.log(Level.INFO, "converting CCA events");
            printArrayPaddedNumbersDate(printedCcas, ccas, userInput);
            listNotEmpty(printedCcas, printedEvents, CATEGORY_CCAS);
        }
        if (haveTests()) {
            logger.log(Level.INFO, "converting test events");
            printArrayPaddedNumbersDate(printedTests, tests, userInput);
            listNotEmpty(printedTests, printedEvents, CATEGORY_TESTS);
        }
        if (haveTuitions()) {
            logger.log(Level.INFO, "converting tuition events");
            printArrayPaddedNumbersDate(printedTuitions, tuitions, userInput);
            listNotEmpty(printedTuitions, printedEvents, CATEGORY_TUITIONS);
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

    private boolean haveTuitions() {
        return tuitions.size() != 0;
    }

    private boolean hasNoSchedule() {
        return (!haveClasses() && !haveCcas() && !haveTests() && !haveTuitions());
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


    private void printArrayPaddedNumbersDate(ArrayList<String> printedEvents, ArrayList<Event> eventArr,
                                             String userInput) {
        assert printedEvents != null;
        assert eventArr != null;
        assert eventArr.size() != 0;
        for (int i = 0; i < eventArr.size(); i++) {
            String[] listDate = eventArr.get(i).getStart().split(" ");
            if (dateTimeParser.isDateEqual(listDate[0], userInput)) {
                printedEvents.add(i + 1 + ". " + eventArr.get(i));
            }
        }
    }

    private void listNotEmpty(ArrayList<String> printedList, ArrayList<String> printedEvents, String categoryName) {
        if (printedList.size() > 0) {
            printedEvents.add(categoryName);
            for (int i = 0; i < printedList.size(); i++) {
                printedEvents.add(printedList.get(i));
            }
        }
    }
}
