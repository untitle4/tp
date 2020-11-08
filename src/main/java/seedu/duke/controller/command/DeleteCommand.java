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
    public static final int INPUT_LENGTH_THREE = 3;

    public DeleteCommand(String userInput) {
        super(userInput);
    }

    /**
     * Runs delete on the required model.
     *
     * @param modelMain A model type to be modified or read.
     * @throws ExtraParameterException If user adds extra parameters to delete [model] [number].
     */
    @Override
    public void execute(ModelMain modelMain) throws ExtraParameterException {
        String[] separatedInputs = userInput.split(INPUT_SPACE);
        if (separatedInputs.length > INPUT_LENGTH_THREE) {
            throw new ExtraParameterException();
        }

        ModelManager modelManager = (ModelManager) modelMain;
        modelManager.delete(separatedInputs);
    }
}
