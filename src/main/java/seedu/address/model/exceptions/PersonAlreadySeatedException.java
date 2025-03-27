package seedu.address.model.exceptions;

public class PersonAlreadySeatedException extends RuntimeException {
    public PersonAlreadySeatedException() {
        super("The given person is already seated at a table!");
    }
}
