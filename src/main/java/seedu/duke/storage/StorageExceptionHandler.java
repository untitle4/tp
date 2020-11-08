package seedu.duke.storage;

import seedu.duke.common.Messages;
import seedu.duke.model.ConfigParameter;
import seedu.duke.storage.config.ConfigStorageManager;
import seedu.duke.storage.contact.ContactStorageManager;
import seedu.duke.storage.event.EventStorageManager;
import seedu.duke.storage.quiz.QuizStorageManager;
import seedu.duke.ui.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

import static seedu.duke.storage.StorageManager.CONFIG_FILE_NAME;
import static seedu.duke.storage.StorageManager.CONTACT_FILE_NAME;
import static seedu.duke.storage.StorageManager.EVENT_FILE_NAME;
import static seedu.duke.storage.StorageManager.QUIZ_FILE_NAME;

/**
 * Class to handle StorageCorruptedException.
 */
public class StorageExceptionHandler {
    private final UserInterface userInterface;

    public StorageExceptionHandler() {
        userInterface = UserInterface.getInstance();
    }

    public void handleCorruptedStorage() {
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
                break;
            default:
                userInterface.showToUser(Messages.MESSAGE_FACTORY_RESET_INVALID_INPUT_PROMPT);
            }
        }

        if (resetSuccessful) {
            userInterface.showToUser(Messages.MESSAGE_FACTORY_RESET_SUCCESSFUL);
        } else {
            userInterface.showToUser(Messages.MESSAGE_FACTORY_RESET_FAILED_OR_CANCELLED);
        }
    }

    private boolean factoryReset() {
        QuizStorageManager quizStorageManager = new QuizStorageManager(QUIZ_FILE_NAME);
        EventStorageManager eventStorageManager = new EventStorageManager(EVENT_FILE_NAME);
        ContactStorageManager contactStorageManager = new ContactStorageManager(CONTACT_FILE_NAME);
        ConfigStorageManager configStorageManager = new ConfigStorageManager(CONFIG_FILE_NAME);

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
}
