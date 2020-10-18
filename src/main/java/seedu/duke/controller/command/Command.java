package seedu.duke.controller.command;

import seedu.duke.exception.*;
import seedu.duke.model.DataManager;

public abstract class Command {
    protected final String userInput;

    public Command() {
        userInput = null;
    }

    protected Command(String userInput) {
        this.userInput = userInput;
    }

    public abstract void execute(DataManager dataModel) throws InvalidClassInputException, TestParamException,
            TestEmptyStringException, CcaParamException, CcaEmptyStringException, EmptyTuitionInputException,
            InvalidTuitionInputException, ContactParamException, ContactEmptyStringException,
            InvalidHelpCommandException, QuizParamException;
}
