package seedu.duke.controller.command;

import seedu.duke.model.DataManager;
import seedu.duke.model.event.EventManager;

public class ListCommand extends Command {
    public void execute(EventManager eventManager) {
        eventManager.listSchedule();
    }

    @Override
    public void execute(DataManager dataModel) {
    //        dataModel.list();
    }
}
