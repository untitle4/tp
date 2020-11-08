package seedu.duke;

import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.exception.StorageCorruptedException;
import seedu.duke.model.Model;
import seedu.duke.model.contact.ContactManager;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.event.EventParameter;
import seedu.duke.model.quiz.QuizManager;
import seedu.duke.storage.StorageExceptionHandler;
import seedu.duke.storage.contact.ContactStorageManager;
import seedu.duke.storage.quiz.QuizStorageManager;
import seedu.duke.storage.event.EventStorageManager;
import seedu.duke.model.config.ConfigManager;
import seedu.duke.ui.UserInterface;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.storage.StorageManager.CONTACT_FILE_NAME;
import static seedu.duke.storage.StorageManager.EVENT_FILE_NAME;
import static seedu.duke.storage.StorageManager.QUIZ_FILE_NAME;

public class Main {
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();

    private static UserInterface userInterface;
    private static boolean isRunning = true;

    private EventStorageManager eventStorageManager;
    private QuizStorageManager quizStorageManager;
    private ContactStorageManager contactStorageManager;
    private ConfigManager configManager;
    private Model model;

    private boolean isActive;

    public Main() throws StorageCorruptedException {
        userInterface = UserInterface.getInstance();
        initializeStorageManagers();
        isActive = true;
        loadModel();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        while (isRunning) {
            try {
                new Main().run();
            } catch (StorageCorruptedException e) {
                userInterface.showToUser(Messages.MESSAGE_STORAGE_CORRUPTED);
                StorageExceptionHandler storageExceptionHandler = new StorageExceptionHandler();
                storageExceptionHandler.handleCorruptedStorage();
            }
        }
    }

    public void run() throws StorageCorruptedException {
        try {
            userInterface.showToUser(Messages.MESSAGE_HELLO_FROM_DUKE);
            configManager.getIntroductoryVariables(configManager.getConfig());
            userInterface.showWelcomeMessage(configManager.getConfig());

            while (isActive) {
                isActive = userInterface.runUi(model, eventStorageManager, quizStorageManager, contactStorageManager);
                isRunning = isActive;
            }
        } catch (NoSuchElementException e) {
            logger.log(Level.SEVERE, "Program ended unexpectedly");
        }

        // Exit Message
        userInterface.showToUser(Messages.MESSAGE_BYE);
    }

    //@@author AndreWongZH
    /**
     * Sets up the eventManager, quizManager, contactManager and configManager to be saved into model.
     *
     * @throws StorageCorruptedException If unable to load data correctly.
     */
    private void loadModel() throws StorageCorruptedException {
        ContactManager contactManager = new ContactManager(contactStorageManager.loadData());
        QuizManager quizManager = new QuizManager(quizStorageManager.loadData());
        EventParameter eventParameter = eventStorageManager.loadData();
        EventManager eventManager = new EventManager(eventParameter, configManager.getConfig());
        model = new Model(eventManager, contactManager, quizManager, configManager);
    }

    //@@author AndreWongZH
    /**
     * Sets up storage managers to get data from text file storage.
     */
    private void initializeStorageManagers() {
        eventStorageManager = new EventStorageManager(EVENT_FILE_NAME);
        quizStorageManager = new QuizStorageManager(QUIZ_FILE_NAME);
        contactStorageManager = new ContactStorageManager(CONTACT_FILE_NAME);
        configManager = ConfigManager.getInstance();
    }
}
