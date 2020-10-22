package seedu.duke.controller.command;

import seedu.duke.model.DataManager;

/**
 * Represents the command for deleting a class, cca, test, tuition, quiz and contact.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(DataManager dataModel) {
        dataModel.delete(userInput.split(" "));
    }
}
