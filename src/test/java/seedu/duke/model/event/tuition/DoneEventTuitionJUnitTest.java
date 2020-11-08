package seedu.duke.model.event.tuition;

import org.junit.jupiter.api.Test;
import seedu.duke.storage.TestUtils;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DoneEventTuitionJUnitTest {
    TestUtils testUtils = new TestUtils();

    @Test
    void setValidTuitionDone() throws ParseException {
        EventTuitionManager eventTuitionManager = new EventTuitionManager(testUtils.getTuitionList(),
                testUtils.getEmptyEventManager());

        String [] userInput = "done tuition 1".trim().split(" ");

        try {
            eventTuitionManager.setDone(userInput);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        boolean actualOutputs = eventTuitionManager.getTuitions().get(0).isDone();
        boolean expectedOutputs = true;

        assertEquals(actualOutputs, expectedOutputs);
    }

    @Test
    void setTuitionDone_invalidIndex_exceptionThrown() throws ParseException {
        EventTuitionManager eventTuitionManager = new EventTuitionManager(testUtils.getTuitionList(),
                testUtils.getEmptyEventManager());

        String [] userInputIndexTooLarge = "done tuition 5".trim().split(" ");
        String [] userInputIndexNegative = "done tuition -1".trim().split(" ");

        assertThrows(IndexOutOfBoundsException.class, () -> eventTuitionManager.setDone(userInputIndexTooLarge));
        assertThrows(IndexOutOfBoundsException.class, () -> eventTuitionManager.setDone(userInputIndexNegative));
    }
}
