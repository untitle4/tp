package seedu.duke.controller.command;

import seedu.duke.model.Model;

public class DoneTuitionCommand extends Command {
    public DoneTuitionCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) {
        model.getEventManager().getTuitionManager().setTuitionDone(userInput.split(" "));
    }
}
