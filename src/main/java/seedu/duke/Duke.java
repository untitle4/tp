package seedu.duke;

import seedu.duke.common.Messages;
import seedu.duke.exception.StorageCorruptedException;
import seedu.duke.model.ConfigParameter;
import seedu.duke.model.Model;
import seedu.duke.model.contact.ContactManager;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.event.EventParameter;
import seedu.duke.model.quiz.QuizManager;
import seedu.duke.storage.ConfigStorageManager;
import seedu.duke.storage.QuizStorageManager;
import seedu.duke.storage.EventStorageManager;
import seedu.duke.ui.ConfigManager;
import seedu.duke.ui.UserInterface;

import java.io.IOException;

public class Duke {
    public static final String EVENT_FILE_NAME = "/events.txt";
    public static final String QUIZ_FILE_NAME = "/quiz.txt";
    public static final String CONFIG_FILE_NAME = "/config.txt";

    private final EventStorageManager eventStorageManager;
    private final QuizStorageManager quizStorageManager;
    //private final ConfigStorageManager configStorageManager;
    //private final ConfigParameter configParameter;
    private final ConfigManager configManager;
    private static UserInterface userInterface;
    private final Model model;

    private boolean active;

    public Duke() throws StorageCorruptedException {
        userInterface = UserInterface.getInstance();
        eventStorageManager = new EventStorageManager(EVENT_FILE_NAME);
        quizStorageManager = new QuizStorageManager(QUIZ_FILE_NAME);
        configManager = ConfigManager.getInstance();
        active = true;
        ContactManager contactManager = new ContactManager();
        QuizManager quizManager = new QuizManager(quizStorageManager.loadData());
        EventParameter eventParameter = eventStorageManager.loadData();
        EventManager eventManager = new EventManager(eventParameter, configManager.getConfigParameter());
        model = new Model(eventManager, contactManager, quizManager, configManager);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (StorageCorruptedException e) {
            userInterface.showToUser(Messages.MESSAGE_STORAGE_CORRUPTED);
        } catch (IOException e) {
            userInterface.showToUser(Messages.MESSAGE_STORAGE_INITIALIZATION_ERROR);
        }
    }

    public void run() throws IOException {
        configManager.getIntroductoryVariables(configManager.getConfigParameter());
        userInterface.showWelcomeMessage(configManager.getConfigParameter());

        while (active) {
            active = userInterface.runUI(model, eventStorageManager, quizStorageManager);
        }

        // Exit Message
        userInterface.showToUser(Messages.MESSAGE_BYE);
    }
}
