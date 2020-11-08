package seedu.duke;

import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.exception.StorageCorruptedException;
import seedu.duke.model.ConfigParameter;
import seedu.duke.model.Model;
import seedu.duke.model.contact.ContactManager;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.event.EventParameter;
import seedu.duke.model.quiz.QuizManager;
import seedu.duke.storage.config.ConfigStorageManager;
import seedu.duke.storage.contact.ContactStorageManager;
import seedu.duke.storage.quiz.QuizStorageManager;
import seedu.duke.storage.event.EventStorageManager;
import seedu.duke.ui.ConfigManager;
import seedu.duke.ui.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static final String EVENT_FILE_NAME = "/events.txt";
    public static final String QUIZ_FILE_NAME = "/quiz.txt";
    public static final String CONTACT_FILE_NAME = "/contact.txt";
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
        while(isRunning) {
            try {
                new Main().run();
            } catch (StorageCorruptedException e) {
                userInterface.showToUser(Messages.MESSAGE_STORAGE_CORRUPTED);
                handleCorruptedStorage();
            }
        }
    }

    private static void handleCorruptedStorage() {
        // Valid input is defined to be y or n
        boolean validInput = false;
        boolean resetSuccessful = false;

        userInterface.showToUser(Messages.MESSAGE_FACTORY_RESET_PROMPT,
                Messages.MESSAGE_MANUAL_TROUBLESHOOT_PROMPT);

        while (!validInput) {
            String userInput = userInterface.getUserCommand();

            switch (userInput) {
            case "y":
                resetSuccessful = factoryReset();
                validInput = true;
                break;
            case "n":
                userInterface.showToUser();
                validInput = true;
                isRunning = false;
                break;
            default:
                userInterface.showToUser(Messages.MESSAGE_FACTORY_RESET_INVALID_INPUT_PROMPT);
            }
        }

        if (!resetSuccessful) {
            userInterface.showToUser(Messages.MESSAGE_FACTORY_RESET_FAILED_OR_CANCELLED);
        }
    }

    private static boolean factoryReset() {
        QuizStorageManager quizStorageManager = new QuizStorageManager(QUIZ_FILE_NAME);
        EventStorageManager eventStorageManager = new EventStorageManager(EVENT_FILE_NAME);
        ContactStorageManager contactStorageManager = new ContactStorageManager(CONTACT_FILE_NAME);
        ConfigStorageManager configStorageManager = new ConfigStorageManager(ConfigManager.CONFIG_FILE_NAME);

        try {
            quizStorageManager.saveData(new ArrayList<>(), QUIZ_FILE_NAME);
            eventStorageManager.saveData(new ArrayList<>());
            contactStorageManager.saveData(new ArrayList<>(), CONTACT_FILE_NAME);
            configStorageManager.saveData(new ConfigParameter());

            return true;
        } catch (IOException e) {
            userInterface.showToUser(Messages.MESSAGE_STORAGE_INITIALIZATION_ERROR);

            return false;
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
