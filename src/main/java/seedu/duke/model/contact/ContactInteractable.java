package seedu.duke.model.contact;

import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.Interactable;

//@@author AndreWongZH
/**
 * Represents the public api methods for ContactManager that the controller can call.
 */
public interface ContactInteractable extends Interactable {
    /**
     * List all contacts in ContactManager.
     */
    void list();

    /**
     * Find a list of contacts that matches with the keyword.
     *
     * @param userInput The input entered by the user.
     * @throws MissingParameterException If keyword is missing from the command.
     */
    void find(String userInput) throws MissingParameterException;

    void delete(String[] userInputs) throws IndexOutOfBoundsException;
}
