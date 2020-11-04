package seedu.duke.controller.command;

import seedu.duke.exception.ExtraParameterException;
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
    public void execute(ModelMain modelMain) throws ExtraParameterException {
        String[] separatedInputs = userInput.split(INPUT_SPACE);
        if (separatedInputs.length > 3) {
            throw new ExtraParameterException();
        }

        ModelManager modelManager = (ModelManager) modelMain;
        modelManager.delete(separatedInputs);
    }
}
