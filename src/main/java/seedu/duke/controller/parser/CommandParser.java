package seedu.duke.controller.parser;

import seedu.duke.common.LogManager;
import seedu.duke.exception.InvalidCommandException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandParser {
    public static final String INPUT_ADD = "add";
    public static final String INPUT_DELETE = "delete";
    public static final String INPUT_DONE = "done";
    public static final String INPUT_LIST = "list";
    public static final String INPUT_BYE = "bye";
    public static final String INPUT_HELP = "help";
    public static final String INPUT_FIND = "find";
    public static final String INPUT_QUIZ = "quiz";
    public static final int MAIN_COMMAND_INDEX = 0;

    private final String[] separatedInputs;

    private CommandType commandType;
    private static final Logger logger = LogManager.getLoggerInstance().getLogger();

    public CommandParser(String userInput) {
        separatedInputs = userInput.split(" ");
        commandType = null;
    }

    public CommandType extractCommand() throws InvalidCommandException {
        logger.log(Level.INFO, "Extracting command now...");

        switch (separatedInputs[MAIN_COMMAND_INDEX]) {
        case INPUT_ADD:
            if (separatedInputs.length == 1) {
                throw new InvalidCommandException();
            }
            commandType = CommandType.ADD;
            break;
        case INPUT_DELETE:
            if (separatedInputs.length == 1) {
                throw new InvalidCommandException();
            }
            commandType = CommandType.DELETE;
            break;
        case INPUT_DONE:
            if (separatedInputs.length == 1) {
                throw new InvalidCommandException();
            }
            commandType = CommandType.DONE;
            break;
        case INPUT_LIST:
            if (separatedInputs.length != 2) {
                throw new InvalidCommandException();
            }
            commandType = CommandType.LIST;
            break;
        case INPUT_BYE:
            commandType = CommandType.BYE;
            break;
        case INPUT_HELP:
            commandType = CommandType.HELP;
            break;
        case INPUT_QUIZ:
            commandType = CommandType.QUIZ;
            break;
        default:
            throw new InvalidCommandException();
        }

        return commandType;
    }
}
