package seedu.duke.controller.command;

import seedu.duke.controller.parser.CommandType;

/**
 * Represents a generator that returns the corresponding Command class
 * based on the commandType.
 */
public class CommandFactory {
    private final CommandType commandType;
    private final String userInput;

    public CommandFactory(CommandType commandType, String userInput) {
        assert commandType != null : "commandType must not be null";
        this.commandType = commandType;
        this.userInput = userInput;
    }

    /**
     * Returns a Command class back to the ControlManager
     * based on the commandType.
     *
     * @return Command class which is to be executed.
     */
    public Command generateActionableCommand() {
        switch (commandType) {
        case HELP:
            return new HelpCommand(userInput);
        case ADD:
            return new AddCommand(userInput);
        case DELETE:
            return new DeleteCommand(userInput);
        case DONE:
            return new DoneCommand(userInput);
        case LIST:
            return new ListCommand();
        case FIND:
            return new FindCommand(userInput);
        case BYE:
            // bye does not have a corresponding Command class
            break;
        default:
            assert false : "all commandType should be handled";
        }
        return null;
    }
}
