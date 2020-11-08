package seedu.duke.model.event.tuition;

import org.junit.jupiter.api.Test;
import seedu.duke.storage.TestUtils;
import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.event.Event;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddEventTuitionJUnitTest {
    TestUtils testUtils = new TestUtils();

    @Test
    void addEvent_validTuition_noException() throws EmptyParameterException,
            MissingParameterException, ParseException {
        ArrayList<Event> tuitions = new ArrayList<>();
        EventTuitionManager eventTuitionManager = new EventTuitionManager(tuitions,
                testUtils.getEmptyEventManager());
        eventTuitionManager.add("add tuition /n math tuition "
                + "/s 2020-10-13 1500 /e 2020-10-13 1700 "
                + "/l home");

        DateTimeParser dateTimeParser = new DateTimeParser();

        EventTuition eventTuition = new EventTuition("math tuition",
                dateTimeParser.convertStringToCalendar("2020-10-13 1500"),
                dateTimeParser.convertStringToCalendar("2020-10-13 1700"),
                "home");
        assertEquals(tuitions.get(0), eventTuition);
    }

    @Test
    void addEvent_invalidTuition_exceptionThrown() {
        ArrayList<Event> tuitions = new ArrayList<>();
        EventTuitionManager eventTuitionManager = new EventTuitionManager(tuitions,
                testUtils.getEmptyEventManager());

        assertThrows(EmptyParameterException.class, () -> eventTuitionManager.add("/n /s /e /l"));
        assertThrows(MissingParameterException.class, () -> eventTuitionManager.add("/n math tuition "
                + "/s 2020-09-22 1700 /e 2020-09-22 1800"));
    }
}
