package seedu.duke.controller.command;

import seedu.duke.model.Model;
import seedu.duke.exception.InvalidClassInputException;

public class AddClassCommand extends Command {
    public AddClassCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) throws InvalidClassInputException {
        model.getEventManager().getClassManager().addClass(userInput);
    }
}
