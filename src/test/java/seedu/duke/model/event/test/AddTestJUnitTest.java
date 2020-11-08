package seedu.duke.model.event.test;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.SwappedParameterException;
import seedu.duke.storage.TestUtils;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.event.Event;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddTestJUnitTest {
    TestUtils testUtils = new TestUtils();

    @Test
    void addTestToList() throws MissingParameterException, EmptyParameterException, SwappedParameterException {
        ArrayList<Event> test = new ArrayList<>();
        EventTestManager eventTestManager = new EventTestManager(test,
                testUtils.getEmptyEventManager());
        eventTestManager.add("add test /n Math test "
                + "/s 2020-09-26 1400 /e 2020-09-26 1500");
        int actualOutputs = eventTestManager.getTestListSize();
        int expectedOutputs = 1;
        assertEquals(actualOutputs, expectedOutputs);
    }
}