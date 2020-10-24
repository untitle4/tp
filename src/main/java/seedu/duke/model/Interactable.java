package seedu.duke.model;

import seedu.duke.exception.ContactParamException;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.exception.QuizParamException;

public interface Interactable {
    void add(String userInput) throws MissingParameterException, EmptyParameterException,
            QuizParamException, ContactParamException;

    void delete(String[] userInputs);
}
