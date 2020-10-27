package seedu.duke.controller.command;

import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.IncompleteListCommandException;
import seedu.duke.exception.InvalidHelpCommandException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.ModelMain;

//@@author AndreWongZH
/**
 * Represents the base of all command types.
 */
public abstract class Command {
    protected final String userInput;

    /**
     * Runs if the execution requires passing the user input to the model.
     *
     * @param userInput String given from the user.
     */
    protected Command(String userInput) {
        this.userInput = userInput;
    }

    public abstract void execute(ModelMain modelMain) throws InvalidHelpCommandException,
            EmptyParameterException, MissingParameterException, IncompleteListCommandException;
}
