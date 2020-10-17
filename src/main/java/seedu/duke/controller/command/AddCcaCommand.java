package seedu.duke.controller.command;

import seedu.duke.Model;
import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;

public class AddCcaCommand extends Command {
    public AddCcaCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) throws CcaParamException, CcaEmptyStringException {
        model.getEventManager().getCcaManager().addCca(userInput);
    }
}
