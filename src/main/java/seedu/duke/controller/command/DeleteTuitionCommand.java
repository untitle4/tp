package seedu.duke.controller.command;

import seedu.duke.model.Model;

public class DeleteTuitionCommand extends Command {
    public DeleteTuitionCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) {
        model.getEventManager().getTuitionManager().deleteTuition(userInput.split(" "));
    }
}
