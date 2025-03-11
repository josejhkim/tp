package seedu.address.model.person;

import java.util.Objects;

public class RSVP {
    public enum Status {
        YES, NO, NO_RESPONSE
    }
    private final Status status;

    public RSVP(Status status) {
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
        if (!(other instanceof RSVP)) {
            return false;
        }
        RSVP otherRSVP = (RSVP) other;
        return status == otherRSVP.status;
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