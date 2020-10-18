package seedu.duke.model.event;

import seedu.duke.model.event.cca.CcaManager;
import seedu.duke.model.event.classlesson.ClassManager;
import seedu.duke.ListSchedule;
import seedu.duke.common.Messages;
import seedu.duke.model.event.test.TestManager;
import seedu.duke.model.event.tuition.TuitionManager;
import seedu.duke.ui.UserInterface;
import seedu.duke.exception.EmptyListException;
import seedu.duke.controller.parser.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class EventManager {
    private static ClassManager classManager;
    private static TestManager testManager;
    private static CcaManager ccaManager;
    private static TuitionManager tuitionManager;
    private UserInterface userInterface;

    public EventManager(EventParameter eventParameter) {
        classManager = new ClassManager(eventParameter.getClasses());
        testManager = new TestManager(eventParameter.getTests());
        ccaManager = new CcaManager(eventParameter.getCcas());
        tuitionManager = new TuitionManager(eventParameter.getTuitions());
        userInterface = UserInterface.getInstance();
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
            userInterface.printArray(printedEvents);
        } catch (EmptyListException e) {
            userInterface.showToUser(Messages.MESSAGE_EMPTY_SCHEDULE_LIST);
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
            userInterface.showToUser(Messages.MESSAGE_EMPTY_SCHEDULE_LIST);
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
