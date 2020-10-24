package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.test.EventTestManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoneTestJUnitTest {

    //@Test
    void setTestDoneFromList() throws MissingParameterException, EmptyParameterException {
        ArrayList<Event> test = new ArrayList<>();
        EventTestManager eventTestManager = new EventTestManager(test);

        eventTestManager.add("add test /n Math test "
                + "/s 2020-09-26 1400 /e 2020-09-26 1500");
        eventTestManager.add("add test /n Science test "
                + "/s 2020-09-23 1600 /e 2020-09-23 1800");

        String [] userInput = "done test 1".trim().split(" ");

        try {
            eventTestManager.setDone(userInput);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        boolean actualOutputs = test.get(0).isDone();
        boolean expectedOutputs = true;

        assertEquals(actualOutputs, expectedOutputs);
    }

}