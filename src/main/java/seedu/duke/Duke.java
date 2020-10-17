package seedu.duke;

import seedu.duke.contact.ContactManager;
import seedu.duke.event.EventManager;
import seedu.duke.event.EventParameter;
import seedu.duke.parser.CommandParser;
import seedu.duke.parser.CommandType;
import seedu.duke.quiz.Quiz;
import seedu.duke.quiz.QuizManager;
import seedu.duke.storage.QuizStorageManager;
import seedu.duke.storage.EventStorageManager;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String EVENT_FILE_NAME = "/events.txt";
    public static final String QUIZ_FILE_NAME = "/quiz.txt";

    private static EventStorageManager eventStorageManager;
    private static QuizStorageManager quizStorageManager;
    private static EventManager eventManager;
    private static QuizManager quizManager;
    private static ContactManager contactManager;
    private static UserInterface userInterface;
    private static boolean active = true;

    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static void main(String[] args) {
        new LogManager();
        eventStorageManager = new EventStorageManager(EVENT_FILE_NAME);
        quizStorageManager = new QuizStorageManager(QUIZ_FILE_NAME);
        EventParameter eventParameter = eventStorageManager.loadData();
        eventManager = new EventManager(eventParameter);
        quizManager = new QuizManager(quizStorageManager.loadData());
        contactManager = new ContactManager();
        userInterface = UserInterface.getInstance();

        userInterface.showWelcomeMessage();

        while (active) {
            String line = userInterface.getUserCommand();
            if (!line.trim().isEmpty()) {
                CommandType commandType = new CommandParser(line, eventManager,
                        quizManager, contactManager).parseCommand();
                checkIfProgramEnds(commandType);
            }
            refreshEvents();
            refreshQuizzes();
        }

        // Exit Message
        userInterface.showToUser(Messages.MESSAGE_BYE);
    }

    private static void checkIfProgramEnds(CommandType commandType) {
        if (commandType == CommandType.BYE) {
            active = false;
        }
    }

    private static void refreshEvents() {
        ArrayList<Event> events = new ArrayList<>();

        events.addAll(eventManager.getCcaManager().getCcaList());
        events.addAll(eventManager.getTestManager().getTestList());
        events.addAll(eventManager.getClassManager().getClasses());
        events.addAll(eventManager.getTuitionManager().getTuitions());

        try {
            eventStorageManager.saveData(events);
        } catch (IOException e) {
            userInterface.showToUser(Messages.MESSAGE_STORAGE_INITIALIZATION_ERROR);
        }
    }

    private static void refreshQuizzes() {
        ArrayList<Quiz> quizzes = quizManager.getQuizList();

        try {
            quizStorageManager.saveData(quizzes, QUIZ_FILE_NAME);
        } catch (IOException e) {
            userInterface.showToUser(Messages.MESSAGE_STORAGE_INITIALIZATION_ERROR);
        }
    }
}
