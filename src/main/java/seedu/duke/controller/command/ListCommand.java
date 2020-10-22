package seedu.duke.controller.command;

import seedu.duke.model.DataManager;
import seedu.duke.model.event.EventManager;

/**
 * Represents the command for listing events, quizzes and contacts.
 */
public class ListCommand extends Command {
    public ListCommand() {
    }

    public ListCommand(String userInput) {
        super(userInput);
    }

    public void execute(EventManager eventManager) {
        eventManager.listSchedule(userInput);
    }

    @Override
    public void execute(DataManager dataModel) {
        dataModel.list();
    }
}
