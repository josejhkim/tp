
package seedu.address.model.table.exceptions;

/**
 * Signals that the operation will result in duplicate Tables (Tables are considered duplicates
 * if they have the same identity).
 */
public class DuplicateTableException extends RuntimeException {
    public DuplicateTableException() {
        super("Operation would result in duplicate tables.");
    }
}
