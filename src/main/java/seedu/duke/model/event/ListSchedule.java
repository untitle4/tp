package seedu.duke.model.event;

import seedu.duke.common.LogManager;
import seedu.duke.exception.EmptyListException;
import seedu.duke.controller.parser.DateTimeParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author AndreWongZH
public class ListSchedule {
    private final ArrayList<Event> classes;
    private final ArrayList<Event> ccas;
    private final ArrayList<Event> tests;
    private final ArrayList<Event> tuitions;

    private static final String CATEGORY_TUITIONS = "Tuitions: ";
    private static final String CATEGORY_CLASSES = "Classes: ";
    private static final String CATEGORY_TESTS = "Tests: ";
    private static final String CATEGORY_CCAS = "CCAs: ";

    private final DateTimeParser dateTimeParser = new DateTimeParser();
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();

    private final String userInput;
    private Calendar inputCalendar;

    public ListSchedule(String userInput, ArrayList<Event> classes, ArrayList<Event> ccas,
                        ArrayList<Event> tests, ArrayList<Event> tuitions) {
        this.userInput = userInput;
        this.classes = classes;
        this.ccas = ccas;
        this.tests = tests;
        this.tuitions = tuitions;
    }

    public ArrayList<String> getPrintableEvents() throws EmptyListException, ParseException {
        logger.log(Level.INFO, "starting to convert events instance to strings");
        ArrayList<String> printedEvents = new ArrayList<>();
        ArrayList<String> printedClasses;
        ArrayList<String> printedTests;
        ArrayList<String> printedCcas;
        ArrayList<String> printedTuitions;

        inputCalendar = checkAndConvertToday();

        if (haveClasses()) {
            logger.log(Level.INFO, "converting class events");
            printedClasses = parseEventWithNumberPad(classes);
            addToMainList(printedClasses, printedEvents, CATEGORY_CLASSES);
        }
        if (haveCcas()) {
            logger.log(Level.INFO, "converting CCA events");
            printedCcas = parseEventWithNumberPad(ccas);
            addToMainList(printedCcas, printedEvents, CATEGORY_CCAS);
        }
        if (haveTests()) {
            logger.log(Level.INFO, "converting test events");
            printedTests = parseEventWithNumberPad(tests);
            addToMainList(printedTests, printedEvents, CATEGORY_TESTS);
        }
        if (haveTuitions()) {
            logger.log(Level.INFO, "converting tuition events");
            printedTuitions = parseEventWithNumberPad(tuitions);
            addToMainList(printedTuitions, printedEvents, CATEGORY_TUITIONS);
        }

        if (hasNoSchedule() || printedEvents.size() == 0) {
            logger.log(Level.WARNING, "schedule is empty");
            throw new EmptyListException();
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

    //@@author Aliciaho
    /**
     * If the user input contains today/week, get the date for today.
     * Else if user input a date, convert the string date to calendar form
     *
     * @return resultCalender Calendar containing the resulting output
    */
    private Calendar checkAndConvertToday() throws ParseException {
        Calendar resultCalendar = null;
        if (userInput != null && (userInput.contains("today") || userInput.contains("week"))) {
            resultCalendar = Calendar.getInstance();
        } else if (userInput != null) {
            resultCalendar = dateTimeParser.convertStringToCalendarByDate(userInput);
        }
        return resultCalendar;
    }

    //@@author Aliciaho
    /**
     * Converts event instances into strings representation padded with numbers.
     * PrintedEvents cannot be null.
     * EventArr cannot be empty or null.
     *
     * @param eventArr Array list of event instances to be converted.
     * @return printedEvents Array list containing the relevant events in correct output format
     */
    private ArrayList<String> parseEventWithNumberPad(ArrayList<Event> eventArr) {
        assert eventArr != null;
        assert eventArr.size() != 0;
        ArrayList<String> printedEvents = new ArrayList<>();

        // todo might have a problem here
        for (int i = 0; i < eventArr.size(); i++) {
            Calendar listDate = eventArr.get(i).getStart();
            if (userInput == null || dateTimeParser.isDateEqual(listDate, inputCalendar)) {
                printedEvents.add(i + 1 + ". " + eventArr.get(i));
            }
        }

        return printedEvents;
    }

    private void addToMainList(ArrayList<String> subEvents, ArrayList<String> printedEvents, String categoryName) {
        if (subEvents.size() > 0) {
            printedEvents.add(categoryName);
            printedEvents.addAll(subEvents);
        }
    }
}
