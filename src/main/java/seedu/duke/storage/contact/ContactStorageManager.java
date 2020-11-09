package seedu.duke.storage.contact;

import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.exception.StorageCorruptedException;
import seedu.duke.exception.StorageSeparatorException;
import seedu.duke.model.contact.Contact;
import seedu.duke.storage.StorageManager;
import seedu.duke.ui.UserInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author AndreWongZH
/**
 * Helps to manage the storage of contacts into a text file and load contacts from the file
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
     * Loads the content of the contact text file.
     *
     * @return An array list of type Contact.
     */
    public ArrayList<Contact> loadData() throws StorageCorruptedException {
        ArrayList<String> data;
        logger.log(Level.INFO, "Loading storage...");

        try {
            boolean fileCreated = createDataFile();
            if (!fileCreated) {
                logger.log(Level.INFO, "Data file found, reading data file...");
                data = getDataFromFile();
                logger.log(Level.INFO, "Load successful");
                return contactListDecoder.decodeContactList(data);
            }
        } catch (IOException e) {
            userInterface.showToUser(Messages.MESSAGE_STORAGE_READ_ERROR);
            logger.log(Level.SEVERE, "Initialization failed");
        } catch (StorageSeparatorException e) {
            logger.log(Level.SEVERE, "Contact Storage corrupted");
            throw new StorageCorruptedException();
        }
        logger.log(Level.INFO, "Data file not found, initializing data file...");
        return new ArrayList<>();
    }

    /**
     * Reads data from contact.txt line by line and saves to an array list.
     * Returns the arrayList.
     *
     * @return An array list of contents in the contact.txt file.
     * @throws FileNotFoundException If file is not found.
     */
    private ArrayList<String> getDataFromFile() throws FileNotFoundException {
        ArrayList<String> data = new ArrayList<>();
        File contactFile = new File(DIRECTORY_FOLDER_PATH + fileName);
        Scanner sc = new Scanner(contactFile);

        while (sc.hasNext()) {
            String dataString = sc.nextLine();
            data.add(dataString);
        }

        return data;
    }

    /**
     * Saves the array list of contacts to a text file after every command.
     *
     * @param contactList Arraylist of type contact to be saved to txt file.
     * @param filePath Name of the txt file.
     * @throws IOException If file writer fails to write to txt file.
     */
    public void saveData(ArrayList<Contact> contactList, String filePath) throws IOException {
        ArrayList<String> encodedContactList = contactListEncoder.encodeContactList(contactList);
        Files.write(Path.of(DIRECTORY_FOLDER_PATH + filePath), encodedContactList);
    }
}
