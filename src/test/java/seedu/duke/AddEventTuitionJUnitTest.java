package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.tuition.EventTuition;
import seedu.duke.model.event.tuition.EventTuitionManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddEventTuitionJUnitTest {
    @Test
    void addEvent_validTuition_noException() throws EmptyParameterException, MissingParameterException {
        ArrayList<Event> tuitions = new ArrayList<>();
        EventTuitionManager eventTuitionManager = new EventTuitionManager(tuitions);
        eventTuitionManager.add("add tuition /n math tuition "
                + "/s 2020-10-13 1500 /e 2020-10-13 1700 "
                + "/l home");

        EventTuition eventTuition = new EventTuition("math tuition",
                "2020-10-13 1500",
                "2020-10-13 1700",
                "home");
        assertEquals(tuitions.get(0), eventTuition);
    }

    @Test
    void addEvent_invalidTuition_exceptionThrown() {
        ArrayList<Event> tuitions = new ArrayList<>();
        EventTuitionManager eventTuitionManager = new EventTuitionManager(tuitions);

        assertThrows(EmptyParameterException.class, () -> eventTuitionManager.add("/n /s /e /l"));
        assertThrows(MissingParameterException.class, () -> eventTuitionManager.add("/n math tuition "
                + "/s 2020-09-22 1700 /e 2020-09-22 1800"));
    }
}
