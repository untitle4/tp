package seedu.duke.controller.command;

import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;
import seedu.duke.exception.ContactEmptyStringException;
import seedu.duke.exception.ContactParamException;
import seedu.duke.exception.EmptyTuitionInputException;
import seedu.duke.exception.InvalidClassInputException;
import seedu.duke.exception.InvalidTuitionInputException;
import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;
import seedu.duke.model.DataManager;

public class AddCommand extends Command {
    public AddCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(DataManager dataModel) throws EmptyTuitionInputException, ContactEmptyStringException,
            TestParamException, ContactParamException, InvalidTuitionInputException, InvalidClassInputException,
            CcaParamException, CcaEmptyStringException, TestEmptyStringException {
        dataModel.add(userInput);
    }
}
