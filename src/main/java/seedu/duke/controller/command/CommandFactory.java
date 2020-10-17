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
        case ADD_CLASS:
            return new AddClassCommand(userInput);
        case ADD_CCA:
            return new AddCcaCommand(userInput);
        case ADD_TEST:
            return new AddTestCommand(userInput);
        case ADD_CONTACT:
            return new AddContactCommand(userInput);
        case ADD_TUITION:
            return new AddTuitionCommand(userInput);
        case DELETE_CLASS:
            return new DeleteClassCommand(userInput);
        case DELETE_CCA:
            return new DeleteCcaCommand(userInput);
        case DELETE_TEST:
            return new DeleteTestCommand(userInput);
        case DELETE_QUIZ:
            return new DeleteQuizCommand(userInput);
        case DELETE_TUITION:
            return new DeleteTuitionCommand(userInput);
        case DELETE_CONTACT:
            return new DeleteContactCommand(userInput);
        case DONE_CLASS:
            return new DoneClassCommand(userInput);
        case DONE_TEST:
            return new DoneTestCommand(userInput);
        case DONE_CCA:
            return new DoneCcaCommand(userInput);
        case DONE_TUITION:
            return new DoneTuitionCommand(userInput);
        case ADD_QUIZ:
            return new AddQuizCommand(userInput);
        case LIST:
            return new ListCommand();
        case LIST_QUIZ:
            return new ListQuizCommand();
        case LIST_CONTACT:
            return new ListContactCommand();
        case BYE:
            // fall through
        default:
            // do nothing
        }
        return null;
    }
}
