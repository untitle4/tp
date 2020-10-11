package seedu.duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        case "[CCA]":
            return parseCca(data);
        case "[CLASS]":
            return parseClass(data);
        case "[TEST]":
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

        return new Test(description, isDone, start, end);
    }
}
