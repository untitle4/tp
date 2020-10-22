package seedu.duke.controller.command;

import seedu.duke.controller.parser.CommandType;

public class CommandFactory {
    private final CommandType commandType;
    private final String userInput;

    public CommandFactory(CommandType commandType, String userInput) {
        this.commandType = commandType;
        this.userInput = userInput;
    }

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
        case QUIZ:
            return new QuizCommand(userInput);
        case BYE:
            // fall through
        default:
            // do nothing
        }
        return null;
    }
}
