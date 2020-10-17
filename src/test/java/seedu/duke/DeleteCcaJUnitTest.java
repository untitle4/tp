package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;
import seedu.duke.model.event.cca.CcaManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCcaJUnitTest {

    @Test
    void deleteCcaInList() throws CcaEmptyStringException, CcaParamException {
        ArrayList<Event> cca = new ArrayList<>();
        CcaManager ccaManager = new CcaManager(cca);

        ccaManager.addCca("add cca /n basketball "
                + "/s 2020-10-13 1500 /e 2020-10-13 1700");

        String[] userInput = "delete cca 1".trim().split(" ");

        try {
            ccaManager.deleteCca(userInput);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        int actualOutput = ccaManager.getCcaListSize();
        int expectedOutput = 0;

        assertEquals(actualOutput, expectedOutput);
    }
}
