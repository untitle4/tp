package seedu.duke.storage;

import seedu.duke.model.event.cca.Cca;
import seedu.duke.model.event.classlesson.Class;
import seedu.duke.Event;
import seedu.duke.model.event.test.Test;
import seedu.duke.model.event.tuition.Tuition;

import java.util.ArrayList;

public class EventListDecoder {
    public EventListDecoder() {
    }

    public ArrayList<Event> decodeEventList(ArrayList<String> encodedEventList) {
        final ArrayList<Event> decodedEvents = new ArrayList<>();
        for (String encodedEvent : encodedEventList) {
            decodedEvents.add(decodeEventFromString(encodedEvent));
        }
        return decodedEvents;
    }

    private Event decodeEventFromString(String encodedEvent) {
        final String[] data = encodedEvent.trim().split("\\|", 3);

        switch (data[0]) {
        case Cca.CCA_ICON:
            return parseCca(data);
        case Class.CLASS_ICON:
            return parseClass(data);
        case Test.TEST_ICON:
            return parseTest(data);
        case Tuition.TUITION_ICON:
            return parseTuition(data);
        default:
            return null;
        }
    }

    private Cca parseCca(String[] data) {
        boolean isDone;
        String description;
        isDone = Boolean.parseBoolean(data[1]);
        String[] eventInfo = data[2].trim().split("\\|", 3);
        description = eventInfo[0];
        String start = eventInfo[1];
        String end = eventInfo[2];

        isValidParams(description, start, end);

        return new Cca(description, isDone, start, end);
    }

    private Class parseClass(String[] data) {
        boolean isDone;
        String description;
        isDone = Boolean.parseBoolean(data[1]);
        String[] eventInfo = data[2].trim().split("\\|", 3);
        description = eventInfo[0];
        String start = eventInfo[1];
        String end = eventInfo[2];

        isValidParams(description, start, end);

        return new Class(description, isDone, start, end);
    }

    private Test parseTest(String[] data) {
        boolean isDone;
        String description;
        isDone = Boolean.parseBoolean(data[1]);
        String[] eventInfo = data[2].trim().split("\\|", 3);
        description = eventInfo[0];
        String start = eventInfo[1];
        String end = eventInfo[2];

        isValidParams(description, start, end);

        return new Test(description, isDone, start, end);
    }

    private Tuition parseTuition(String[] data) {
        boolean isDone;
        String description;
        isDone = Boolean.parseBoolean(data[1]);
        String[] eventInfo = data[2].trim().split("\\|", 4);
        description = eventInfo[0];
        String start = eventInfo[1];
        String end = eventInfo[2];
        String location = eventInfo[3];

        isValidTuition(description, start, end, location);

        return new Tuition(description, isDone, start, end, location);
    }

    private void isValidParams(String description, String start, String end) {
        assert description != null;
        assert !description.equals("");
        assert start != null;
        assert !start.equals("");
        assert end != null;
        assert !end.equals("");
    }

    private void isValidTuition(String description, String start, String end, String location) {
        isValidParams(description, start, end);
        assert location != null;
        assert !location.equals("");
    }
}
