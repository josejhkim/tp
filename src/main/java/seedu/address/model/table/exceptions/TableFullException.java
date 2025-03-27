package seedu.address.model.table.exceptions;

/**
 * Signals that the specified Table could not be found.
 */
public class TableFullException extends RuntimeException {
    public TableFullException() {
        super("The table you're trying to add a person to is already full!");
    }
}
