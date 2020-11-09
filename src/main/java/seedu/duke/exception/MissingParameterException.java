package seedu.duke.exception;

/**
 * Represents the error when there are missing required prefixes.
 */
public class MissingParameterException extends Exception {
    public MissingParameterException(String message) {
        super(message);
    }
}
