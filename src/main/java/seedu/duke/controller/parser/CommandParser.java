package seedu.duke.controller.parser;

import seedu.duke.LogManager;
import seedu.duke.exception.InvalidCommandException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandParser {
    public static final String INPUT_ADD = "add";
    public static final String INPUT_DELETE = "delete";
    public static final String INPUT_DONE = "done";
    public static final String INPUT_LIST = "list";
    public static final String INPUT_BYE = "bye";
    public static final String INPUT_SCHEDULE_CLASS = "class";
    public static final String INPUT_SCHEDULE_TEST = "test";
    public static final String INPUT_SCHEDULE_CCA = "cca";
    public static final String INPUT_SCHEDULE_TUITION = "tuition";
    public static final String INPUT_HELP = "help";
    public static final String INPUT_QUIZ = "quiz";
    public static final String INPUT_CONTACT = "contact";
    public static final int MAIN_COMMAND_INDEX = 0;
    public static final int SUB_COMMAND_INDEX = 1;

    private final String[] separatedInputs;

    private CommandType commandType;
    private static final Logger logger = LogManager.getLogger();

    public CommandParser(String userInput) {
        separatedInputs = userInput.split(" ");
        commandType = null;
    }

    public CommandType extractCommand() throws InvalidCommandException {
        logger.log(Level.INFO, "Extracting commands now...");
        if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_HELP)) {
            commandType = CommandType.HELP;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_ADD)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_CLASS)) {
            commandType = CommandType.ADD_CLASS;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_ADD)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_TEST)) {
            commandType = CommandType.ADD_TEST;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_ADD)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_CCA)) {
            commandType = CommandType.ADD_CCA;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_ADD)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_CONTACT)) {
            commandType = CommandType.ADD_CONTACT;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_ADD)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_TUITION)) {
            commandType = CommandType.ADD_TUITION;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DELETE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_CLASS)) {
            commandType = CommandType.DELETE_CLASS;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DELETE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_TEST)) {
            commandType = CommandType.DELETE_TEST;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DELETE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_CCA)) {
            commandType = CommandType.DELETE_CCA;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DELETE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_QUIZ)) {
            commandType = CommandType.DELETE_QUIZ;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DELETE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_CONTACT)) {
            commandType = CommandType.DELETE_CONTACT;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_LIST)
                && separatedInputs.length > 1
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_QUIZ)) {
            commandType = CommandType.LIST_QUIZ;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_LIST)
                && separatedInputs.length > 1
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_CONTACT)) {
            commandType = CommandType.LIST_CONTACT;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_LIST)) {
            commandType = CommandType.LIST;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_BYE)) {
            commandType = CommandType.BYE;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DONE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_TEST)) {
            commandType = CommandType.DONE_TEST;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DONE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_CLASS)) {
            commandType = CommandType.DONE_CLASS;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DONE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_CCA)) {
            commandType = CommandType.DONE_CCA;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_ADD)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_QUIZ)) {
            commandType = CommandType.ADD_QUIZ;
        } else {
            throw new InvalidCommandException();
        }

        return commandType;
    }
}
