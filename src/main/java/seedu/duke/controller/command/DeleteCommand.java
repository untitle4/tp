package seedu.duke.controller.command;

import seedu.duke.model.DataManager;

public class DeleteCommand extends Command {
    public DeleteCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(DataManager dataModel) {
        dataModel.delete(userInput.split(" "));
    }
}
