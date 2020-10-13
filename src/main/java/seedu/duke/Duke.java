package seedu.duke;

import seedu.duke.event.EventManager;
import seedu.duke.exception.InvalidValueException;
import seedu.duke.parser.CommandParser;
import seedu.duke.parser.CommandType;
import seedu.duke.storage.StorageManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String DATA_STRING = "data";
    public static final String FILE_STRING = "/events.txt";

    private static StorageManager storageManager;
    private static EventManager eventManager;
    private static boolean active = true;

    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static void main(String[] args) throws InvalidValueException {
        storageManager = new StorageManager(DATA_STRING, FILE_STRING);
        eventManager = new EventManager(storageManager);

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
            CommandType commandType = new CommandParser(line, eventManager).parseCommand();
            checkIfProgramEnds(commandType);
            refreshEvents();
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
}
