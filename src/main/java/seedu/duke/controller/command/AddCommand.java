package seedu.duke.controller.command;

import seedu.duke.exception.*;
import seedu.duke.model.DataManager;

public class AddCommand extends Command {
    public AddCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(DataManager dataModel) throws EmptyTuitionInputException, ContactEmptyStringException,
            TestParamException, ContactParamException, InvalidTuitionInputException, InvalidClassInputException,
            CcaParamException, CcaEmptyStringException, TestEmptyStringException, QuizParamException {
        dataModel.add(userInput);
    }
}
