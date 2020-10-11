package seedu.duke;

import seedu.duke.parser.CommandParser;
import seedu.duke.parser.CommandType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Event> classes;
    static ArrayList<Event> tests;
    static ArrayList<Event> ccas;
    private static ClassManager classManager;
    private static TestManager testManager;
    private static CcaManager ccaManager;
    private static ListSchedule listSchedule;
    private static StorageManager storageManager;

    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static void main(String[] args) {
        storageManager = new StorageManager();

        // Initializing ArrayLists
        classes = storageManager.getClassList();
        tests = storageManager.getTestList();
        ccas = storageManager.getCcaList();
        classManager = new ClassManager(storageManager.getClassList());
        testManager = new TestManager(storageManager.getTestList());
        ccaManager = new CcaManager(storageManager.getCcaList());
        listSchedule = new ListSchedule(classes, ccas, tests);


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
        String line;
        CommandType commandType = null;
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine() && commandType != CommandType.BYE) {
            line = input.nextLine();
            commandType = new CommandParser(line, testManager, ccaManager, classManager, listSchedule)
                    .parseCommand();
            refreshEvents();
        }

        //Exit Message
        System.out.println("BYE BYE! SEE YOU NEXT TIME! :3");
    }

    public static class InvalidHelpCommandException extends Exception {
    }

    private static void refreshEvents() {
        ArrayList<Event> events = new ArrayList<>();

        events.addAll(ccaManager.getCcaList());
        events.addAll(testManager.getTestList());
        events.addAll(classManager.getClasses());

        try {
            storageManager.save(events);
        } catch (IOException e) {
            System.out.println("STORAGE: There was an error");
        }
    }
}
