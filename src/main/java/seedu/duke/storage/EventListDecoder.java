package seedu.duke.storage;

import seedu.duke.Cca;
import seedu.duke.Class;
import seedu.duke.Event;
import seedu.duke.Test;

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

    private void isValidParams(String description, String start, String end) {
        assert description != null;
        assert !description.equals("");
        assert start != null;
        assert !start.equals("");
        assert end != null;
        assert !end.equals("");
    }
}
