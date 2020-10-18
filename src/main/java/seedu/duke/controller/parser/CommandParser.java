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
        case INPUT_HELP:
            commandType = CommandType.HELP;
            break;
        case INPUT_ADD:
            commandType = CommandType.ADD;
            break;
        case INPUT_DELETE:
            commandType = CommandType.DELETE;
            break;
        case INPUT_LIST:
            commandType = CommandType.LIST;
            break;
        case INPUT_BYE:
            commandType = CommandType.BYE;
            break;
        case INPUT_DONE:
            commandType = CommandType.DONE;
            break;
        default:
            throw new InvalidCommandException();
        }

        return commandType;
    }
}
