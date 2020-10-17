package seedu.duke.controller.command;

import seedu.duke.model.Model;

public class DeleteTestCommand extends Command {
    public DeleteTestCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) {
        model.getEventManager().getCcaManager().deleteCca(userInput.split(" "));
    }
}
