package seedu.duke;

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
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            line = input.nextLine();

            String[] checkCommand = line.split(" ");
            if (checkCommand[0].equals("help")) {
                try {
                    handleHelp(checkCommand);
                } catch (InvalidHelpCommandException e) {
                    System.out.println("Oops! If you're trying to ask for help, simply enter 'help'!\n");
                }
            } else if (checkCommand[0].equals("add") && checkCommand[1].equals("class")) {
                classManager.addClass(line);
            } else if (checkCommand[0].equals("add") && checkCommand[1].equals("test")) {
                testManager.addTest(line);
            } else if (checkCommand[0].equals("add") && checkCommand[1].equals("cca")) {
                ccaManager.addCca(line);
            } else if (checkCommand[0].equals("delete") && checkCommand[1].equals("class")) {
                classManager.deleteClass(checkCommand);
            } else if (checkCommand[0].equals("delete") && checkCommand[1].equals("test")) {
                testManager.deleteTest(checkCommand);
            } else if (checkCommand[0].equals("delete") && checkCommand[1].equals("cca")) {
                ccaManager.deleteCca(checkCommand);
            } else if (checkCommand[0].equals("list")) {
                listSchedule.printAllEvents();
            } else if (checkCommand[0].equals("bye")) {
                break;
            }
            refreshEvents();
        }

        //Exit Message
        System.out.println("BYE BYE! SEE YOU NEXT TIME! :3");
    }

    public static void handleHelp(String[] checkCommand) throws InvalidHelpCommandException {
        if (checkCommand.length == 1) {
            System.out.println("Hello! Here is a list of commands you can try:\n\n"
                    + "\t1. Add class: add class /n [name of class] /s [start date-time of class] /e"
                    + " [end date-time of class]\n"
                    + "\t2. Delete class: delete class /n [class number]\n"
                    + "\t3. Add cca: add cca /n [name of cca] /s [start date-time of cca] /e [end date-time of cca]\n"
                    + "\t4. Delete cca: type delete cca /n [cca number]\n"
                    + "\t5. Add test: type add test /n [name of test] /s [start date-time of test] /e "
                    + "[end date-time of test]\n"
                    + "\t6. Delete test: type delete test /n [test number]\n"
                    + "\t7. Delete all: delete all\n");
        // "\n\tPlease enter the date-time in the following format: YYYY-MM-DD [time in 24hr format]\n" +
        // "\te.g. 2020-08-19 1300\n\n);
        } else {
            throw new InvalidHelpCommandException();
        }
    }

    public static class InvalidHelpCommandException extends Exception {
    }

    private static void refreshEvents () {
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
