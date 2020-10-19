package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidClassInputException;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.classlesson.EventClassManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteEventClassJUnitTest {

    @Test
    void addClassToList() throws InvalidClassInputException {
        ArrayList<Event> classes = new ArrayList<>();
        EventClassManager eventClassManager = new EventClassManager(classes);

        eventClassManager.add("add class /n English lesson "
                + "/s 2020-09-26 1400 /e 2020-09-26 1500");
        eventClassManager.add("add class /n Science lesson "
                + "/s 2020-09-23 1600 /e 2020-09-23 1800");

        String [] userInput = "delete class 1".trim().split(" ");

        try {
            eventClassManager.delete(userInput);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        int actualOutputs = eventClassManager.getClassListSize();
        int expectedOutputs = 1;

        assertEquals(actualOutputs, expectedOutputs);
    }
}