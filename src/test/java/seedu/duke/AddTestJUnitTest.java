package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddTestJUnitTest {

    @Test
    void addTestToList() {
        ArrayList<Event> test = new ArrayList<>();
        TestManager testManager = new TestManager(test);
        try {
            testManager.addTest("add test /n Math test " +
                    "/s 26-09-2020 1400 /e 26-09-2020 1500");
        } catch (TestEmptyStringException e) {
            e.printStackTrace();
        } catch (TestParamException e) {
            e.printStackTrace();
        }
        int actualOutputs = testManager.getTestListSize() ;
        int expectedOutputs = 1;
        assertEquals(actualOutputs, expectedOutputs);
    }
}