package seedu.duke.model;

import seedu.duke.model.contact.ContactManager;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.quiz.QuizManager;
import seedu.duke.ui.ConfigManager;

//@@author AndreWongZH
/**
 * Represents a model object that stores volatile memory of the program data.
 * This consist of the eventManager, contactManager and quizManager.
 */
public class Model {
    EventManager eventManager;
    ContactManager contactManager;
    QuizManager quizManager;
    ConfigManager configManager;

    public Model(EventManager eventManager, ContactManager contactManager,
                 QuizManager quizManager, ConfigManager configManager) {
        this.eventManager = eventManager;
        this.contactManager = contactManager;
        this.quizManager = quizManager;
        this.configManager = configManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public ContactManager getContactManager() {
        return contactManager;
    }

    public QuizManager getQuizManager() {
        return quizManager;
    }

    public ConfigManager getConfigManager() {return configManager;}
}
