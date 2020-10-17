package seedu.duke.controller.command;

import seedu.duke.Model;
import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;

public class AddTestCommand extends Command {
    public AddTestCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) throws TestParamException, TestEmptyStringException {
        model.getEventManager().getTestManager().addTest(userInput);
    }
}
