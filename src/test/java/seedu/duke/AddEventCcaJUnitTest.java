package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.cca.EventCcaManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddEventCcaJUnitTest {
    TestUtils testUtils = new TestUtils();

    @Test
    void addCcaToList() throws EmptyParameterException, MissingParameterException {
        ArrayList<Event> cca = new ArrayList<>();
        EventCcaManager eventCcaManager = new EventCcaManager(cca,
                testUtils.getEmptyEventManager());
        eventCcaManager.add("add cca /n basketball camp "
                + "/s 2020-10-13 1500 /e 2020-10-13 1600");
        String actualOutputs = cca.get(0).getDescription();
        String expectedOutputs = "basketball camp";
        assertEquals(actualOutputs, expectedOutputs);
    }
}
