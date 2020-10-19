package seedu.duke;

import seedu.duke.common.Messages;
import seedu.duke.model.Model;
import seedu.duke.model.contact.ContactManager;
import seedu.duke.controller.ControlManager;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.event.EventParameter;
import seedu.duke.controller.parser.CommandType;
import seedu.duke.model.quiz.Quiz;
import seedu.duke.model.quiz.QuizManager;
import seedu.duke.storage.QuizStorageManager;
import seedu.duke.storage.EventStorageManager;
import seedu.duke.ui.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    public static final String EVENT_FILE_NAME = "/events.txt";
    public static final String QUIZ_FILE_NAME = "/quiz.txt";

    private final EventStorageManager eventStorageManager;
    private final QuizStorageManager quizStorageManager;
    private final UserInterface userInterface;
    private final Model model;

    private boolean active;

    public Duke() {
        eventStorageManager = new EventStorageManager(EVENT_FILE_NAME);
        quizStorageManager = new QuizStorageManager(QUIZ_FILE_NAME);
        EventParameter eventParameter = eventStorageManager.loadData();
        EventManager eventManager = new EventManager(eventParameter);
        QuizManager quizManager = new QuizManager(quizStorageManager.loadData());
        ContactManager contactManager = new ContactManager();
        userInterface = UserInterface.getInstance();
        active = true;
        model = new Model(eventManager, contactManager, quizManager);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        userInterface.showWelcomeMessage();

        while (active) {
            String line = userInterface.getUserCommand();
            if (!line.trim().isEmpty()) {
                ControlManager controlManager = new ControlManager(line, model);
                CommandType commandType = controlManager.runLogic();
                checkIfProgramEnds(commandType);
            }
            refreshEvents();
            refreshQuizzes();
        }

        // Exit Message
        userInterface.showToUser(Messages.MESSAGE_BYE);
    }

    private void checkIfProgramEnds(CommandType commandType) {
        if (commandType == CommandType.BYE) {
            active = false;
        }
    }

    private void refreshEvents() {
        ArrayList<Event> events = new ArrayList<>();

        events.addAll(model.getEventManager().getCcaManager().getCcaList());
        events.addAll(model.getEventManager().getTestManager().getTestList());
        events.addAll(model.getEventManager().getClassManager().getClasses());
        events.addAll(model.getEventManager().getTuitionManager().getTuitions());

        try {
            eventStorageManager.saveData(events);
        } catch (IOException e) {
            userInterface.showToUser(Messages.MESSAGE_STORAGE_INITIALIZATION_ERROR);
        }
    }

    private void refreshQuizzes() {
        ArrayList<Quiz> quizzes = model.getQuizManager().getQuizList();

        try {
            quizStorageManager.saveData(quizzes, QUIZ_FILE_NAME);
        } catch (IOException e) {
            userInterface.showToUser(Messages.MESSAGE_STORAGE_INITIALIZATION_ERROR);
        }
    }
}
