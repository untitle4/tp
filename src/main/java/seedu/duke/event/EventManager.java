package seedu.duke.event;

import seedu.duke.*;
import seedu.duke.Class;
import seedu.duke.exception.EmptyListException;
import seedu.duke.parser.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class EventManager {
    private static ClassManager classManager;
    private static TestManager testManager;
    private static CcaManager ccaManager;
    private static TuitionManager tuitionManager;
    private static DateTimeParser dateTimeParser;

    public EventManager(EventParameter eventParameter) {
        classManager = new ClassManager(eventParameter.getClasses());
        testManager = new TestManager(eventParameter.getTests());
        ccaManager = new CcaManager(eventParameter.getCcas());
        tuitionManager = new TuitionManager(eventParameter.getTuitions());
    }

    public ClassManager getClassManager() {
        return classManager;
    }

    public TestManager getTestManager() {
        return testManager;
    }

    public CcaManager getCcaManager() {
        return ccaManager;
    }

    public TuitionManager getTuitionManager() {
        return tuitionManager;
    }

    public void listSchedule() {
        try {
            ListSchedule listSchedule = new ListSchedule(
                classManager.getClasses(), ccaManager.getCcaList(),
                testManager.getTestList(), tuitionManager.getTuitions());
            ArrayList<String> printedEvents = listSchedule.getAllEventsPrinted();
            printArray(printedEvents);
        } catch (EmptyListException e) {
            System.out.println("Schedule is empty. Add some!");
        }
    }

    public void listDate(String[] userInput) {
        try {
            if (userInput[2].contains("today")) {
                listDateInputDate(LocalDate.now().toString());
            } else {
                listDateInputDate(userInput[2]);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please enter valid date and time in format yyyy-mm-dd HHMM or today!");
        }
    }

    public void listDateInputDate(String userInput) {
        try {
            ListSchedule listSchedule = new ListSchedule(
                    classManager.getClasses(), ccaManager.getCcaList(),
                    testManager.getTestList(), tuitionManager.getTuitions());
            ArrayList<String> printedEvents = listSchedule.getAllEventsPrintedDate(userInput);
            if (printedEvents.size() > 0) {
                printArray(printedEvents);
            } else {
                System.out.println("No events are due.");
            }
        } catch (EmptyListException e) {
            System.out.println("Schedule is empty. Add some!");
        } catch (DateTimeParseException e) {
            System.out.println("☹ OOPS!!! Please enter valid date and time in format yyyy-mm-dd HHMM or today!");
        }
    }

    private static void printArray(ArrayList<String> printedEvents) {
        assert printedEvents != null;
        for (String line : printedEvents) {
            System.out.println(line);
        }
    }

}
