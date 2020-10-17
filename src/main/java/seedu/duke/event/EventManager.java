package seedu.duke.event;

import seedu.duke.CcaManager;
import seedu.duke.ClassManager;
import seedu.duke.ListSchedule;
import seedu.duke.Messages;
import seedu.duke.TestManager;
import seedu.duke.TuitionManager;
import seedu.duke.UserInterface;
import seedu.duke.exception.EmptyListException;

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
}
