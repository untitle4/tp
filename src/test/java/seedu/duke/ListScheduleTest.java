package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyListException;
import seedu.duke.model.event.classlesson.EventClass;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListScheduleTest {

    @Test
    void getAllEventsPrinted_emptySchedule_expectException() throws EmptyListException {
        ListSchedule listSchedule = new ListSchedule(new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        assertThrows(EmptyListException.class, listSchedule::getAllEventsPrinted);
    }

    @Test
    void getAllEventsPrinted_classSchedule_classList() throws EmptyListException {
        ArrayList<Event> classes = new ArrayList<>();
        classes.add(new EventClass("Math", "2019-02-26 1400", "2019-02-27 1500"));
        ListSchedule listSchedule = new ListSchedule(classes, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        ArrayList<String> actualOutputs = listSchedule.getAllEventsPrinted();
        ArrayList<String> expectedOutputs = new ArrayList<>(
                List.of("Classes:", "1. [CLASS] [NOT DONE] Math from 26th Feb 2019 , "
                        + "02:00 PM to 27th Feb 2019 , 03:00 PM"));
        assertEquals(actualOutputs, expectedOutputs);
    }
}