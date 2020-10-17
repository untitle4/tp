package seedu.duke.controller.command;

import seedu.duke.Model;

public class DeleteContactCommand extends Command {
    public DeleteContactCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) {
        model.getContactManager().deleteContact(userInput.split(" "));
    }
}
