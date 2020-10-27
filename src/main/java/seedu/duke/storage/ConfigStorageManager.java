package seedu.duke.storage;

import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.exception.StorageCorruptedException;
import seedu.duke.exception.StorageSeparatorException;
import seedu.duke.model.ConfigParameter;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.EventParameter;
import seedu.duke.ui.UserInterface;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigStorageManager extends StorageManager {
    private final ConfigEncoder configEncoder;
    private final ConfigDecoder configDecoder;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();
    private final UserInterface userInterface;

    public ConfigStorageManager(String fileName) {
        super(fileName);
        this.configEncoder = new ConfigEncoder();
        this.configDecoder = new ConfigDecoder();
        userInterface = UserInterface.getInstance();
    }

    public void saveData(ConfigParameter configParameter) throws IOException {
        String encodedConfig = configEncoder.encodeConfig(configParameter);
        Files.write(Path.of(DIRECTORY_FOLDER_PATH + fileName), Collections.singleton(encodedConfig));
    }

    public ConfigParameter loadData() throws StorageCorruptedException {
        File eventFile = new File(DIRECTORY_FOLDER_PATH + fileName);
        logger.log(Level.INFO, "Loading storage...");

        try {
            boolean fileCreated = createDataFile();
            if (!fileCreated) {
                logger.log(Level.INFO, "Data file found, reading data file...");
                Scanner sc = new Scanner(eventFile);
                String dataString = sc.nextLine();
                ConfigParameter configParameter = configDecoder.decodeConfig(dataString);
                logger.log(Level.INFO, "Load successful");
                return configParameter;
            } else {
                logger.log(Level.INFO, "Data file not found, initializing data file...");
            }
        } catch (IOException e) {
            userInterface.showToUser(Messages.MESSAGE_STORAGE_READ_ERROR);
            logger.log(Level.SEVERE, "Initialization failed");
        }
        return new ConfigParameter();
    }
}
