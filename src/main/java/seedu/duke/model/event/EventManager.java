package seedu.duke.model.event;

import seedu.duke.model.event.cca.EventCcaManager;
import seedu.duke.model.event.classlesson.EventClassManager;
import seedu.duke.ListSchedule;
import seedu.duke.common.Messages;
import seedu.duke.model.event.test.EventTestManager;
import seedu.duke.model.event.tuition.EventTuitionManager;
import seedu.duke.ui.UserInterface;
import seedu.duke.exception.EmptyListException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class EventManager {
    private static EventClassManager eventClassManager;
    private static EventTestManager eventTestManager;
    private static EventCcaManager eventCcaManager;
    private static EventTuitionManager eventTuitionManager;
    private UserInterface userInterface;

    public EventManager(EventParameter eventParameter) {
        eventClassManager = new EventClassManager(eventParameter.getClasses());
        eventTestManager = new EventTestManager(eventParameter.getTests());
        eventCcaManager = new EventCcaManager(eventParameter.getCcas());
        eventTuitionManager = new EventTuitionManager(eventParameter.getTuitions());
        userInterface = UserInterface.getInstance();
    }

    public EventClassManager getClassManager() {
        return eventClassManager;
    }

    public EventTestManager getTestManager() {
        return eventTestManager;
    }

    public EventCcaManager getCcaManager() {
        return eventCcaManager;
    }

    public EventTuitionManager getTuitionManager() {
        return eventTuitionManager;
    }

    public void listSchedule() {
        try {
            ListSchedule listSchedule = new ListSchedule(
                eventClassManager.getClasses(), eventCcaManager.getCcaList(),
                eventTestManager.getTestList(), eventTuitionManager.getTuitions());
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
                    eventClassManager.getClasses(), eventCcaManager.getCcaList(),
                    eventTestManager.getTestList(), eventTuitionManager.getTuitions());
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
