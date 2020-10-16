package seedu.duke.storage;

import seedu.duke.*;
import seedu.duke.Class;
import seedu.duke.event.EventParameter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventStorageManager extends StorageManager {
    private final EventListEncoder eventListEncoder;
    private final EventListDecoder eventListDecoder;
    private static final Logger logger = Logger.getLogger("storage");

    public EventStorageManager(String fileName) {
        super(fileName);
        this.eventListEncoder = new EventListEncoder();
        this.eventListDecoder = new EventListDecoder();
    }

    public void saveData(ArrayList<Event> eventList) throws IOException {
        ArrayList<String> encodedEventList = eventListEncoder.encodeEventList(eventList);
        Files.write(Path.of(DIRECTORY_FOLDER_PATH + fileName), encodedEventList);
    }

    public EventParameter loadData() {
        File eventFile = new File(DIRECTORY_FOLDER_PATH + fileName);
        ArrayList<String> data = new ArrayList<>();
        logger.log(Level.INFO, "Loading storage...");

        try {
            boolean fileCreated = createDataFile();
            if (!fileCreated) {
                logger.log(Level.INFO, "Data file found, reading data file...");
                Scanner sc = new Scanner(eventFile);
                while (sc.hasNext()) {
                    String dataString = sc.nextLine();
                    data.add(dataString);
                }
                ArrayList<Event> eventList = eventListDecoder.decodeEventList(data);
                logger.log(Level.INFO, "Load successful");
                return separateEventsIntoList(eventList);
            } else {
                logger.log(Level.INFO, "Data file not found, initializing data file...");
            }
        } catch (IOException e) {
            System.out.println("There was an error loading your files.");
            logger.log(Level.SEVERE, "Initialization failed");
        }
        return new EventParameter();
    }

    private EventParameter separateEventsIntoList(ArrayList<Event> events) {
        ArrayList<Event> ccas = new ArrayList<>();
        ArrayList<Event> classes = new ArrayList<>();
        ArrayList<Event> tests = new ArrayList<>();
        ArrayList<Event> tuitions = new ArrayList<>();
        for (Event event : events) {
            if (event instanceof Cca) {
                ccas.add(event);
            } else if (event instanceof Tuition) {
                tuitions.add(event);
            } else if (event instanceof Class) {
                classes.add(event);
            } else if (event instanceof Test) {
                tests.add(event);
            }
        }
        return new EventParameter(ccas, tests, classes, tuitions);
    }
}
