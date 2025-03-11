package seedu.address.model.person;

import java.util.HashSet;
import java.util.Objects;

public class Guest extends Person {
    private final DietaryRestriction DietaryRestriction;
    private final RSVP rsvp;
    private final int guestId;

    /*
        * Every field must be present and not null, a person class is created with
        * name, phone, email, address, and an EMPTY set of tags
     */
    public Guest(Name name, Email email, Address address, Phone phone, DietaryRestriction DietaryRestriction,
                 RSVP rsvp) {
        super(name, phone, email, address, new HashSet<>());
        this.DietaryRestriction = DietaryRestriction;
        this.rsvp = rsvp;
        this.guestId = generateGuestId(phone);
    }

    private int generateGuestId(Phone phone) {
        return phone.hashCode();
    }

    public DietaryRestriction getDietaryRestriction() {
        return DietaryRestriction;
    }

    public RSVP getRSVP() {
        return rsvp;
    }

    public Integer getGuestId() {
        return this.guestId;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Guest)) {
            return false;
        }
        Guest otherGuest = (Guest) other;
        return super.equals(other) &&
                Objects.equals(DietaryRestriction, otherGuest.DietaryRestriction) &&
                rsvp == otherGuest.rsvp &&
                guestId == otherGuest.guestId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), DietaryRestriction, rsvp);
    }

    @Override
    public String toString() {
        return super.toString() + ", Dietary Restriction: " + DietaryRestriction + ", RSVP: " + rsvp;
    }

}
