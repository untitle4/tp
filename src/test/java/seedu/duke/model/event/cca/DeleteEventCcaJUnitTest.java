package seedu.duke.model.event.cca;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.SwappedParameterException;
import seedu.duke.storage.TestUtils;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.event.Event;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteEventCcaJUnitTest {
    TestUtils testUtils = new TestUtils();

    @Test
    void deleteCcaInList() throws EmptyParameterException, MissingParameterException, SwappedParameterException {
        ArrayList<Event> cca = new ArrayList<>();
        EventCcaManager eventCcaManager = new EventCcaManager(cca,
                testUtils.getEmptyEventManager());

        eventCcaManager.add("add cca /n basketball "
                + "/s 2020-10-13 1500 /e 2020-10-13 1700");

        String[] userInput = "delete cca 1".trim().split(" ");

        try {
            eventCcaManager.delete(userInput);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        int actualOutput = eventCcaManager.getCcaListSize();
        int expectedOutput = 0;

        assertEquals(actualOutput, expectedOutput);
    }
}
