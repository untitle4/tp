package seedu.duke.model;

import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;

//@@author AndreWongZH
/**
 * Represents the public api methods across all ModelManager that the controller can call.
 */
public interface Interactable {

    /**
     * Adds an object to a ModelManager.
     *
     * @param userInput The input entered by the user.
     * @throws MissingParameterException If command is missing parameters prefix.
     * @throws EmptyParameterException If no parameter inputs are found after the prefix.
     */
    void add(String userInput) throws MissingParameterException, EmptyParameterException;

    /**
     * Deletes an object from a ModelManager.
     *
     * @param userInputs The input entered by the user.
     */
    void delete(String[] userInputs);
}
