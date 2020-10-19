package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.cca.EventCcaManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteEventCcaJUnitTest {

    @Test
    void deleteCcaInList() throws CcaEmptyStringException, CcaParamException {
        ArrayList<Event> cca = new ArrayList<>();
        EventCcaManager eventCcaManager = new EventCcaManager(cca);

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
