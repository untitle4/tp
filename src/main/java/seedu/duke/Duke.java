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
    private static boolean active = true;

    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static void main(String[] args) {
        eventStorageManager = new EventStorageManager(EVENT_FILE_NAME);
        quizStorageManager = new QuizStorageManager(QUIZ_FILE_NAME);
        EventParameter eventParameter = eventStorageManager.loadData();
        eventManager = new EventManager(eventParameter);
        quizManager = new QuizManager(quizStorageManager.loadData());
        contactManager = new ContactManager();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
        System.out.println("What can we do for you?");

        while (active) {
            String line = in.nextLine();
            if (!line.trim().isEmpty()) {
                CommandType commandType = new CommandParser(line, eventManager,
                        quizManager, contactManager).parseCommand();
                checkIfProgramEnds(commandType);
            }
            refreshEvents();
            refreshQuizzes();
        }

        //Exit Message
        System.out.println("BYE BYE! SEE YOU NEXT TIME! :3");
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

        try {
            eventStorageManager.saveData(events);
        } catch (IOException e) {
            System.out.println("STORAGE: There was an error");
        }
    }

    private static void refreshQuizzes() {

        ArrayList<Quiz> quizzes = quizManager.getQuizList();

        try {
            quizStorageManager.saveData(quizzes, QUIZ_FILE_NAME);
        } catch (IOException e) {
            System.out.println("STORAGE: There was an error");
        }
    }
}
