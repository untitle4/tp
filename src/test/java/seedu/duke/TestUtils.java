package seedu.duke;

import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.event.EventParameter;
import seedu.duke.model.event.cca.EventCca;
import seedu.duke.model.event.classlesson.EventClass;
import seedu.duke.model.event.test.EventTest;
import seedu.duke.model.event.tuition.EventTuition;

import java.text.ParseException;
import java.util.ArrayList;

public class TestUtils {
    public EventManager getEmptyEventManager() {
        return new EventManager(new EventParameter());
    }

    public ArrayList<Event> getEventList() throws ParseException {
        ArrayList<Event> events = new ArrayList<>();
        DateTimeParser dateTimeParser = new DateTimeParser();

        events.add(new EventCca("Basketball training",
                dateTimeParser.convertStringToCalendar("2020-09-21 1800"),
                dateTimeParser.convertStringToCalendar("2020-09-21 1900")));
        events.add(new EventClass("Math tuition",
                dateTimeParser.convertStringToCalendar("2020-09-22 1400"),
                dateTimeParser.convertStringToCalendar("2020-09-22 1600")));
        events.add(new EventTest("CS2113T Finals",
                dateTimeParser.convertStringToCalendar("2020-12-04 1500"),
                dateTimeParser.convertStringToCalendar("2020-12-04 1600")));
        events.add(new EventTuition("English",
                dateTimeParser.convertStringToCalendar("2020-12-05 1600"),
                dateTimeParser.convertStringToCalendar("2020-12-05 1800"),
                "home"));

        return events;
    }

    public ArrayList<Event> getTuitionList() throws ParseException {
        ArrayList<Event> tuitions = new ArrayList<>();
        DateTimeParser dateTimeParser = new DateTimeParser();

        tuitions.add(new EventTuition("math", dateTimeParser.convertStringToCalendar("2020-09-26 1400"),
                dateTimeParser.convertStringToCalendar("2020-09-26 1500"), "home"));
        tuitions.add(new EventTuition("english", dateTimeParser.convertStringToCalendar("2020-09-27 1400"),
                dateTimeParser.convertStringToCalendar("2020-09-27 1500"), "tuition centre"));

        return tuitions;
    }
}
