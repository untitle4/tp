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

class ListScheduleTest {
    //@Test
    void getPrintableEvents_emptySchedule_expectException() {
        ListSchedule listSchedule = new ListSchedule(null, new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        assertThrows(EmptyListException.class, listSchedule::getPrintableEvents);
    }

    //@Test
    void getPrintableEvents_classSchedule_allClasses() throws EmptyListException, ParseException {
        ArrayList<Event> classes = new ArrayList<>();
        Calendar startCalendar = parseLocalDateTime("2019-02-26 1400");
        Calendar endCalendar = parseLocalDateTime("2019-02-27 1500");
        classes.add(new EventClass("Math", startCalendar,
                endCalendar));
        ListSchedule listSchedule = new ListSchedule(null, classes, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        ArrayList<String> actualOutputs = listSchedule.getPrintableEvents();
        ArrayList<String> expectedOutputs = new ArrayList<>(
                List.of("Classes: ", "1. [CLASS] [NOT DONE] Math from 26th Feb 2019 , "
                        + "02:00 PM to 27th Feb 2019 , 03:00 PM"));
        assertEquals(expectedOutputs, actualOutputs);
    }

    //@Test
    void getPrintableEvents_allSchedule_allEvents() throws EmptyListException {
        ArrayList<Event> classes = new ArrayList<>();
        classes.add(new EventClass("Math", parseLocalDateTime("2019-02-26 1400"),
                parseLocalDateTime("2019-02-27 1500")));
        ArrayList<Event> ccas = new ArrayList<>();
        ccas.add(new EventCca("Basketball", parseLocalDateTime("2019-02-26 1400"),
                parseLocalDateTime("2019-02-27 1500")));
        ArrayList<Event> tests = new ArrayList<>();
        tests.add(new EventTest("Science", parseLocalDateTime("2019-02-26 1400"),
                parseLocalDateTime("2019-02-27 1500")));
        ArrayList<Event> tuitions = new ArrayList<>();
        tuitions.add(new EventTuition("English", parseLocalDateTime("2019-02-26 1400"),
                parseLocalDateTime("2019-02-27 1500"),
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

    /*  @Test
    void getPrintableEvents_classScheduleToday_oneClass() throws EmptyListException, ParseException {
        ArrayList<Event> classes = new ArrayList<>();
        LocalDate todayDate = LocalDate.now();
        String todayString = todayDate.toString() + " 1400";
        classes.add(new EventClass("Math", parseLocalDateTime(todayString),
                parseLocalDateTime("2019-02-27 1500")));
        ListSchedule listSchedule = new ListSchedule("today", classes, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        ArrayList<String> actualOutputs = listSchedule.getPrintableEvents();

        DateTimeParser dateTimeParser = new DateTimeParser();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mma");
        String formattedDate = sdf.parse(todayString);

        ArrayList<String> expectedOutputs = new ArrayList<>(
                List.of("Classes: ", "1. [CLASS] [NOT DONE] Math from " + formattedDate
                        + " to 27th Feb 2019 , 03:00 PM"));

        assertEquals(expectedOutputs, actualOutputs);
    }*/

    /*    @Test
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

    private Calendar parseLocalDateTime(String localDateTimeString) {
        LocalDateTime.parse(localDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmm");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(localDateTimeString);
            calendar.setTime(date);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        return calendar;
    }
}