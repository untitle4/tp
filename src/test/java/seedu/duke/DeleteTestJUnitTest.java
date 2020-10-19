package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.test.EventTestManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTestJUnitTest {

    @Test
    void deleteTestFromList() throws TestParamException, TestEmptyStringException {
        ArrayList<Event> test = new ArrayList<>();
        EventTestManager eventTestManager = new EventTestManager(test);

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