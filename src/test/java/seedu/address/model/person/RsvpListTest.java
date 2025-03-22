package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.category.DietaryRestriction;
import seedu.address.model.person.category.Rsvp;
import seedu.address.model.person.exceptions.PersonNotFoundException;

public class RsvpListTest {

    private RsvpList rsvpList;
    private Person guest;

    @BeforeEach
    public void setUp() {
        rsvpList = new RsvpList();
        guest = new Person(
            new Name("John Doe"),
            new Phone("12345678"),
            new Email("johndoe@example.com"),
            new Address("123 Street"),
            new HashSet<>(),
            new DietaryRestriction(DietaryRestriction.TypicalRestriction.VEGAN),
            new Rsvp(Rsvp.Status.YES),
            null
        );
    }

    @Test
    public void add_validGuest_success() {
        rsvpList.add(guest);
        assertEquals(1, rsvpList.getAllGuests().size());
    }

    @Test
    public void contains_validGuest_returnsTrue() {
        rsvpList.add(guest);
        assertTrue(rsvpList.contains(guest));
    }

    @Test
    public void contains_invalidPerson_returnsFalse() {
        Person person = new Person(
            new Name("Jane Doe"),
            new Phone("87654321"),
            new Email("janedoe@example.com"),
            new Address("456 Avenue"),
            new HashSet<>(),
            new DietaryRestriction(DietaryRestriction.TypicalRestriction.NONE),
            new Rsvp(Rsvp.Status.NO),
            null
        );
        assertFalse(rsvpList.contains(person));
    }

    @Test
    public void remove_validGuest_success() {
        rsvpList.add(guest);
        rsvpList.remove(guest);
        assertEquals(0, rsvpList.getAllGuests().size());
    }

    @Test
    public void remove_invalidPerson_throwsPersonNotFoundException() {
        Person person = new Person(
            new Name("Jane Doe"),
            new Phone("87654321"),
            new Email("janedoe@example.com"),
            new Address("456 Avenue"),
            new HashSet<>(),
            new DietaryRestriction(DietaryRestriction.TypicalRestriction.NONE),
            new Rsvp(Rsvp.Status.NO),
            null
        );
        assertThrows(PersonNotFoundException.class, () -> rsvpList.remove(person));
    }
}
