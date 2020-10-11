package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @Test
    public void constructor_noTxtExtension_exceptionThrown() {
        assertThrows(InvalidValueException.class, () ->
                new StorageManager("test", "file"));
    }

    @Test
    public void constructor_nullFilePath_exceptionThrown() {
        assertThrows(NullPointerException.class, () ->
                new StorageManager("test", null));
    }

    @Test
    public void load_validFormat() {
        try {
            StorageManager storageManager = new StorageManager("test", "/ValidData.txt");
            assertEquals(getEventList(), storageManager.getEventList());
        } catch (InvalidValueException e) {
            e.printStackTrace();
        }
    }

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

        return events;
    }
}
