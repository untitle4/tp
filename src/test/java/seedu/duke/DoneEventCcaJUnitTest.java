package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;
import seedu.duke.model.event.cca.EventCcaManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneEventCcaJUnitTest {

    @Test
    void setCcaDone() throws CcaEmptyStringException, CcaParamException {
        ArrayList<Event> cca = new ArrayList<>();
        EventCcaManager eventCcaManager = new EventCcaManager(cca);

        eventCcaManager.add("add cca /n basketball "
                + "/s 2020-10-13 1500 /e 2020-10-13 1700");

        String[] userInput = "done cca 1".trim().split(" ");

        try {
            eventCcaManager.setDone(userInput);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        boolean actualOutputs = cca.get(0).isDone;
        boolean expectedOutputs = true;

        assertEquals(actualOutputs, expectedOutputs);
    }
}
