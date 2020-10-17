package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;
import seedu.duke.model.event.test.TestManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddTestJUnitTest {

    @Test
    void addTestToList() throws TestEmptyStringException, TestParamException {
        ArrayList<Event> test = new ArrayList<>();
        TestManager testManager = new TestManager(test);
        testManager.addTest("add test /n Math test "
                + "/s 2020-09-26 1400 /e 2020-09-26 1500");
        int actualOutputs = testManager.getTestListSize();
        int expectedOutputs = 1;
        assertEquals(actualOutputs, expectedOutputs);
    }
}