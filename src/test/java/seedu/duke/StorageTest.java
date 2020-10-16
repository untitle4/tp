package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidValueException;
import seedu.duke.storage.EventStorageManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    //    @Test
    //    public void constructor_noTxtExtension_exceptionThrown() {
    //        assertThrows(InvalidValueException.class, () ->
    //                new EventStorageManager("test"));
    //    }
    //
    //    @Test
    //    public void constructor_nullFilePath_exceptionThrown() {
    //        assertThrows(NullPointerException.class, () ->
    //                new EventStorageManager("test"));
    //    }

    //    @Test
    //    public void load_validFormat() throws InvalidValueException {
    //        EventStorageManager eventStorageManager = new EventStorageManager("test");
    //        assertEquals(getEventList(), eventStorageManager.getEventList());
    //    }

    private ArrayList<Event> getEventList() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(new Cca("Basketball training ",
                "2020-09-21 1800 ",
                "2020-09-21 1900"));
        events.add(new Class("Math tuition ",
                "2020-09-22 1400 ",
                "2020-09-22-1600"));
        events.add(new seedu.duke.Test("CS2113T Finals ",
                "2020-12-04 1500 ",
                "2020-12-04 1600"));
        events.add(new Tuition("English",
                "2020-12-05 1600",
                "2020-12-05 1800",
                "home"));

        return events;
    }
}
