package seedu.duke.controller.command;

import seedu.duke.exception.ContactParamException;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.InvalidHelpCommandException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.exception.QuizParamException;
import seedu.duke.model.DataManager;

public abstract class Command {
    protected final String userInput;

    public Command() {
        userInput = null;
    }

    protected Command(String userInput) {
        this.userInput = userInput;
    }

    public abstract void execute(DataManager dataModel) throws ContactParamException, InvalidHelpCommandException,
            QuizParamException, EmptyParameterException, MissingParameterException;
}
