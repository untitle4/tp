package seedu.duke.model;

import seedu.duke.exception.*;

public interface Interactable {
    void add(String userInput) throws InvalidTuitionInputException, EmptyTuitionInputException,
            CcaEmptyStringException, CcaParamException, InvalidClassInputException, TestEmptyStringException,
            TestParamException, ContactEmptyStringException, ContactParamException, QuizParamException;

    void delete(String[] userInputs);
}
