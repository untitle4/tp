package seedu.duke;

import seedu.duke.event.EventManager;
import seedu.duke.exception.InvalidValueException;
import seedu.duke.parser.CommandParser;
import seedu.duke.parser.CommandType;
import seedu.duke.quiz.Quiz;
import seedu.duke.quiz.QuizManager;
import seedu.duke.storage.QuizStorageManager;
import seedu.duke.storage.StorageManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String DATA_STRING = "data";
    public static final String FILE_STRING = "/events.txt";
    public static final String QUIZ_FILE_STRING = "/quizzes.txt";

    private static StorageManager storageManager;
    private static QuizStorageManager quizStorageManager;
    private static EventManager eventManager;
    private static QuizManager quizManager;
    private static boolean active = true;

    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static void main(String[] args) throws InvalidValueException {
        storageManager = new StorageManager(DATA_STRING, FILE_STRING);
        quizStorageManager = new QuizStorageManager(DATA_STRING, QUIZ_FILE_STRING);
        eventManager = new EventManager(storageManager);
        quizManager = new QuizManager(quizStorageManager);

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
                CommandType commandType = new CommandParser(line, eventManager, quizManager).parseCommand();
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
            storageManager.save(events, FILE_STRING);
        } catch (IOException e) {
            System.out.println("STORAGE: There was an error");
        }
    }

    private static void refreshQuizzes() {

        ArrayList<Quiz> quizzes = new ArrayList<>();

        quizzes.addAll(quizManager.getQuizList());

        try {
            quizStorageManager.save(quizzes, QUIZ_FILE_STRING);
        } catch (IOException e) {
            System.out.println("STORAGE: There was an error");
        }
    }
}
