package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;
import seedu.duke.model.event.cca.CcaManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCcaJUnitTest {

    @Test
    void setCcaDone() throws CcaEmptyStringException, CcaParamException {
        ArrayList<Event> cca = new ArrayList<>();
        CcaManager ccaManager = new CcaManager(cca);

        ccaManager.add("add cca /n basketball "
                + "/s 2020-10-13 1500 /e 2020-10-13 1700");

        String[] userInput = "done cca 1".trim().split(" ");

        try {
            ccaManager.setDone(userInput);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        boolean actualOutputs = cca.get(0).isDone;
        boolean expectedOutputs = true;

        assertEquals(actualOutputs, expectedOutputs);
    }
}
