package seedu.duke.controller.command;

import seedu.duke.model.Model;

public class DoneClassCommand extends Command {
    public DoneClassCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) {
        model.getEventManager().getClassManager().setClassDone(userInput.split(" "));
    }
}
