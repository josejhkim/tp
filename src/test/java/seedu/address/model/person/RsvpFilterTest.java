package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.Rsvp.Status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonBuilder;

public class RsvpFilterTest {

    private Rsvp accepted;
    private Rsvp declined;
    private Rsvp noResponse;
    private Status yesStatus = Status.YES;
    private Status noStatus = Status.NO;
    private Status noResponseStatus = Status.NO_RESPONSE;
    private RsvpFilter acceptedFilter;
    private RsvpFilter declinedFilter;
    private RsvpFilter noResponseFilter;

    @BeforeEach
    public void setUp() {
        // Initialise common RSVP values and the corresponding filter.
        accepted = new Rsvp(yesStatus);
        declined = new Rsvp(noStatus);
        noResponse = new Rsvp(noResponseStatus);
        acceptedFilter = new RsvpFilter(accepted);
        declinedFilter = new RsvpFilter(declined);
        noResponseFilter = new RsvpFilter(noResponse);
    }

    @Test
    public void equals() {
        RsvpFilter acceptedFilterCopy = new RsvpFilter(accepted);
        RsvpFilter declinedFilter = new RsvpFilter(declined);

        // Same object -> returns true
        assertEquals(acceptedFilter, acceptedFilter);

        // Same values -> returns true
        assertEquals(acceptedFilter, acceptedFilterCopy);

        // Different types -> returns false
        assertNotEquals(acceptedFilter, 1);

        // Null -> returns false
        assertNotEquals(acceptedFilter, null);

        // Different RSVP -> returns false
        assertNotEquals(acceptedFilter, declinedFilter);
    }

    @Test
    public void test_rsvpMatches_returnsTrue() {
        // Build a Person with a matching RSVP ("Accepted").
        Person person = new PersonBuilder().withRsvp(yesStatus).build();
        assertTrue(acceptedFilter.test(person));
    }

    @Test
    public void test_rsvpDoesNotMatch_returnsFalse() {
        // Build a Person with a different RSVP ("Declined").
        Person person = new PersonBuilder().withRsvp(noStatus).build();
        assertFalse(acceptedFilter.test(person));
    }

    @Test
    public void test_toString() {
        String expected = RsvpFilter.class.getCanonicalName() + "{keywords=" + accepted + "}";
        assertEquals(expected, acceptedFilter.toString());
    }
}
