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

/**
 * Class responsible for the decoding of events read from the data file.
 */
public class EventListDecoder {
    public EventListDecoder() {
    }

    /**
     * Decodes an ArrayList of Strings read from the data file to an ArrayList of Events.
     *
     * @param encodedEventList ArrayList of encoded String read from the data file.
     * @return ArrayList of decoded String.
     * @throws StorageSeparatorException when any separator other than "|" is used.
     * @throws StorageCorruptedException when the storage file is determined to be unreadable.
     */
    public ArrayList<Event> decodeEventList(ArrayList<String> encodedEventList) throws StorageSeparatorException,
            StorageCorruptedException {
        final ArrayList<Event> decodedEvents = new ArrayList<>();
        for (String encodedEvent : encodedEventList) {
            decodedEvents.add(decodeEventFromString(encodedEvent));
        }
        return decodedEvents;
    }

    /**
     * Decode a String to an Event.
     *
     * @param encodedEvent String containing the encoded Event.
     * @return Event decoded from the String.
     * @throws StorageSeparatorException when any separator other than "|" is used.
     * @throws StorageCorruptedException when the storage file is determined to be unreadable.
     */
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

    /**
     * Parses a separated String input into an EventCca.
     *
     * @param data Array of Strings containing the separated parameters.
     * @return EventCca decoded from the String
     * @throws StorageSeparatorException when any separator other than "|" is used.
     * @throws StorageCorruptedException when the storage file is determined to be unreadable.
     */
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

    /**
     * Parses a separated String input into an EventClass.
     *
     * @param data Array of Strings containing the separated parameters.
     * @return EventClass decoded from the String
     * @throws StorageSeparatorException when any separator other than "|" is used.
     * @throws StorageCorruptedException when the storage file is determined to be unreadable.
     */
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

    /**
     * Parses a separated String input into an EventTest.
     *
     * @param data Array of Strings containing the separated parameters.
     * @return EventTest decoded from the String
     * @throws StorageSeparatorException when any separator other than "|" is used.
     * @throws StorageCorruptedException when the storage file is determined to be unreadable.
     */
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

    /**
     * Parses a separated String input into an EventTuition.
     *
     * @param data Array of Strings containing the separated parameters.
     * @return EventTuition decoded from the String
     * @throws StorageSeparatorException when any separator other than "|" is used.
     * @throws StorageCorruptedException when the storage file is determined to be unreadable.
     */
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
