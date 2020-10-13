package seedu.duke.storage;

import seedu.duke.Cca;
import seedu.duke.Class;
import seedu.duke.Event;
import seedu.duke.Test;
import seedu.duke.exception.InvalidValueException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StorageManager {
    private static String storage_directory_path;

    private EventListEncoder eventListEncoder;
    private EventListDecoder eventListDecoder;
    private ArrayList<Event> eventList;
    private ArrayList<Event> ccaList;
    private ArrayList<Event> testList;
    private ArrayList<Event> classList;
    private static Logger logger = Logger.getLogger("storage");

    public StorageManager(String directoryPath, String filePath) throws InvalidValueException {
        this.eventListEncoder = new EventListEncoder();
        this.eventListDecoder = new EventListDecoder();
        this.eventList = new ArrayList<>();
        this.ccaList = new ArrayList<>();
        this.testList = new ArrayList<>();
        this.classList = new ArrayList<>();

        assert !directoryPath.equals("");
        assert !filePath.equals("");

        if (!isFilePathValid(filePath)) {
            throw new InvalidValueException();
        }

        storage_directory_path = new File(directoryPath).getAbsolutePath();

        initializeStorageManager(filePath);
    }

    public ArrayList<Event> getEventList() {
        return eventList;
    }

    public ArrayList<Event> getCcaList() {
        return ccaList;
    }

    public ArrayList<Event> getTestList() {
        return testList;
    }

    public ArrayList<Event> getClassList() {
        return classList;
    }

    private void initializeStorageManager(String filePath) {
        File eventFile = new File(storage_directory_path + filePath);
        ArrayList<String> data = new ArrayList<>();
        logger.log(Level.INFO, "Loading storage...");

        try {
            boolean fileCreated = createDataFile(filePath);
            if (!fileCreated) {
                logger.log(Level.INFO, "Data file found, reading data file...");
                Scanner sc = new Scanner(eventFile);
                while (sc.hasNext()) {
                    String dataString = sc.nextLine();
                    data.add(dataString);
                }
                eventList = eventListDecoder.decodeEventList(data);
                separateEventsIntoList(eventList);
                logger.log(Level.INFO, "Load successful");
            } else {
                logger.log(Level.INFO, "Data file not found, initializing data file...");
            }
        } catch (IOException e) {
            System.out.println("There was an error loading your files.");
            logger.log(Level.SEVERE, "Initialization failed");
        }
    }

    private void separateEventsIntoList(ArrayList<Event> events) {
        for (Event event : events) {
            if (event instanceof Cca) {
                ccaList.add(event);
            } else if (event instanceof Class) {
                classList.add(event);
            } else if (event instanceof Test) {
                testList.add(event);
            }
        }
    }

    private boolean isFilePathValid(String filePath) {
        int filePathLength = filePath.length();
        if (!filePath.substring(filePathLength - 4).equals(".txt")) {
            return false;
        }
        return true;
    }

    private boolean createDataFile(String filePath) throws IOException {
        File file = new File(storage_directory_path);
        boolean isDirectoryCreated = file.mkdir();
        file = new File(storage_directory_path + filePath);

        if (isDirectoryCreated) {
            logger.log(Level.INFO, "Directory not found, creating...");
        } else {
            logger.log(Level.INFO, "Directory found...");
        }

        return file.createNewFile();
    }

    public void save(ArrayList<Event> eventList, String filePath) throws IOException {
        ArrayList<String> encodedEventList = eventListEncoder.encodeEventList(eventList);
        Files.write(Path.of(storage_directory_path + filePath), encodedEventList);
    }
}
