package seedu.duke.storage.event;

import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.model.event.cca.EventCca;
import seedu.duke.model.event.classlesson.EventClass;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.test.EventTest;
import seedu.duke.model.event.tuition.EventTuition;

import java.util.ArrayList;

//@@author durianpancakes
/**
 * Class responsible for the encoding of Events to String for writing to the data file.
 */
public class EventListEncoder {
    public EventListEncoder() {
    }

    /**
     * Encodes an ArrayList of Events into an ArrayList of Strings.
     *
     * @param eventList ArrayList of Events to be encoded.
     * @return ArrayList of encoded Strings to be written to data file.
     */
    public ArrayList<String> encodeEventList(ArrayList<Event> eventList) {
        ArrayList<String> encodedEvents = new ArrayList<>();

        for (Event event : eventList) {
            encodedEvents.add(encodeEventToString(event));
        }

        return encodedEvents;
    }

    /**
     * Encode an Event to String.
     *
     * @param event Event to be encoded.
     * @return String containing the encoded Event.
     */
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
