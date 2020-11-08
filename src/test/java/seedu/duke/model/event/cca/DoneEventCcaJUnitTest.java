package seedu.duke.model.event.cca;

import org.junit.jupiter.api.Test;
import seedu.duke.storage.TestUtils;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.event.Event;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneEventCcaJUnitTest {
    TestUtils testUtils = new TestUtils();

    @Test
    void setCcaDone() throws EmptyParameterException, MissingParameterException {
        ArrayList<Event> cca = new ArrayList<>();
        EventCcaManager eventCcaManager = new EventCcaManager(cca,
                testUtils.getEmptyEventManager());

        eventCcaManager.add("add cca /n basketball "
                + "/s 2020-10-13 1500 /e 2020-10-13 1700");

        String[] userInput = "done cca 1".trim().split(" ");

        try {
            eventCcaManager.setDone(userInput);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        boolean actualOutputs = cca.get(0).isDone();
        boolean expectedOutputs = true;

        assertEquals(actualOutputs, expectedOutputs);
    }
}
