package seedu.duke.model.contact;

import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.Interactable;

public interface ContactInteractable extends Interactable {
    void list();

    void find(String userInput) throws MissingParameterException;
}
