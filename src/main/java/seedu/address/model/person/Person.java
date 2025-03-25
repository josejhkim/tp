package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.category.DietaryRestriction;
import seedu.address.model.person.category.Rsvp;
import seedu.address.model.table.Table;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book. Guarantees: details are present and
 * not null, field values are validated, immutable.
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
    private final Optional<Table> table;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
            DietaryRestriction dietaryRestriction, Rsvp rsvp, Optional<Table> table) {
        requireAllNonNull(name, phone, email, address, tags, dietaryRestriction, rsvp);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.dietaryRestriction = dietaryRestriction;
        this.rsvp = rsvp;
        this.table = table != null ? table : Optional.empty();
    }

    /**
     * @param guest A guest object to copy attributes from
     * @param table A table object that this guest is assigned to
     */
    public Person(Person guest, Table table) {
        requireAllNonNull(guest.name, guest.phone, guest.email, guest.address, guest.tags, guest.dietaryRestriction,
                guest.rsvp);
        this.name = guest.getName();
        this.phone = guest.getPhone();
        this.email = guest.getEmail();
        this.address = guest.getAddress();
        this.tags.addAll(guest.getTags());
        this.dietaryRestriction = guest.getDietaryRestriction();
        this.rsvp = guest.getRsvp();
        this.table = Optional.ofNullable(table);
    }

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
        this.table = other.getTable();
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public DietaryRestriction getDietaryRestriction() {
        return dietaryRestriction;
    }

    public Rsvp getRsvp() {
        return rsvp;
    }

    public Optional<Table> getTable() {
        return table;
    }

    /**
     * Get the id for the table this guest is sitting at. Return -1 if the guest is
     * unassigned to a table.
     * 
     * @return The id of the table for this guest or -1 if unassigned
     */
    public int getTableId() {
        return this.table.map(Table::getTableId).orElse(-1);
    }

    /**
     * Get the string representation for the table id. Return "Unassigned" if the
     * guest is unassigned to a table.
     * 
     * @return The string representation for the table id
     */
    public String getTableIdString() {
        return table.map(t -> String.valueOf(t.getTableId())).orElse("Unassigned");
    }

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name. This defines a weaker notion
     * of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields. This
     * defines a stronger notion of equality between two persons.
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
                && dietaryRestriction.equals(otherPerson.dietaryRestriction) && rsvp.equals(otherPerson.rsvp);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("name", name).add("phone", phone).add("email", email)
                .add("address", address).add("tags", tags).add("dietaryRestriction", dietaryRestriction)
                .add("rsvp", rsvp).add("table", table.map(Table::toString).orElse("Unassigned")).toString();
    }

}
