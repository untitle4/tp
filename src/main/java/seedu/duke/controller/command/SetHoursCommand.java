package seedu.duke.controller.command;

import seedu.duke.exception.ExtraParameterException;
import seedu.duke.model.ModelMain;
import seedu.duke.ui.ConfigManager;

//@@author Aliciaho
public class SetHoursCommand extends Command {
    public static final String INPUT_SPACE = " ";

    /**
     * Runs if the execution requires passing the user input to the model.
     *
     * @param userInput String given from the user.
     */
    protected SetHoursCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(ModelMain modelMain) throws NumberFormatException, ExtraParameterException {
        if (userInput.split(INPUT_SPACE).length > 2) {
            throw new ExtraParameterException();
        }
        ConfigManager configManager = (ConfigManager) modelMain;
        configManager.editHours();
    }
}
