package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.StorageCorruptedException;
import seedu.duke.storage.event.EventStorageManager;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    EventStorageManager validEventStorage = new EventStorageManager("testdata", "/validdata.txt");
    EventStorageManager invalidEventStorage = new EventStorageManager("testdata", "/invaliddata.txt");

    @Test
    public void loadData_validData_noException() throws StorageCorruptedException {
        validEventStorage.loadData();
    }

    @Test
    public void loadData_invalidData_exceptionThrown() {
        assertThrows(StorageCorruptedException.class, () -> invalidEventStorage.loadData());
    }
}
