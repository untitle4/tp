package seedu.duke.controller.command;

import seedu.duke.model.Model;
import seedu.duke.exception.EmptyTuitionInputException;
import seedu.duke.exception.InvalidTuitionInputException;

public class AddTuitionCommand extends Command {
    public AddTuitionCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) throws EmptyTuitionInputException, InvalidTuitionInputException {
        model.getEventManager().getTuitionManager().addTuition(userInput);
    }
}
