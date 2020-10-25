package seedu.duke.storage;

import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.model.event.cca.EventCca;
import seedu.duke.model.event.classlesson.EventClass;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.test.EventTest;
import seedu.duke.model.event.tuition.EventTuition;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

//@@author durianpancakes
public class EventListEncoder {
    public EventListEncoder() {
    }

    public ArrayList<String> encodeEventList(ArrayList<Event> eventList) {
        ArrayList<String> encodedEvents = new ArrayList<>();

        for (Event event : eventList) {
            encodedEvents.add(encodeEventToString(event));
        }

        return encodedEvents;
    }

    private String encodeEventToString(Event event) {
        String result = "";
        DateTimeParser dateTimeParser = new DateTimeParser();

        String start = dateTimeParser.convertCalendarToString(event.getStart());
        String end = dateTimeParser.convertCalendarToString(event.getEnd());

        if (event instanceof EventCca) {
            EventCca eventCca = (EventCca) event;
            result = EventCca.CCA_ICON + "|"
                    + eventCca.isDone() + "|"
                    + eventCca.getDescription() + "|"
                    + start + "|"
                    + end;
        } else if (event instanceof EventTest) {
            EventTest eventTest = (EventTest) event;
            result = EventTest.TEST_ICON + "|"
                    + eventTest.isDone() + "|"
                    + eventTest.getDescription() + "|"
                    + start + "|"
                    + end;
        } else if (event instanceof EventTuition) {
            EventTuition newEventTuition = (EventTuition) event;
            result = EventTuition.TUITION_ICON + "|"
                    + newEventTuition.isDone() + "|"
                    + newEventTuition.getDescription() + "|"
                    + start + "|"
                    + end + "|"
                    + newEventTuition.getLocation();
        } else if (event instanceof EventClass) {
            EventClass newEventClass = (EventClass) event;
            result = EventClass.CLASS_ICON + "|"
                    + newEventClass.isDone() + "|"
                    + newEventClass.getDescription() + "|"
                    + start + "|"
                    + end;
        }

        return result;
    }
}
