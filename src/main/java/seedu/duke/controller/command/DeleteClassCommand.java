package seedu.duke.controller.command;

import seedu.duke.model.Model;

public class DeleteClassCommand extends Command {
    public DeleteClassCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) {
        model.getEventManager().getClassManager().deleteClass(userInput.split(" "));
    }
}
