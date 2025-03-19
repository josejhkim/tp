package seedu.address.model.table.exceptions;

/**
 * Signals that the specified Table could not be found.
 */
public class TableNotFoundException extends RuntimeException {
    public TableNotFoundException() {
        super("Requested table does not exist in the list.");
    }
}
