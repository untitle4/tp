package seedu.duke.controller.command;

import seedu.duke.Model;

public class ListCommand extends Command {
    @Override
    public void execute(Model model) {
        model.getEventManager().listSchedule();
    }
}
