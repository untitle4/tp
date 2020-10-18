package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;
import seedu.duke.model.event.cca.CcaManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCcaJUnitTest {

    @Test
    void addCcaToList() throws CcaEmptyStringException, CcaParamException {
        ArrayList<Event> cca = new ArrayList<>();
        CcaManager ccaManager = new CcaManager(cca);
        ccaManager.add("add cca /n basketball camp "
                + "/s 2020-10-13 1500 /e 2020-10-13 1500");
        String actualOutputs = cca.get(0).description;
        String expectedOutputs = "basketball camp";
        assertEquals(actualOutputs, expectedOutputs);
    }
}
