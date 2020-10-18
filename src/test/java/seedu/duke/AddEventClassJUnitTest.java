package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidClassInputException;
import seedu.duke.model.event.classlesson.EventClassManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddEventClassJUnitTest {

    @Test
    void addClassToList() throws InvalidClassInputException {
        ArrayList<Event> classes = new ArrayList<>();
        EventClassManager eventClassManager = new EventClassManager(classes);
        eventClassManager.add("add class /n Math /s 2020-08-19 1300 /e 2020-08-19 1400");
        int actualOutputs = eventClassManager.getClassListSize();
        int expectedOutputs = 1;
        assertEquals(actualOutputs, expectedOutputs);
    }
}

