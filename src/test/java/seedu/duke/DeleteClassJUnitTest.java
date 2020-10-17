package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidClassInputException;
import seedu.duke.model.event.classlesson.ClassManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteClassJUnitTest {

    @Test
    void addClassToList() throws InvalidClassInputException {
        ArrayList<Event> classes = new ArrayList<>();
        ClassManager classManager = new ClassManager(classes);

        classManager.addClass("add class /n English lesson "
                + "/s 2020-09-26 1400 /e 2020-09-26 1500");
        classManager.addClass("add class /n Science lesson "
                + "/s 2020-09-23 1600 /e 2020-09-23 1800");

        String [] userInput = "delete class 1".trim().split(" ");

        try {
            classManager.deleteClass(userInput);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        int actualOutputs = classManager.getClassListSize();
        int expectedOutputs = 1;

        assertEquals(actualOutputs, expectedOutputs);
    }
}