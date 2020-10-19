package seedu.duke.controller.command;

import seedu.duke.model.DataManager;
import seedu.duke.model.event.EventManager;

public class ListCommand extends Command {
    private String userInput;

    public ListCommand() {
    }

    public ListCommand(String userInput) {
        this.userInput = userInput;
    }

    public void execute(EventManager eventManager) {
        eventManager.listSchedule(userInput);
    }

    @Override
    public void execute(DataManager dataModel) {
    //        dataModel.list();
    }
}
