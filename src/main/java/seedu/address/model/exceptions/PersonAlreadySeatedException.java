package seedu.address.model.exceptions;

/**
 * Exception to denote that the person in question is already seated at a table
 */
public class PersonAlreadySeatedException extends RuntimeException {
    public PersonAlreadySeatedException() {
        super("The given person is already seated at a table!");
    }
}
