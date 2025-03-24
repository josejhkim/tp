package seedu.address.model.wedding.exceptions;

/**
 * Signals that the specified Table could not be found.
 */
public class WeddingNotFoundException extends RuntimeException {
    public WeddingNotFoundException() {
        super("Requested wedding does not exist in the app.");
    }
}
