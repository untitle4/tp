package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListScheduleTest {

    @Test
    void getAllEventsPrinted_emptySchedule_emptyList() {
        ListSchedule listSchedule = new ListSchedule(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        ArrayList<String> actualOutputs = listSchedule.getAllEventsPrinted();
        ArrayList<String> expectedOutputs = new ArrayList<>(List.of("Classes:", "CCAs:", "Tests:"));
        assertEquals(actualOutputs, expectedOutputs);
    }

    @Test
    void getAllEventsPrinted_classSchedule_classList() {
        ArrayList<Event> classes = new ArrayList<>();
        classes.add(new Class("Math ", "26/2/2019 ", "27/2/2019"));
        ListSchedule listSchedule = new ListSchedule(classes, new ArrayList<>(), new ArrayList<>());
        ArrayList<String> actualOutputs = listSchedule.getAllEventsPrinted();
        ArrayList<String> expectedOutputs = new ArrayList<>(
                List.of("Classes:", "1. [Class] Math from 26/2/2019 to 27/2/2019",  "CCAs:", "Tests:"));
        assertEquals(actualOutputs, expectedOutputs);
    }
}