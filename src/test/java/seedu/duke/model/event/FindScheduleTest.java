package seedu.duke.model.event;

import org.junit.jupiter.api.Test;
import seedu.duke.model.event.classlesson.EventClass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author AndreWongZH
class FindScheduleTest {
    private final ArrayList<Event> classes = new ArrayList<>();
    private final ArrayList<Event> ccas = new ArrayList<>();
    private final ArrayList<Event> tests = new ArrayList<>();
    private final ArrayList<Event> tuitions = new ArrayList<>();

    public FindScheduleTest() {
        Calendar cal = Calendar.getInstance();
        cal.set(2020, Calendar.MARCH, 10, 23,10);
        classes.add(new EventClass("Math exam", cal, cal));
        classes.add(new EventClass("English exam", cal, cal));
    }

    @Test
    void getFilteredEvents_matchingKeyword_oneSearchResult() {
        FindSchedule findSchedule = new FindSchedule("math", classes, ccas, tests, tuitions);
        ArrayList<String> actualEvents = findSchedule.getFilteredEvents();
        ArrayList<String> expectedEvents = new ArrayList<>(
                List.of("[CLASS] Math exam from 10th Mar 2020, 11:10PM to 10th Mar 2020, 11:10PM"));
        assertEquals(expectedEvents, actualEvents);
    }

    @Test
    void getFilteredEvents_nonMatchingKeyword_noSearchResult() {
        FindSchedule findSchedule = new FindSchedule("science", classes, ccas, tests, tuitions);
        ArrayList<String> actualEvents = findSchedule.getFilteredEvents();
        ArrayList<String> expectedEvents = new ArrayList<>();
        assertEquals(expectedEvents, actualEvents);
    }

    @Test
    void getFilteredEvents_matchingMultipleKeyword_twoSearchResult() {
        FindSchedule findSchedule = new FindSchedule("math english", classes, ccas, tests, tuitions);
        ArrayList<String> actualEvents = findSchedule.getFilteredEvents();
        ArrayList<String> expectedEvents = new ArrayList<>(
                List.of("[CLASS] Math exam from 10th Mar 2020, 11:10PM to 10th Mar 2020, 11:10PM",
                        "[CLASS] English exam from 10th Mar 2020, 11:10PM to 10th Mar 2020, 11:10PM"));
        assertEquals(expectedEvents, actualEvents);
    }
}