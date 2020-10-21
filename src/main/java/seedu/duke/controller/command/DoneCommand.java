package seedu.duke.controller.command;

import seedu.duke.model.DataManager;
import seedu.duke.model.event.EventDataManager;

public class DoneCommand extends Command {
    public DoneCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(DataManager dataModel) {
        EventDataManager eventDataModel = (EventDataManager) dataModel;
        eventDataModel.setDone(userInput.split(" "));
    }
}
