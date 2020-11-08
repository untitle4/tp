package seedu.duke.controller.parser;

import seedu.duke.common.LogManager;
import seedu.duke.exception.ExtraParameterException;
import seedu.duke.exception.InvalidCommandException;
import seedu.duke.exception.MissingModelException;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author AndreWongZH
/**
 * Represents the process of extracting out the commands.
 */
public class CommandParser {
    public static final String INPUT_ADD = "add";
    public static final String INPUT_DELETE = "delete";
    public static final String INPUT_DONE = "done";
    public static final String INPUT_LIST = "list";
    public static final String INPUT_BYE = "bye";
    public static final String INPUT_HELP = "help";
    public static final String INPUT_FIND = "find";
    public static final String INPUT_SPACES = " ";
    public static final int LENGTH_SINGLE_WORD = 1;
    public static final String INPUT_QUIZ = "quiz";
    public static final int MAIN_COMMAND_INDEX = 0;
    public static final String INPUT_SET = "set";

    private final String[] separatedInputs;

    private CommandType commandType;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();

    public CommandParser(String userInput) {
        separatedInputs = userInput.toLowerCase().split(INPUT_SPACES);
        commandType = null;
    }

    /**
     * Checks if the first word in the input string matches any command word.
     * If it contains any command word, returns the respective commandType.
     *
     * @return CommandType corresponding to the command.
     * @throws InvalidCommandException If no command word matches the first word.
     * @throws MissingModelException If command is missing a model word.
     * @throws ExtraParameterException If command contains extra parameters.
     */
    public CommandType extractCommand() throws InvalidCommandException, MissingModelException, ExtraParameterException {
        logger.log(Level.INFO, "Extracting command now...");

        switch (separatedInputs[MAIN_COMMAND_INDEX]) {
        case INPUT_ADD:
            if (separatedInputs.length == LENGTH_SINGLE_WORD) {
                throw new MissingModelException();
            }
            commandType = CommandType.ADD;
            break;
        case INPUT_DELETE:
            if (separatedInputs.length == LENGTH_SINGLE_WORD) {
                throw new MissingModelException();
            }
            commandType = CommandType.DELETE;
            break;
        case INPUT_DONE:
            if (separatedInputs.length == LENGTH_SINGLE_WORD) {
                throw new MissingModelException();
            }
            commandType = CommandType.DONE;
            break;
        case INPUT_LIST:
            commandType = CommandType.LIST;
            break;
        case INPUT_BYE:
            if (separatedInputs.length > LENGTH_SINGLE_WORD) {
                throw new ExtraParameterException();
            }
            commandType = CommandType.BYE;
            break;
        case INPUT_HELP:
            commandType = CommandType.HELP;
            break;
        case INPUT_FIND:
            commandType = CommandType.FIND;
            break;
        case INPUT_QUIZ:
            commandType = CommandType.QUIZ;
            break;
        case INPUT_SET:
            commandType = CommandType.SET;
            break;
        default:
            throw new InvalidCommandException();
        }

        return commandType;
    }
}
