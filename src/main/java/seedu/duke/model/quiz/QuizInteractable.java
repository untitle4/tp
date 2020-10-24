package seedu.duke.model.quiz;

import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.Interactable;

//@@author AndreWongZH
/**
 * Represents the public api methods for QuizManager that the controller can call.
 */
public interface QuizInteractable extends Interactable {
    /**
     * List all quizzes in QuizManager.
     */
    void list();

    /**
     * Find a list of quiz that matches with the keyword.
     *
     * @param userInput The input entered by the user.
     * @throws MissingParameterException If keyword is missing from the command.
     */
    void find(String userInput) throws MissingParameterException;
}
