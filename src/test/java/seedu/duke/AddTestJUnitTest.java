package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;
import seedu.duke.model.event.test.EventTestManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddTestJUnitTest {

    @Test
    void addTestToList() throws TestEmptyStringException, TestParamException {
        ArrayList<Event> test = new ArrayList<>();
        EventTestManager eventTestManager = new EventTestManager(test);
        eventTestManager.add("add test /n Math test "
                + "/s 2020-09-26 1400 /e 2020-09-26 1500");
        int actualOutputs = eventTestManager.getTestListSize();
        int expectedOutputs = 1;
        assertEquals(actualOutputs, expectedOutputs);
    }
}