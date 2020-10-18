package seedu.duke.controller.command;

import seedu.duke.model.DataManager;
import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;
import seedu.duke.exception.ContactEmptyStringException;
import seedu.duke.exception.ContactParamException;
import seedu.duke.exception.EmptyTuitionInputException;
import seedu.duke.exception.InvalidClassInputException;
import seedu.duke.exception.InvalidHelpCommandException;
import seedu.duke.exception.InvalidTuitionInputException;
import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;

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
            InvalidHelpCommandException;
}
