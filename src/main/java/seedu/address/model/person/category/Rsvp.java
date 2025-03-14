package seedu.address.model.person.category;

import java.util.Objects;

/**
 * Represents an RSVP in the address book.
 */
public class Rsvp implements Category {
    /**
     * Enum to represent typical RSVP statuses.
     */
    public enum Status {
        YES, NO, NO_RESPONSE
    }
    private final Status status;

    /**
     * Constructs a default {@code Rsvp}
     * representing no response
     */
    public Rsvp() {
        this.status = Status.NO_RESPONSE;
    }

    public Rsvp(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Rsvp)) {
            return false;
        }
        Rsvp otherRsvp = (Rsvp) other;
        return status == otherRsvp.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return status.toString();
    }
}
