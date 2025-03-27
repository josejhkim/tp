package seedu.address.model.exceptions;

/**
 * Exception to denote that there is no current wedding set in the AddressBook.
 */
public class NoCurrentWeddingException extends RuntimeException {
    public NoCurrentWeddingException() {
        super("There is no current wedding.");
    }
}
