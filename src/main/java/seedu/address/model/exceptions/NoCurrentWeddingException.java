package seedu.address.model.exceptions;

public class NoCurrentWeddingException extends RuntimeException {
    public NoCurrentWeddingException() {
        super("There is no current wedding.");
    }
}
