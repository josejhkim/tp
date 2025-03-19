package seedu.address.model.person;

import java.util.HashSet;
import java.util.Objects;

import seedu.address.model.person.category.DietaryRestriction;
import seedu.address.model.person.category.Rsvp;
import seedu.address.model.table.Table;

/**
 * Represents a Guest in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Guest extends Person {
    private final DietaryRestriction dietaryRestriction;
    private final Rsvp rsvp;
    private final Table table;

    /**
    * Every field must be present and not null, a guest class is created with
    * name, phone, email, address, and an EMPTY set of tags
     */
    public Guest(Name name, Phone phone, Email email, Address address,
                 DietaryRestriction dietaryRestriction,
                 Rsvp rsvp, Table table) {
        super(name, phone, email, address, new HashSet<>());
        this.dietaryRestriction = dietaryRestriction;
        this.rsvp = rsvp;
        this.table = table;
    }

    public Guest(Guest guest, Table table) {
        super(guest.getName(), guest.getPhone(), guest.getEmail(), guest.getAddress(), new HashSet<>());
        this.dietaryRestriction = guest.getDietaryRestriction();
        this.rsvp = guest.getRsvp();
        this.table = table;
    }

    public DietaryRestriction getDietaryRestriction() {
        return dietaryRestriction;
    }

    public Rsvp getRsvp() {
        return rsvp;
    }

    public Table getTable() {
        return table;
    }

    /**
     * Get the id for the table this guest is sitting at.
     * Return -1 if the guest is unassigned to a table.
     * @return The id of the table for this guest or -1 if unassigned
     */
    public int getTableId() {
        return this.table == null ? -1 : this.table.getTableId();
    }

    /**
     * Get the string representation for the table id.
     * Return "Unassigned" if the guest is unassigned to a table.
     * @return The string representation for the table id
     */
    public String getTableIdString() {
        if (this.table == null) {
            return "Unassigned";
        }

        return String.valueOf(this.table.getTableId());
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
        return super.equals(other)
            && Objects.equals(dietaryRestriction, otherGuest.dietaryRestriction)
            && rsvp == otherGuest.rsvp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dietaryRestriction, rsvp);
    }

    @Override
    public String toString() {
        return "Name: " + getName()
            + "; Phone: " + getPhone()
            + "; Email: " + getEmail()
            + "; Address: " + getAddress()
            + "; Tags: " + getTags()
            + "; Dietary Restriction: " + dietaryRestriction
            + ", RSVP: " + rsvp
            + ", Table: " + getTableIdString();
    }

}
