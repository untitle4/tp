package seedu.duke.model.event;

import seedu.duke.exception.MissingParameterException;

//@@author AndreWongZH
/**
 * Represents the public api methods for EventManager that the controller can call.
 */
public interface EventManagerInteractable {
    /**
     * List all events in EventManager.
     *
     * @param userInput The input entered by the user.
     */
    void list(String userInput);

    /**
     * Find a list of events that matches with the keyword.
     *
     * @param userInput The input entered by the user.
     * @throws MissingParameterException If keyword is missing from the command.
     */
    void find(String userInput) throws MissingParameterException;
}
