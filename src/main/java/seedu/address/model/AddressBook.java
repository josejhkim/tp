package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.wedding.Wedding;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;

    private Wedding wedding;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();

    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }



    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Sets the active wedding in the address book.
     * Replaces any existing wedding.
     */
    public void setWedding(Wedding wedding) {
        requireNonNull(wedding);
        this.wedding = wedding;
    }

    /**
     * Retrieves the currently active wedding.
     */
    public Wedding getWedding() {
        return wedding;
    }

    public void removeWedding() {
        this.wedding = null;
    }

    /**
     * Checks if a wedding exists.
     */
    public boolean hasWedding() {
        return wedding != null;
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// wedding-level operations

    // /**
    //  * Returns true if a wedding with the same identity as {@code wedding} exists in the address book.
    //  */
    // public boolean hasWedding(Wedding wedding) {
    //     requireNonNull(wedding);
    //     return weddings.contains(wedding);
    // }

    // /**
    //  * Adds a wedding to the address book.
    //  * The wedding must not already exist in the address book.
    //  */
    // public void addWedding(Wedding wedding) {
    //     weddings.add(wedding);
    // }
    //
    // /**
    //  * Removes {@code wedding} from this {@code AddressBook}.
    //  * {@code wedding} must exist in the address book.
    //  */
    // public void removeWedding(Wedding wedding) {
    //     weddings.remove(wedding);
    // }
    //
    // /**
    //  * Finds and returns the wedding with the given name.
    //  * Returns null if no such wedding exists.
    //  */
    // public Wedding findWeddingByName(String name) {
    //     requireNonNull(name);
    //     return weddings.stream()
    //         .filter(wedding -> wedding.getName().equals(name))
    //         .findFirst()
    //         .orElseThrow(() -> new IllegalArgumentException("No such wedding exists"));
    // }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons);

    }


    @Override
    public int hashCode() {
        return persons.hashCode();
    }


}
