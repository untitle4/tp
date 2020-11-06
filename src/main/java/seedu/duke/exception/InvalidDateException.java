package seedu.duke.exception;

public class InvalidDateException extends Exception {
    private final InvalidDateType errorType;

    public InvalidDateException(InvalidDateType errorType) {
        this.errorType = errorType;
    }

    public InvalidDateType getErrorType() {
        return this.errorType;
    }
}
