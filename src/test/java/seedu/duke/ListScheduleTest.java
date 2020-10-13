package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyListException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListScheduleTest {

    @Test
    void getAllEventsPrinted_emptySchedule_expectException() throws EmptyListException {
        ListSchedule listSchedule = new ListSchedule(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertThrows(EmptyListException.class, listSchedule::getAllEventsPrinted);
    }

    @Test
    void getAllEventsPrinted_classSchedule_classList() throws EmptyListException {
        ArrayList<Event> classes = new ArrayList<>();
        classes.add(new Class("Math", "26/2/2019", "27/2/2019"));
        ListSchedule listSchedule = new ListSchedule(classes, new ArrayList<>(), new ArrayList<>());
        ArrayList<String> actualOutputs = listSchedule.getAllEventsPrinted();
        ArrayList<String> expectedOutputs = new ArrayList<>(
                List.of("Classes:", "1. [Class] [NOT DONE] Math from 26/2/2019 to 27/2/2019"));
        assertEquals(actualOutputs, expectedOutputs);
    }
}