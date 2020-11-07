package seedu.duke.storage.event;

import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.exception.StorageCorruptedException;
import seedu.duke.exception.StorageSeparatorException;
import seedu.duke.model.event.cca.EventCca;
import seedu.duke.model.event.classlesson.EventClass;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.test.EventTest;
import seedu.duke.model.event.tuition.EventTuition;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

//@@author durianpancakes
public class EventListDecoder {
    public EventListDecoder() {
    }

    public ArrayList<Event> decodeEventList(ArrayList<String> encodedEventList) throws StorageSeparatorException,
            StorageCorruptedException {
        final ArrayList<Event> decodedEvents = new ArrayList<>();
        for (String encodedEvent : encodedEventList) {
            decodedEvents.add(decodeEventFromString(encodedEvent));
        }
        return decodedEvents;
    }

    private Event decodeEventFromString(String encodedEvent) throws StorageSeparatorException,
            StorageCorruptedException {
        final String[] data = encodedEvent.trim().split("\\|", 3);

        switch (data[0]) {
        case EventCca.CCA_ICON:
            return parseCca(data);
        case EventClass.CLASS_ICON:
            return parseClass(data);
        case EventTest.TEST_ICON:
            return parseTest(data);
        case EventTuition.TUITION_ICON:
            return parseTuition(data);
        default:
            return null;
        }
    }

    private EventCca parseCca(String[] data) throws StorageSeparatorException, StorageCorruptedException {
        boolean isDone;
        String description;
        isDone = Boolean.parseBoolean(data[1]);
        String[] eventInfo = data[2].trim().split("\\|", 3);

        if (eventInfo.length != 3) {
            throw new StorageSeparatorException();
        }

        description = eventInfo[0];
        String start = eventInfo[1];
        String end = eventInfo[2];

        DateTimeParser dateTimeParser = new DateTimeParser();
        Calendar startCalendar;
        Calendar endCalendar;
        try {
            startCalendar = dateTimeParser.convertStringToCalendar(start);
            endCalendar = dateTimeParser.convertStringToCalendar(end);
        } catch (ParseException e) {
            throw new StorageCorruptedException();
        }

        return new EventCca(description, isDone, startCalendar, endCalendar);
    }

    private EventClass parseClass(String[] data) throws StorageSeparatorException, StorageCorruptedException {
        boolean isDone;
        String description;
        isDone = Boolean.parseBoolean(data[1]);
        String[] eventInfo = data[2].trim().split("\\|", 3);

        if (eventInfo.length != 3) {
            throw new StorageSeparatorException();
        }

        description = eventInfo[0];
        String start = eventInfo[1];
        String end = eventInfo[2];

        DateTimeParser dateTimeParser = new DateTimeParser();
        Calendar startCalendar;
        Calendar endCalendar;
        try {
            startCalendar = dateTimeParser.convertStringToCalendar(start);
            endCalendar = dateTimeParser.convertStringToCalendar(end);
        } catch (ParseException e) {
            throw new StorageCorruptedException();
        }

        return new EventClass(description, isDone, startCalendar, endCalendar);
    }

    private EventTest parseTest(String[] data) throws StorageSeparatorException, StorageCorruptedException {
        boolean isDone;
        String description;
        isDone = Boolean.parseBoolean(data[1]);
        String[] eventInfo = data[2].trim().split("\\|", 3);

        if (eventInfo.length != 3) {
            throw new StorageSeparatorException();
        }

        description = eventInfo[0];
        String start = eventInfo[1];
        String end = eventInfo[2];

        DateTimeParser dateTimeParser = new DateTimeParser();
        Calendar startCalendar;
        Calendar endCalendar;
        try {
            startCalendar = dateTimeParser.convertStringToCalendar(start);
            endCalendar = dateTimeParser.convertStringToCalendar(end);
        } catch (ParseException e) {
            throw new StorageCorruptedException();
        }

        return new EventTest(description, isDone, startCalendar, endCalendar);
    }

    private EventTuition parseTuition(String[] data) throws StorageSeparatorException, StorageCorruptedException {
        boolean isDone;
        String description;
        isDone = Boolean.parseBoolean(data[1]);
        String[] eventInfo = data[2].trim().split("\\|", 4);

        if (eventInfo.length != 4) {
            throw new StorageSeparatorException();
        }

        description = eventInfo[0];
        String start = eventInfo[1];
        String end = eventInfo[2];
        String location = eventInfo[3];

        DateTimeParser dateTimeParser = new DateTimeParser();
        Calendar startCalendar;
        Calendar endCalendar;
        try {
            startCalendar = dateTimeParser.convertStringToCalendar(start);
            endCalendar = dateTimeParser.convertStringToCalendar(end);
        } catch (ParseException e) {
            throw new StorageCorruptedException();
        }

        return new EventTuition(description, isDone, startCalendar, endCalendar, location);
    }
}
