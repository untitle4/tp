package seedu.duke.storage;

import seedu.duke.model.event.cca.EventCca;
import seedu.duke.model.event.classlesson.EventClass;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.test.EventTest;
import seedu.duke.model.event.tuition.EventTuition;

import java.util.ArrayList;

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

        if (event instanceof EventCca) {
            EventCca eventCca = (EventCca) event;
            assertValidCca(eventCca);
            result = EventCca.CCA_ICON + "|"
                    + eventCca.isDone() + "|"
                    + eventCca.getDescription() + "|"
                    + eventCca.getStart() + "|"
                    + eventCca.getEnd();
        } else if (event instanceof EventTest) {
            EventTest eventTest = (EventTest) event;
            assertValidTest(eventTest);
            result = EventTest.TEST_ICON + "|"
                    + eventTest.isDone() + "|"
                    + eventTest.getDescription() + "|"
                    + eventTest.getStart() + "|"
                    + eventTest.getEnd();
        } else if (event instanceof EventTuition) {
            EventTuition newEventTuition = (EventTuition) event;
            assertValidTuition(newEventTuition);
            result = EventTuition.TUITION_ICON + "|"
                    + newEventTuition.isDone() + "|"
                    + newEventTuition.getDescription() + "|"
                    + newEventTuition.getStart() + "|"
                    + newEventTuition.getEnd() + "|"
                    + newEventTuition.getLocation();
        } else if (event instanceof EventClass) {
            EventClass newEventClass = (EventClass) event;
            assertValidClass(newEventClass);
            result = EventClass.CLASS_ICON + "|"
                    + newEventClass.isDone() + "|"
                    + newEventClass.getDescription() + "|"
                    + newEventClass.getStart() + "|"
                    + newEventClass.getEnd();
        }

        return result;
    }

    private void assertValidCca(EventCca eventCca) {
        isValidParams(eventCca.getDescription(), eventCca.getStart(), eventCca.getEnd());
    }

    private void assertValidClass(EventClass inputEventClass) {
        isValidParams(inputEventClass.getDescription(), inputEventClass.getStart(), inputEventClass.getEnd());
    }

    private void assertValidTest(EventTest eventTest) {
        isValidParams(eventTest.getDescription(), eventTest.getStart(), eventTest.getEnd());
    }

    private void assertValidTuition(EventTuition eventTuition) {
        isValidParams(eventTuition.getDescription(), eventTuition.getStart(), eventTuition.getEnd());
        assert eventTuition.getLocation() != null;
        assert !eventTuition.getLocation().equals("");
    }

    private void isValidParams(String description, String start, String end) {
        assert description != null;
        assert !description.equals("");
        assert start != null;
        assert !start.equals("");
        assert end != null;
        assert !end.equals("");
    }
}
