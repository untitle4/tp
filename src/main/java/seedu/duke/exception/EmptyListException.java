package seedu.duke.exception;

/**
 * Represents the error when list to be displayed is empty.
 */
public class EmptyListException extends Exception {
    public EmptyListException(String message) {
        super(message);
    }
}
