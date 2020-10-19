package seedu.duke.controller.command;

import seedu.duke.exception.ContactParamException;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.exception.QuizParamException;
import seedu.duke.model.DataManager;

public class AddCommand extends Command {
    public AddCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(DataManager dataModel) throws ContactParamException, QuizParamException,
            EmptyParameterException, MissingParameterException {
        dataModel.add(userInput);
    }
}
