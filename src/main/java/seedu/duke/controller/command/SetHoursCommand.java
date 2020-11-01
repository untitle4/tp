package seedu.duke.controller.command;

import seedu.duke.model.ConfigParameter;
import seedu.duke.model.ModelMain;
import seedu.duke.storage.ConfigStorageManager;
import seedu.duke.ui.ConfigManager;

public class SetHoursCommand extends Command{
    private final ConfigManager configManager;
    /**
     * Runs if the execution requires passing the user input to the model.
     *
     * @param userInput String given from the user.
     */
    protected SetHoursCommand(String userInput) {
        super(userInput);
        configManager = ConfigManager.getInstance();
    }

    @Override
    public void execute(ModelMain modelMain) throws NumberFormatException {
        configManager.editHours();
    }
}
