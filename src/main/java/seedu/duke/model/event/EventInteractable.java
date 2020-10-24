package seedu.duke.model.event;

import seedu.duke.model.Interactable;

//@@author AndreWongZH
/**
 * Represents the public api methods for Class, CCA, Test and Tuition Manager that the controller can call.
 */
public interface EventInteractable extends Interactable {
    /**
     * Set a particular event to be done.
     *
     * @param userInputs The input entered by the user.
     */
    void setDone(String[] userInputs);
}
