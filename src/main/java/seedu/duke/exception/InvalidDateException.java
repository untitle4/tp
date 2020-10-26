package seedu.duke.exception;

public class InvalidDateException extends Exception {
    private InvalidDateType errorType;

    public InvalidDateException(InvalidDateType errorType) {
        this.errorType = errorType;
    }

    public InvalidDateType getErrorType() {
        return this.errorType;
    }
}
