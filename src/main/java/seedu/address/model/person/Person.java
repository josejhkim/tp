package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book. Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    private final DietaryRestriction dietaryRestriction;
    private final Rsvp rsvp;
    // Value of -1 denotes an unseated person
    private final int tableId;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
            DietaryRestriction dietaryRestriction, Rsvp rsvp, int tableId) {
        requireAllNonNull(name, phone, email, address, tags, dietaryRestriction, rsvp, tableId);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.dietaryRestriction = dietaryRestriction;
        this.rsvp = rsvp;
        this.tableId = tableId;
    }

    /**
     * Constructor for person with unassigned table
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  DietaryRestriction dietaryRestriction, Rsvp rsvp) {
        requireAllNonNull(name, phone, email, address, tags, dietaryRestriction, rsvp);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.dietaryRestriction = dietaryRestriction;
        this.rsvp = rsvp;
        this.tableId = -1;
    }

    /**
     * Creates a new Person by copying attributes from an existing guest and assigning them to a specific table.
     *
     * @param guest A guest object to copy attributes from
     * @param tableId A table object that this guest is assigned to
     */
    public Person(Person guest, int tableId) {
        requireAllNonNull(guest.name, guest.phone, guest.email, guest.address, guest.tags, guest.dietaryRestriction,
                guest.rsvp);
        this.name = guest.getName();
        this.phone = guest.getPhone();
        this.email = guest.getEmail();
        this.address = guest.getAddress();
        this.tags.addAll(guest.getTags());
        this.dietaryRestriction = guest.getDietaryRestriction();
        this.rsvp = guest.getRsvp();
        this.tableId = tableId;
    }

    /**
     * Creates a new Person that is a copy of another Person. All attributes are copied from the other Person, including
     * any table assignment.
     *
     * @param other The Person object to copy from
     */
    public Person(Person other) {
        requireAllNonNull(other.name, other.phone, other.email, other.address, other.tags, other.dietaryRestriction,
                other.rsvp);
        this.name = other.getName();
        this.phone = other.getPhone();
        this.email = other.getEmail();
        this.address = other.getAddress();
        this.tags.addAll(other.getTags());
        this.dietaryRestriction = other.getDietaryRestriction();
        this.rsvp = other.getRsvp();
        this.tableId = other.getTableId();
    }

    /**
     * Returns the name of this person.
     *
     * @return The name of this person
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns the phone number of this person.
     *
     * @return The phone number of this person
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Returns the email address of this person.
     *
     * @return The email address of this person
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Returns the residential address of this person.
     *
     * @return The residential address of this person
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns the dietary restriction of this person.
     *
     * @return The dietary restriction of this person
     */
    public DietaryRestriction getDietaryRestriction() {
        return dietaryRestriction;
    }

    /**
     * Returns the RSVP status of this person.
     *
     * @return The RSVP status of this person
     */
    public Rsvp getRsvp() {
        return rsvp;
    }

    /**
     * Returns the table assignment of this person.
     *
     * @return The table assignment of this person
     */
    public int getTableId() {
        return tableId;
    }

    public boolean isSeated() {
        return tableId > -1;
    }

    /**
     * Get the string representation for the table id. Return "Unassigned" if the guest is unassigned to a table.
     *
     * @return The string representation for the table id
     */
    public String getTableIdString() {
        return tableId == -1 ? "Unassigned" : String.valueOf(tableId);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException} if modification is attempted.
     */

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name. This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields. This defines a stronger notion of equality
     * between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name) && phone.equals(otherPerson.phone) && email.equals(otherPerson.email)
                && address.equals(otherPerson.address) && tags.equals(otherPerson.tags)
                && dietaryRestriction.equals(otherPerson.dietaryRestriction) && rsvp.equals(otherPerson.rsvp)
                && getTableId() == otherPerson.getTableId();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return this.getName().toString();
    }

}
