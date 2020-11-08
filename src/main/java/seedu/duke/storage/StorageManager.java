package seedu.duke.storage;

import seedu.duke.common.LogManager;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract StorageManager class containing the basic methods to create a data file.
 */
public abstract class StorageManager {
    //@@author durianpancakes
    public static final String EMPTY_FILE_NAME = "";
    public static final String EVENT_FILE_NAME = "/events.txt";
    public static final String QUIZ_FILE_NAME = "/quiz.txt";
    public static final String CONTACT_FILE_NAME = "/contact.txt";
    public static final String CONFIG_FILE_NAME = "/config.txt";
    protected static String DIRECTORY_FOLDER_PATH = new File("data").getAbsolutePath();
    protected String fileName;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();

    //@@author AndreWongZH
    public StorageManager(String fileName) {
        assert !fileName.equals(EMPTY_FILE_NAME);
        this.fileName = fileName;
    }

    //@@author durianpancakes
    /**
     * Constructor for Test files.
     *
     * @param directory String containing the directory to be accessed.
     * @param fileName String containing the file to be accessed.
     */
    public StorageManager(String directory, String fileName) {
        assert !directory.equals(EMPTY_FILE_NAME);
        DIRECTORY_FOLDER_PATH = new File(directory).getAbsolutePath();
        assert !fileName.equals(EMPTY_FILE_NAME);
        this.fileName = fileName;
    }
  
    /**
     * Creates the 'data' directory if it does not exist. Creates the data file .txt if it does not exist.
     *
     * @return boolean. true if data file is created, false if data file already exists
     * @throws IOException if there was a problem in creating a new file
     */
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
