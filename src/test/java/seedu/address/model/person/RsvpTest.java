package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.category.Rsvp;

public class RsvpTest {

    @Test
    public void constructor_validStatus_success() {
        Rsvp rsvp = new Rsvp(Rsvp.Status.YES);
        assertEquals(Rsvp.Status.YES, rsvp.getStatus());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        Rsvp rsvp = new Rsvp(Rsvp.Status.YES);
        assertEquals(rsvp, rsvp);
    }

    @Test
    public void equals_differentObject_returnsFalse() {
        Rsvp rsvp1 = new Rsvp(Rsvp.Status.YES);
        Rsvp rsvp2 = new Rsvp(Rsvp.Status.NO);
        assertNotEquals(rsvp1, rsvp2);
    }

    @Test
    public void hashCode_sameObject_returnsSameHashCode() {
        Rsvp rsvp = new Rsvp(Rsvp.Status.YES);
        assertEquals(rsvp.hashCode(), rsvp.hashCode());
    }

    @Test
    public void toString_validStatus_returnsString() {
        Rsvp rsvp = new Rsvp(Rsvp.Status.YES);
        assertEquals("YES", rsvp.toString());
    }
}
