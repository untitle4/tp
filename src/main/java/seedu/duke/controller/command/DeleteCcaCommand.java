package seedu.duke.controller.command;

import seedu.duke.model.Model;

public class DeleteCcaCommand extends Command {
    public DeleteCcaCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) {
        model.getEventManager().getCcaManager().deleteCca(userInput.split(" "));
    }
}
