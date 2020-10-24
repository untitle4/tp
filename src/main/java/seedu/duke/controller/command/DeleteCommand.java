package seedu.duke.controller.command;

import seedu.duke.model.ModelMain;
import seedu.duke.model.ModelManager;

//@@author AndreWongZH
/**
 * Represents the command for deleting a class, cca, test, tuition, quiz and contact.
 */
public class DeleteCommand extends Command {

    public static final String INPUT_SPACE = " ";

    public DeleteCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(ModelMain modelMain) {
        ModelManager modelManager = (ModelManager) modelMain;
        modelManager.delete(userInput.split(INPUT_SPACE));
    }
}
