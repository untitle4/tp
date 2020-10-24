package seedu.duke.model.event;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyListException;
import seedu.duke.model.event.cca.EventCca;
import seedu.duke.model.event.classlesson.EventClass;
import seedu.duke.model.event.test.EventTest;
import seedu.duke.model.event.tuition.EventTuition;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListScheduleTest {
    @Test
    void getPrintableEvents_emptySchedule_expectException() {
        ListSchedule listSchedule = new ListSchedule(null, new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        assertThrows(EmptyListException.class, listSchedule::getPrintableEvents);
    }

    @Test
    void getPrintableEvents_classSchedule_allClasses() throws EmptyListException {
        ArrayList<Event> classes = new ArrayList<>();
        classes.add(new EventClass("Math", "2019-02-26 1400", "2019-02-27 1500"));
        ListSchedule listSchedule = new ListSchedule(null, classes, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        ArrayList<String> actualOutputs = listSchedule.getPrintableEvents();
        ArrayList<String> expectedOutputs = new ArrayList<>(
                List.of("Classes: ", "1. [CLASS] [NOT DONE] Math from 26th Feb 2019 , "
                        + "02:00 PM to 27th Feb 2019 , 03:00 PM"));
        assertEquals(expectedOutputs, actualOutputs);
    }

    @Test
    void getPrintableEvents_allSchedule_allEvents() throws EmptyListException {
        ArrayList<Event> classes = new ArrayList<>();
        classes.add(new EventClass("Math", "2019-02-26 1400", "2019-02-27 1500"));
        ArrayList<Event> ccas = new ArrayList<>();
        ccas.add(new EventCca("Basketball", "2019-02-26 1400", "2019-02-27 1500"));
        ArrayList<Event> tests = new ArrayList<>();
        tests.add(new EventTest("Science", "2019-02-26 1400", "2019-02-27 1500"));
        ArrayList<Event> tuitions = new ArrayList<>();
        tuitions.add(new EventTuition("English", "2019-02-26 1400", "2019-02-27 1500",
                "Choa Chu Kang Avenue 5 Block 433"));

        ListSchedule listSchedule = new ListSchedule(null, classes, ccas, tests, tuitions);
        ArrayList<String> actualOutputs = listSchedule.getPrintableEvents();
        ArrayList<String> expectedOutputs = new ArrayList<>(
                List.of("Classes: ",
                        "1. [CLASS] [NOT DONE] Math from 26th Feb 2019 , 02:00 PM to 27th Feb 2019 , 03:00 PM",
                        "CCAs: ",
                        "1. [CCA] [NOT DONE] Basketball from 26th Feb 2019 , 02:00 PM to 27th Feb 2019 , 03:00 PM",
                        "Tests: ",
                        "1. [TEST] [NOT DONE] Science from 26th Feb 2019 , 02:00 PM to 27th Feb 2019 , 03:00 PM",
                        "Tuitions: ",
                        "1. [TUITION] [NOT DONE] English from 26th Feb 2019 , 02:00 PM to 27th Feb 2019 , 03:00 PM"
                                + " at Choa Chu Kang Avenue 5 Block 433"));
        assertEquals(expectedOutputs, actualOutputs);
    }

    //problematic testcase
    //    @Test
    //    void getPrintableEvents_classScheduleToday_oneClass() throws EmptyListException {
    //        ArrayList<Event> classes = new ArrayList<>();
    //        LocalDate todayDate = LocalDate.now();
    //
    //        classes.add(new EventClass("Math", todayDate.toString() + " 1400", "2019-02-27 1500"));
    //        ListSchedule listSchedule = new ListSchedule("today", classes, new ArrayList<>(),
    //                new ArrayList<>(), new ArrayList<>());
    //        ArrayList<String> actualOutputs = listSchedule.getPrintableEvents();
    //
    //        String formattedDate = todayDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
    //        ArrayList<String> expectedOutputs = new ArrayList<>(
    //                List.of("Classes: ", String.format("1. [CLASS] [NOT DONE] Math from %sth %s ,"
    //                        + " 02:00 PM to 27th Feb 2019 , 03:00 PM", todayDate.getDayOfMonth(), formattedDate)));
    //        assertEquals(expectedOutputs, actualOutputs);
    //    }
}