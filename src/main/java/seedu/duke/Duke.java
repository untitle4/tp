package seedu.duke;

import seedu.duke.common.Messages;
import seedu.duke.exception.StorageCorruptedException;
import seedu.duke.model.Model;
import seedu.duke.model.contact.ContactManager;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.event.EventParameter;
import seedu.duke.model.quiz.QuizManager;
import seedu.duke.storage.contact.ContactStorageManager;
import seedu.duke.storage.quiz.QuizStorageManager;
import seedu.duke.storage.event.EventStorageManager;
import seedu.duke.ui.ConfigManager;
import seedu.duke.ui.UserInterface;

public class Duke {
    public static final String EVENT_FILE_NAME = "/events.txt";
    public static final String QUIZ_FILE_NAME = "/quiz.txt";
    public static final String CONTACT_FILE_NAME = "/contact.txt";

    private static UserInterface userInterface;

    private EventStorageManager eventStorageManager;
    private QuizStorageManager quizStorageManager;
    private ContactStorageManager contactStorageManager;
    private ConfigManager configManager;
    private Model model;

    private boolean active;

    public Duke() throws StorageCorruptedException {
        userInterface = UserInterface.getInstance();
        initializeStorageManagers();
        active = true;
        loadModel();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (StorageCorruptedException e) {
            userInterface.showToUser(Messages.MESSAGE_STORAGE_CORRUPTED);
        }
    }

    public void run() {
        userInterface.showToUser(Messages.MESSAGE_HELLO_FROM_DUKE);
        configManager.getIntroductoryVariables(configManager.getConfigParameter());
        userInterface.showWelcomeMessage(configManager.getConfigParameter());

        while (active) {
            active = userInterface.runUI(model, eventStorageManager, quizStorageManager, contactStorageManager);
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
        EventManager eventManager = new EventManager(eventParameter, configManager.getConfigParameter());
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
