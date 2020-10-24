package seedu.duke.model.quiz;

import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.Interactable;

public interface QuizInteractable extends Interactable {
    void list();

    void find(String userInput) throws MissingParameterException;
}
