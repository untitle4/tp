package seedu.duke.exception;

/**
 * Represents the error when the start date or end date is not valid.
 */
public class InvalidDateException extends Exception {
    private final InvalidDateType errorType;

    public InvalidDateException(InvalidDateType errorType) {
        this.errorType = errorType;
    }

    public InvalidDateType getErrorType() {
        return this.errorType;
    }
}
