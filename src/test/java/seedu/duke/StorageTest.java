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

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author durianpancakes
public class StorageTest {
    TestUtils testUtils = new TestUtils();

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
    public void load_validFormat() throws StorageCorruptedException, ParseException {
        EventStorageManager eventStorageManager = new EventStorageManager("/ValidData.txt");
        EventParameter eventParameter = eventStorageManager.loadData();
        eventParameter.getEventMasterList();
        assertEquals(testUtils.getEventList(), eventParameter.getEventMasterList());
    }
}
