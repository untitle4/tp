package seedu.duke.storage.event;

import seedu.duke.exception.StorageCorruptedException;
import seedu.duke.exception.StorageSeparatorException;
import seedu.duke.model.event.cca.EventCca;
import seedu.duke.model.event.classlesson.EventClass;
import seedu.duke.model.event.Event;
import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.model.event.test.EventTest;
import seedu.duke.model.event.tuition.EventTuition;
import seedu.duke.storage.StorageManager;
import seedu.duke.ui.UserInterface;
import seedu.duke.model.event.EventParameter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author durianpancakes
public class EventStorageManager extends StorageManager {
    private final EventListEncoder eventListEncoder;
    private final EventListDecoder eventListDecoder;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();
    private final UserInterface userInterface;

    public EventStorageManager(String directory, String fileName) {
        super(directory, fileName);
        this.eventListEncoder = new EventListEncoder();
        this.eventListDecoder = new EventListDecoder();
        userInterface = UserInterface.getInstance();
    }

    public EventStorageManager(String fileName) {
        super(fileName);
        this.eventListEncoder = new EventListEncoder();
        this.eventListDecoder = new EventListDecoder();
        userInterface = UserInterface.getInstance();
    }

    public void saveData(ArrayList<Event> eventList) throws IOException {
        ArrayList<String> encodedEventList = eventListEncoder.encodeEventList(eventList);
        Files.write(Path.of(DIRECTORY_FOLDER_PATH + fileName), encodedEventList);
    }

    public EventParameter loadData() throws StorageCorruptedException {
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
            userInterface.showToUser(Messages.MESSAGE_STORAGE_READ_ERROR);
            logger.log(Level.SEVERE, "Initialization failed");
        } catch (StorageSeparatorException e) {
            logger.log(Level.SEVERE, "Storage corrupted");
            throw new StorageCorruptedException();
        }
        return new EventParameter();
    }

    private EventParameter separateEventsIntoList(ArrayList<Event> events) {
        ArrayList<Event> ccas = new ArrayList<>();
        ArrayList<Event> classes = new ArrayList<>();
        ArrayList<Event> tests = new ArrayList<>();
        ArrayList<Event> tuitions = new ArrayList<>();
        for (Event event : events) {
            if (event instanceof EventCca) {
                ccas.add(event);
            } else if (event instanceof EventTuition) {
                tuitions.add(event);
            } else if (event instanceof EventClass) {
                classes.add(event);
            } else if (event instanceof EventTest) {
                tests.add(event);
            }
        }
        return new EventParameter(ccas, tests, classes, tuitions);
    }
}
