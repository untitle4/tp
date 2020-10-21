package seedu.duke.model.event;

import seedu.duke.model.event.cca.EventCcaManager;
import seedu.duke.model.event.classlesson.EventClassManager;
import seedu.duke.common.Messages;
import seedu.duke.model.event.test.EventTestManager;
import seedu.duke.model.event.tuition.EventTuitionManager;
import seedu.duke.ui.UserInterface;
import seedu.duke.exception.EmptyListException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class EventManager {
    private static EventClassManager eventClassManager;
    private static EventTestManager eventTestManager;
    private static EventCcaManager eventCcaManager;
    private static EventTuitionManager eventTuitionManager;
    private final UserInterface userInterface;

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

    public void listSchedule(String userInput) {
        ArrayList<String> printedEvents;
        try {
            String dateParam = userInput.split(" ").length == 2 ? null : userInput.split(" ")[2];
            ListSchedule listSchedule = new ListSchedule(dateParam, eventClassManager.getClasses(),
                    eventCcaManager.getCcas(), eventTestManager.getTests(), eventTuitionManager.getTuitions());

            if (userInput.contains("week")) {
                printedEvents = listSchedule.getPrintableEventsWeek();
            } else {
                printedEvents = listSchedule.getPrintableEvents();
            }
            userInterface.printArray(printedEvents);
        } catch (EmptyListException e) {
            userInterface.showToUser(Messages.MESSAGE_EMPTY_SCHEDULE_LIST);
        } catch (DateTimeParseException e) {
            System.out.println("☹ OOPS!!! Please enter valid date and time in format yyyy-mm-dd or today!");
        }
    }
}
