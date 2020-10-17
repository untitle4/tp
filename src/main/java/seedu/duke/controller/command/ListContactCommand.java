package seedu.duke.controller.command;

import seedu.duke.model.Model;

public class ListContactCommand extends Command {
    @Override
    public void execute(Model model) {
        model.getContactManager().listContacts();
    }
}
