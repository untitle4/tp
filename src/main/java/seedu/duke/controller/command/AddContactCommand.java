package seedu.duke.controller.command;

import seedu.duke.Model;
import seedu.duke.exception.ContactEmptyStringException;
import seedu.duke.exception.ContactParamException;

public class AddContactCommand extends Command {
    public AddContactCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) throws ContactParamException, ContactEmptyStringException {
        model.getContactManager().addContact(userInput);
    }
}
