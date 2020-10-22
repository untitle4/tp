package seedu.duke.controller.command;

import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.DataManager;
import seedu.duke.model.event.EventManager;

/**
 * Represents the command for searching via event description.
 */
public class FindCommand extends Command {
    public FindCommand(String userInput) {
        super(userInput);
    }

    public void execute(EventManager eventManager) throws MissingParameterException {
        assert userInput != null;
        eventManager.findEvents(userInput);
    }

    @Override
    public void execute(DataManager dataModel) throws MissingParameterException {
        dataModel.find(userInput);
    }
}
