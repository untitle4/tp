package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTestJUnitTest {

    @Test
    void addTestToList() throws TestParamException, TestEmptyStringException {
        ArrayList<Event> test = new ArrayList<>();
        TestManager testManager = new TestManager(test);

        testManager.addTest("add test /n Math test "
                + "/s 26-09-2020 1400 /e 26-09-2020 1500");
        testManager.addTest("add test /n Science test "
                + "/s 23-09-2020 1600 /e 23-09-2020 1800");

        String [] userInput = "delete test 1".trim().split(" ");

        try {
            testManager.deleteTest(userInput);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        int actualOutputs = testManager.getTestListSize();
        int expectedOutputs = 1;

        assertEquals(actualOutputs, expectedOutputs);
    }
}