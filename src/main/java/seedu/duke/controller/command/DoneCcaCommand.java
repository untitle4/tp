package seedu.duke.controller.command;

import seedu.duke.model.Model;

public class DoneCcaCommand extends Command {
    public DoneCcaCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) {
        model.getEventManager().getCcaManager().setCcaDone(userInput.split(" "));
    }
}
