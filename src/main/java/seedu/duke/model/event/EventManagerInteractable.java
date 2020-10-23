package seedu.duke.model.event;

import seedu.duke.exception.MissingParameterException;

public interface EventManagerInteractable {
    void list(String userInput);

    void find(String userInput) throws MissingParameterException;
}
