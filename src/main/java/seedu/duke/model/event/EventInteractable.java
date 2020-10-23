package seedu.duke.model.event;

import seedu.duke.model.Interactable;

public interface EventInteractable extends Interactable {
    void setDone(String[] userInputs);
}
