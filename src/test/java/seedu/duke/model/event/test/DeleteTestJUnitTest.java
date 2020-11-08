package seedu.duke.model.event.test;

import org.junit.jupiter.api.Test;
import seedu.duke.storage.TestUtils;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.event.Event;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTestJUnitTest {
    TestUtils testUtils = new TestUtils();

    @Test
    void deleteTestFromList() throws MissingParameterException, EmptyParameterException {
        ArrayList<Event> test = new ArrayList<>();
        EventTestManager eventTestManager = new EventTestManager(test,
                testUtils.getEmptyEventManager());

        eventTestManager.add("add test /n Math test "
                + "/s 2020-09-26 1400 /e 2020-09-26 1500");
        eventTestManager.add("add test /n Science test "
                + "/s 2020-09-23 1600 /e 2020-09-23 1800");

        String [] userInput = "delete test 1".trim().split(" ");

        try {
            eventTestManager.delete(userInput);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        int actualOutputs = eventTestManager.getTestListSize();
        int expectedOutputs = 1;

        assertEquals(actualOutputs, expectedOutputs);
    }
}