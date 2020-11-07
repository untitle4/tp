package seedu.duke.controller.command;

import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.ModelMain;
import seedu.duke.model.ModelManager;

//@@author AndreWongZH
/**
 * Represents the command for adding a class, cca, test, tuition, quiz and contact.
 */
public class AddCommand extends Command {
    public AddCommand(String userInput) {
        super(userInput);
    }

    /**
     * Runs add command on corresponding model type.
     *
     * @param modelMain A model type to be modified or read.
     * @throws EmptyParameterException If command if has empty parameters
     * @throws MissingParameterException If command is lacking prefixes.
     */
    @Override
    public void execute(ModelMain modelMain) throws EmptyParameterException, MissingParameterException {
        ModelManager modelManager = (ModelManager) modelMain;
        modelManager.add(userInput);
    }
}
