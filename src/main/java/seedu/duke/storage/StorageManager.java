package seedu.duke.storage;

import seedu.duke.common.LogManager;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class StorageManager {
    public static final String EMPTY_FILE_NAME = "";
    protected static String DIRECTORY_FOLDER_PATH = new File("data").getAbsolutePath();
    protected String fileName;
    private static final Logger logger = LogManager.getLogger();

    public StorageManager(String fileName) {
        assert !fileName.equals(EMPTY_FILE_NAME);
        this.fileName = fileName;
    }

    protected boolean createDataFile() throws IOException {
        File file = new File(DIRECTORY_FOLDER_PATH);
        boolean isDirectoryCreated = file.mkdir();
        file = new File(DIRECTORY_FOLDER_PATH + fileName);

        if (isDirectoryCreated) {
            logger.log(Level.INFO, "Directory not found, creating...");
        } else {
            logger.log(Level.INFO, "Directory found...");
        }

        return file.createNewFile();
    }
}
