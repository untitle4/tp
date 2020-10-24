package seedu.duke.model.event;

import seedu.duke.common.LogManager;
import seedu.duke.exception.EmptyListException;
import seedu.duke.controller.parser.DateTimeParser;

import java.time.LocalDate;
import java.util.ArrayList;
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

    private String userInput;

    public ListSchedule(String userInput, ArrayList<Event> classes, ArrayList<Event> ccas,
                        ArrayList<Event> tests, ArrayList<Event> tuitions) {
        this.userInput = userInput;
        this.classes = classes;
        this.ccas = ccas;
        this.tests = tests;
        this.tuitions = tuitions;
    }

    public ArrayList<String> getPrintableEvents() throws EmptyListException {
        logger.log(Level.INFO, "starting to convert events instance to strings");
        ArrayList<String> printedEvents = new ArrayList<>();
        ArrayList<String> printedClasses;
        ArrayList<String> printedTests;
        ArrayList<String> printedCcas;
        ArrayList<String> printedTuitions;

        checkAndConvertToday();

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

    public ArrayList<String> getPrintableEventsWeek() throws EmptyListException {
        logger.log(Level.INFO, "starting to convert events instance to strings");
        ArrayList<Event> masterList = new ArrayList<>();

        checkAndConvertToday();

        if (haveClasses()) {
            logger.log(Level.INFO, "adding class events");
            masterList.addAll(getMasterList(classes));
        }
        if (haveCcas()) {
            logger.log(Level.INFO, "adding CCA events");
            masterList.addAll(getMasterList(ccas));
        }
        if (haveTests()) {
            logger.log(Level.INFO, "adding test events");
            masterList.addAll(getMasterList(tests));
        }
        if (haveTuitions()) {
            logger.log(Level.INFO, "adding tuition events");
            masterList.addAll(getMasterList(tuitions));
        }

        if (hasNoSchedule() || masterList.size() == 0) {
            logger.log(Level.WARNING, "schedule is empty");
            throw new EmptyListException();
        }

        ArrayList<String> printedEvents = getListWeek(masterList);

        return printedEvents;

    }

    private ArrayList<String> getListWeek(ArrayList<Event> masterList) {
        logger.log(Level.WARNING, "initialising list week");
        ArrayList<String> daysOfTheWeek = new ArrayList<>();
        ArrayList<String> dateArrayList = new ArrayList<>();

        logger.log(Level.WARNING, "getting days of the current week");
        daysOfTheWeek = dateTimeParser.getDaysOfWeek();

        logger.log(Level.WARNING, "getting relevant events in masterList");
        for (int i = 0; i < daysOfTheWeek.size(); i++) {
            int counter = 1;

            checkAndConvertDayOfWeek(dateArrayList, i);

            for (int j = 0; j < masterList.size(); j++) {
                String[] listDate = masterList.get(j).getStart().split(" ");
                if (dateTimeParser.isDateEqual(daysOfTheWeek.get(i), listDate[0])) {
                    dateArrayList.add(counter + ". " + masterList.get(j));
                    counter++;
                }
            }

            logger.log(Level.WARNING, "removing unneeded days");
            dateArrayList = checkCounter(counter, dateArrayList);
        }
        return dateArrayList;
    }

    private void checkAndConvertDayOfWeek(ArrayList<String> dateArrayList, int i) {
        switch (i) {
        case 0:
            dateArrayList.add("MONDAY:");
            break;
        case 1:
            dateArrayList.add("TUESDAY:");
            break;
        case 2:
            dateArrayList.add("WEDNESDAY:");
            break;
        case 3:
            dateArrayList.add("THURSDAY:");
            break;
        case 4:
            dateArrayList.add("FRIDAY:");
            break;
        case 5:
            dateArrayList.add("SATURDAY:");
            break;
        case 6:
            dateArrayList.add("SUNDAY:");
            break;
        default:
            break;
        }
    }

    private ArrayList<String> checkCounter(int counter, ArrayList<String> dateArrayList) {
        logger.log(Level.WARNING, "checking if the day is unneeded");
        if (counter == 1) {
            dateArrayList.remove(dateArrayList.size() - 1);
        }
        return dateArrayList;
    }

    
    private ArrayList<Event> getMasterList(ArrayList<Event> eventArr) {
        assert eventArr != null;
        assert eventArr.size() != 0;

        logger.log(Level.WARNING, "adding to masterList");

        return new ArrayList<>(eventArr);
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

    private void checkAndConvertToday() {
        if (userInput != null && (userInput.contains("today") || userInput.contains("week"))) {
            userInput = LocalDate.now().toString();
        }
    }

    /**
     * Converts event instances into strings representation padded with numbers.
     * PrintedEvents cannot be null.
     * EventArr cannot be empty or null.
     *
     * @param eventArr Array list of event instances to be converted.
     */
    private ArrayList<String> parseEventWithNumberPad(ArrayList<Event> eventArr) {
        assert eventArr != null;
        assert eventArr.size() != 0;
        ArrayList<String> printedEvents = new ArrayList<>();

        // todo might have a problem here
        for (int i = 0; i < eventArr.size(); i++) {
            String[] listDate = eventArr.get(i).getStart().split(" ");
            if (userInput == null || dateTimeParser.isDateEqual(listDate[0], userInput)) {
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
