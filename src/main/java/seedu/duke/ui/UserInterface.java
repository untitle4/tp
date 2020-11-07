package seedu.duke.ui;

import seedu.duke.common.Messages;
import seedu.duke.controller.ControlManager;
import seedu.duke.controller.parser.CommandType;
import seedu.duke.model.ConfigParameter;
import seedu.duke.model.Model;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.event.ListWeekCommand;
import seedu.duke.storage.contact.ContactStorageManager;
import seedu.duke.storage.event.EventStorageManager;
import seedu.duke.storage.quiz.QuizStorageManager;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

//@@author durianpancakes

/**
 * Singleton UserInterface class containing methods to obtain input from user, and prints output to use
 */
public class UserInterface {
    private static UserInterface userInterface = null;
    private final Scanner in;
    private final PrintStream out;
    public static final String CURSOR = ">> ";

    private UserInterface() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Method to obtain the singleton instance of UserInterface
     *
     * @return UserInterface instance
     */
    public static UserInterface getInstance() {
        if (userInterface == null) {
            userInterface = new UserInterface();
        }

        return userInterface;
    }

    /**
     * Displays the Welcome message to the user
     *
     * @param configParameter ConfigParameter containing the recommended hours to be printed
     */
    public void showWelcomeMessage(ConfigParameter configParameter) {
        showToUser(Messages.MESSAGE_HELLO + configParameter.getName(),
                Messages.MESSAGE_SHOW_HOURS + configParameter.getRecommendedHours());
        out.print(Messages.MESSAGE_PROMPT_COMMAND);
    }

    /**
     * Obtains the user's command
     *
     * @return String containing the user's command
     */
    public String getUserCommand() {
        // Adds an additional row space for better readability
        showEmptyLine();
        // Indicator for user input:
        out.print(CURSOR);

        return in.nextLine();
    }

    /**
     * Helper function to show an empty line
     */
    private void showEmptyLine() {
        showToUser("");
    }

    /**
     * Method to display multiple Strings.
     *
     * @param message String containing message to be printed. Can be separated by "," to be printed on another
     *                line
     */
    public void showToUser(String... message) {
        for (String m : message) {
            out.println(m);
        }
    }

    //@@author AndreWongZH
    /**
     * Prints to user through an array list of strings.
     * This is invoked from either list or find command.
     *
     * @param stringArrayList ArrayList of type string to be printed.
     */
    public void printArray(ArrayList<String> stringArrayList) {
        assert stringArrayList != null;
        for (String line : stringArrayList) {
            showToUser(line);
        }
    }

    //@@author AndreWongZH
    /**
     * Compares if command type is equal to BYE and returns true if it does.
     *
     * @param commandType A CommandType of one of the commands.
     * @return A boolean to indicate if program loop should end.
     */
    public boolean checkIfProgramEnds(CommandType commandType) {
        return commandType != CommandType.BYE;
    }

    //@@author AndreWongZH
    /**
     * Passes user input into the control manager for it to run its logic.
     *
     * @param model Data stored in the program.
     * @param eventStorageManager Updates the storage data in event.txt after command is ran.
     * @param quizStorageManager Updates the storage data in quiz.txt after command is ran.
     * @return A boolean to tell the program to quit or not.
     */
    public boolean runUI(Model model,
                         EventStorageManager eventStorageManager,
                         QuizStorageManager quizStorageManager,
                         ContactStorageManager contactStorageManager) {
        CommandType commandType = null;
        String line = getUserCommand();

        if (!line.trim().isEmpty()) {
            ControlManager controlManager = new ControlManager(line, model,
                    eventStorageManager, quizStorageManager, contactStorageManager);
            commandType = controlManager.runLogic();
        }

        return checkIfProgramEnds(commandType);
    }

    //@@author durianpancakes

    /**
     * Method to print the week schedule of the user
     * @param eventManager EventManager instance for CalendarWeekRenderer to access helper methods
     * @param listWeekCommand ListWeekCommand to identify the week to be printed. CURRENT_WEEK for this week,
     *                        NEXT_WEEK for the next week
     */
    public void printWeekSchedule(EventManager eventManager, ListWeekCommand listWeekCommand) {
        new CalendarWeekRenderer(eventManager, listWeekCommand);
    }
}
