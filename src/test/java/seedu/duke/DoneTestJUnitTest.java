package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoneTestJUnitTest {

    @Test
    void setTestDoneFromList() throws TestParamException, TestEmptyStringException {
        ArrayList<Event> test = new ArrayList<>();
        TestManager testManager = new TestManager(test);

        testManager.addTest("add test /n Math test "
                + "/s 2020-09-26 1400 /e 2020-09-26 1500");
        testManager.addTest("add test /n Science test "
                + "/s 2020-09-23 1600 /e 2020-09-23 1800");

        String [] userInput = "done test 1".trim().split(" ");

        try {
            testManager.setTestDone(userInput);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        boolean actualOutputs = test.get(0).isDone();
        boolean expectedOutputs = true;

        assertEquals(actualOutputs, expectedOutputs);
    }

}