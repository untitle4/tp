package seedu.duke.storage.contact;

import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.model.contact.Contact;
import seedu.duke.storage.StorageManager;
import seedu.duke.ui.UserInterface;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author AndreWongZH
/**
 * Help manage the storage of contacts into a text file and load contacts from the file
 * when the program is executed.
 */
public class ContactStorageManager extends StorageManager {
    private final ContactListEncoder contactListEncoder;
    private final ContactListDecoder contactListDecoder;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();
    private final UserInterface userInterface;

    public ContactStorageManager(String fileName) {
        super(fileName);
        this.contactListEncoder = new ContactListEncoder();
        this.contactListDecoder = new ContactListDecoder();
        this.userInterface = UserInterface.getInstance();
    }

    /**
     * Load the content of the contact text file.
     *
     * @return An array list of type Contact.
     */
    public ArrayList<Contact> loadData() {
        File contactFile = new File(DIRECTORY_FOLDER_PATH + fileName);
        ArrayList<String> data = new ArrayList<>();
        logger.log(Level.INFO, "Loading storage...");

        try {
            boolean fileCreated = createDataFile();
            if (!fileCreated) {
                logger.log(Level.INFO, "Data file found, reading data file...");
                Scanner sc = new Scanner(contactFile);
                while (sc.hasNext()) {
                    String dataString = sc.nextLine();
                    data.add(dataString);
                }
                logger.log(Level.INFO, "Load successful");
                return contactListDecoder.decodeContactList(data);
            } else {
                logger.log(Level.INFO, "Data file not found, initializing data file...");
            }
        } catch (IOException e) {
            userInterface.showToUser(Messages.MESSAGE_STORAGE_READ_ERROR);
            logger.log(Level.SEVERE, "Initialization failed");
        }
        return new ArrayList<>();
    }

    public void saveData(ArrayList<Contact> contactList, String filePath) throws IOException {
        ArrayList<String> encodedContactList = contactListEncoder.encodeContactList(contactList);
        Files.write(Path.of(DIRECTORY_FOLDER_PATH + filePath), encodedContactList);
    }
}
