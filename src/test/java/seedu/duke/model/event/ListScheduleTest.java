package seedu.duke.model.event;

import org.junit.jupiter.api.Test;
import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.exception.EmptyListException;
import seedu.duke.model.event.cca.EventCca;
import seedu.duke.model.event.classlesson.EventClass;
import seedu.duke.model.event.test.EventTest;
import seedu.duke.model.event.tuition.EventTuition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author AndreWongZH
class ListScheduleTest {
    @Test
    void getPrintableEvents_emptySchedule_expectException() {
        ListSchedule listSchedule = new ListSchedule(null, new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        assertThrows(EmptyListException.class, listSchedule::getPrintableEvents);
    }

    @Test
    void getPrintableEvents_classSchedule_allClasses() throws EmptyListException, ParseException {
        ArrayList<Event> classes = new ArrayList<>();
        DateTimeParser dateTimeParser = new DateTimeParser();
        Calendar startCalendar = dateTimeParser.convertStringToCalendar("2019-02-26 1400");
        Calendar endCalendar = dateTimeParser.convertStringToCalendar("2019-02-27 1500");
        EventClass eventClass = new EventClass("Math", startCalendar, endCalendar);
        classes.add(eventClass);
        ListSchedule listSchedule = new ListSchedule(null, classes, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        ArrayList<String> actualOutputs = listSchedule.getPrintableEvents();
        ArrayList<String> expectedOutputs = new ArrayList<>(
                List.of("Classes: ", "1. [CLASS] Math from 26th Feb 2019, "
                        + "02:00PM to 27th Feb 2019, 03:00PM"));
        assertEquals(expectedOutputs, actualOutputs);
    }

    @Test
    void getPrintableEvents_allSchedule_allEvents() throws EmptyListException, ParseException {
        ArrayList<Event> classes = new ArrayList<>();
        DateTimeParser dateTimeParser = new DateTimeParser();
        classes.add(new EventClass("Math", dateTimeParser.convertStringToCalendar("2019-02-26 1400"),
                dateTimeParser.convertStringToCalendar("2019-02-27 1500")));
        ArrayList<Event> ccas = new ArrayList<>();
        ccas.add(new EventCca("Basketball", dateTimeParser.convertStringToCalendar("2019-02-26 1400"),
                dateTimeParser.convertStringToCalendar("2019-02-27 1500")));
        ArrayList<Event> tests = new ArrayList<>();
        tests.add(new EventTest("Science", dateTimeParser.convertStringToCalendar("2019-02-26 1400"),
                dateTimeParser.convertStringToCalendar("2019-02-27 1500")));
        ArrayList<Event> tuitions = new ArrayList<>();
        tuitions.add(new EventTuition("English", dateTimeParser.convertStringToCalendar("2019-02-26 1400"),
                dateTimeParser.convertStringToCalendar("2019-02-27 1500"),
                "Choa Chu Kang Avenue 5 Block 433"));

        ListSchedule listSchedule = new ListSchedule(null, classes, ccas, tests, tuitions);
        ArrayList<String> actualOutputs = listSchedule.getPrintableEvents();
        ArrayList<String> expectedOutputs = new ArrayList<>(
                List.of("Classes: ",
                        "1. [CLASS] Math from 26th Feb 2019, 02:00PM to 27th Feb 2019, 03:00PM",
                        "CCAs: ",
                        "1. [CCA] Basketball from 26th Feb 2019, 02:00PM to 27th Feb 2019, 03:00PM",
                        "Tests: ",
                        "1. [TEST] Science from 26th Feb 2019, 02:00PM to 27th Feb 2019, 03:00PM",
                        "Tuitions: ",
                        "1. [TUITION] English from 26th Feb 2019, 02:00PM to 27th Feb 2019, 03:00PM"
                                + " at Choa Chu Kang Avenue 5 Block 433"));
        assertEquals(expectedOutputs, actualOutputs);
    }

    @Test
    void getPrintableEvents_classScheduleToday_oneClass() throws EmptyListException, ParseException {
        ArrayList<Event> classes = new ArrayList<>();
        DateTimeParser dateTimeParser = new DateTimeParser();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 0);
        classes.add(new EventClass("Math", calendar,
                dateTimeParser.convertStringToCalendar("2019-02-27 1500")));
        ListSchedule listSchedule = new ListSchedule("today", classes, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        ArrayList<String> actualOutputs = listSchedule.getPrintableEvents();

        String formattedDate = dateTimeParser.obtainFormattedDateTimeString(calendar);

        ArrayList<String> expectedOutputs = new ArrayList<>(
                List.of("Classes: ", "1. [CLASS] Math from " + formattedDate
                        + " to 27th Feb 2019, 03:00PM"));

        assertEquals(expectedOutputs, actualOutputs);
    }

    /*@Test
    void getPrintableEvents_classScheduleWeek() throws EmptyListException {
        ArrayList<Event> classes = new ArrayList<>();
        LocalDate todayDate = LocalDate.now();

        classes.add(new EventClass("Math", todayDate.toString() + " 1400", "2019-02-27 1500"));
        ListSchedule listSchedule = new ListSchedule("week", classes, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        ArrayList<String> actualOutputs = listSchedule.getPrintableEvents();

        String formattedDate = todayDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
        String day = Calendar.DAY_OF_MONTH;
        ArrayList<String> expectedOutputs = new ArrayList<>(
                List.of(day, String.format("1. [CLASS] [NOT DONE] Math from %sst %s ,"
                        + " 02:00 PM to 27th Feb 2019 , 03:00 PM", todayDate.getDayOfMonth(), formattedDate)));
        assertEquals(expectedOutputs, actualOutputs);
    }*/
}