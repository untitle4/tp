package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidClassInputException;
import seedu.duke.model.event.classlesson.ClassManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddClassJUnitTest {

    @Test
    void addClassToList() throws InvalidClassInputException {
        ArrayList<Event> classes = new ArrayList<>();
        ClassManager classManager = new ClassManager(classes);
        classManager.addClass("add class /n Math /s 2020-08-19 1300 /e 2020-08-19 1400");
        int actualOutputs = classManager.getClassListSize();
        int expectedOutputs = 1;
        assertEquals(actualOutputs, expectedOutputs);
    }
}

