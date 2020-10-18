package seedu.duke.model;

import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;
import seedu.duke.exception.ContactEmptyStringException;
import seedu.duke.exception.ContactParamException;
import seedu.duke.exception.EmptyTuitionInputException;
import seedu.duke.exception.InvalidClassInputException;
import seedu.duke.exception.InvalidTuitionInputException;
import seedu.duke.exception.QuizParamException;
import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;

public interface Interactable {
    void add(String userInput) throws InvalidTuitionInputException, EmptyTuitionInputException,
            CcaEmptyStringException, CcaParamException, InvalidClassInputException, TestEmptyStringException,
            TestParamException, ContactEmptyStringException, ContactParamException, QuizParamException;

    void delete(String[] userInputs);
}
