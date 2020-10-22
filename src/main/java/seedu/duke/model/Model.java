package seedu.duke.model;

import seedu.duke.model.contact.ContactManager;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.quiz.QuizManager;

public class Model {
    EventManager eventManager;
    ContactManager contactManager;
    QuizManager quizManager;

    public Model(EventManager eventManager, ContactManager contactManager, QuizManager quizManager) {
        this.eventManager = eventManager;
        this.contactManager = contactManager;
        this.quizManager = quizManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public ContactManager getContactManager() {
        return contactManager;
    }

    public QuizManager getQuizManager() { return quizManager; }
}
