package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.test.EventTestManager;
import seedu.duke.model.event.tuition.EventTuition;
import seedu.duke.model.event.tuition.EventTuitionManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DoneEventTuitionJUnitTest {
    @Test
    void setValidTuitionDone() {
        EventTuitionManager eventTuitionManager = new EventTuitionManager(getTuitionList());

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
    void setTuitionDone_invalidIndex_exceptionThrown() {
        EventTuitionManager eventTuitionManager = new EventTuitionManager(getTuitionList());

        String [] userInputIndexTooLarge = "done tuition 5".trim().split(" ");
        String [] userInputIndexNegative = "done tuition -1".trim().split(" ");

        assertThrows(IndexOutOfBoundsException.class, () -> eventTuitionManager.setDone(userInputIndexTooLarge));
        assertThrows(IndexOutOfBoundsException.class, () -> eventTuitionManager.setDone(userInputIndexNegative));
    }

    private ArrayList<Event> getTuitionList() {
        ArrayList<Event> tuitions = new ArrayList<>();

        tuitions.add(new EventTuition("math", "2020-09-26 1400",
                "2020-09-26 1500", "home"));
        tuitions.add(new EventTuition("english", "2020-09-27 1400",
                "2020-09-27 1500", "tuition centre"));

        return tuitions;
    }
}
