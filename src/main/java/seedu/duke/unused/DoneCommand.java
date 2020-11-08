package seedu.duke.unused;

import seedu.duke.controller.command.Command;
import seedu.duke.model.ModelMain;
import seedu.duke.model.event.EventDataManager;

//@@author AndreWongZH-unused
/*
 * We decided to remove the done feature because it does not tie in well with our application.
 */
/**
 * Represents the command for setting class, cca, test and tuition to be done.
 */
public class DoneCommand extends Command {

    public static final String INPUT_SPACE = " ";

    public DoneCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(ModelMain modelMain) {
        EventDataManager eventDataModel = (EventDataManager) modelMain;
        eventDataModel.setDone(userInput.split(INPUT_SPACE));
    }
}
