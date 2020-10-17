package seedu.duke.controller.command;

import seedu.duke.model.Model;

public class DoneTestCommand extends Command {
    public DoneTestCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) {
        model.getEventManager().getTestManager().setTestDone(userInput.split(" "));
    }
}
