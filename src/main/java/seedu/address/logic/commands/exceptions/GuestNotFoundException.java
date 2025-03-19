package seedu.address.logic.commands.exceptions;
/**
 * Signals that the requested guest could not be found in the RSVP list.
 */
public class GuestNotFoundException extends RuntimeException {
    public GuestNotFoundException(String message) {
        super(message);
    }
}
