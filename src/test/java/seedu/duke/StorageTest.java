package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.exception.InvalidValueException;
import seedu.duke.exception.StorageCorruptedException;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.EventParameter;
import seedu.duke.model.event.cca.EventCca;
import seedu.duke.model.event.classlesson.EventClass;
import seedu.duke.model.event.test.EventTest;
import seedu.duke.model.event.tuition.EventTuition;
import seedu.duke.storage.EventStorageManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author durianpancakes
public class StorageTest {
    //@Test
    public void constructor_noTxtExtension_exceptionThrown() {
        assertThrows(InvalidValueException.class, () ->
                new EventStorageManager("test"));
    }

    //@Test
    public void constructor_nullFilePath_exceptionThrown() {
        assertThrows(NullPointerException.class, () ->
                new EventStorageManager("test"));
    }

    //@Test
    public void load_validFormat() throws StorageCorruptedException {
        EventStorageManager eventStorageManager = new EventStorageManager("test");
        EventParameter eventParameter = eventStorageManager.loadData();
        eventParameter.getEventMasterList();
        assertEquals(getEventList(), eventParameter.getEventMasterList());
    }

    private ArrayList<Event> getEventList() {
        ArrayList<Event> events = new ArrayList<>();
        DateTimeParser dateTimeParser = new DateTimeParser();

        events.add(new EventCca("Basketball training ",
                dateTimeParser.convertStringToCalendar("2020-09-21 1800"),
                dateTimeParser.convertStringToCalendar("2020-09-21 1900")));
        events.add(new EventClass("Math tuition ",
                dateTimeParser.convertStringToCalendar("2020-09-22 1400"),
                dateTimeParser.convertStringToCalendar("2020-09-22-1600")));
        events.add(new EventTest("CS2113T Finals ",
                dateTimeParser.convertStringToCalendar("2020-12-04 1500"),
                dateTimeParser.convertStringToCalendar("2020-12-04 1600")));
        events.add(new EventTuition("English",
                dateTimeParser.convertStringToCalendar("2020-12-05 1600"),
                dateTimeParser.convertStringToCalendar("2020-12-05 1800"),
                "home"));

        return events;
    }
}
