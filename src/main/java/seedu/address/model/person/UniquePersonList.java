package seedu.address.model.person;

import java.util.Iterator;
import java.util.List;
import static java.util.Objects.requireNonNull;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList implements Iterable<Person> {

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);


    /**
     * Constructs an empty UniquePersonList.
     */
    public UniquePersonList() {

    }

    /**
     * Constructs a UniquePersonList containing the persons in the given list.
     * The persons are added as deep copies of the original persons.
     *
     * @param other The UniquePersonList to copy from
     */
    public UniquePersonList(UniquePersonList other) {
        for (Person p : other) {
            add(new Person(p));
        }
    }

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPerson);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     *
     * @param toRemove The person to be removed from the list
     * @throws PersonNotFoundException if the person does not exist in the list
     */
    public void delete(Person toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Returns the person with the given name.
     * The person must exist in the list.
     *
     * @param name The name of the person to find
     * @return The person with the given name
     * @throws PersonNotFoundException if no person with the given name exists in the list
     */
    public Person findPersonByName(Name name) throws PersonNotFoundException {
        requireNonNull(name);
        return internalList.stream()
            .filter(person -> person.getName().equals(name))
            .findFirst()
            .orElseThrow(PersonNotFoundException::new);
    }

    /**
     * Returns the person with the given phone number.
     * The person must exist in the list.
     *
     * @param phone The phone number of the person to find
     * @return The person with the given phone number
     * @throws CommandException if no person with the given phone number exists in the list
     */
    public Person findPersonByName(Phone phone) throws CommandException {
        return asUnmodifiableObservableList().stream()
            .filter(guest -> guest.getPhone().equals(phone))
            .findFirst()
            .orElseThrow(() -> new CommandException(
                "Person with phone " + phone + " not found"));
    }

    /**
     * Replaces the contents of this list with the contents of the given UniquePersonList.
     *
     * @param replacement The UniquePersonList containing the replacement persons
     */
    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns all guests' names in the RSVP list.
     * @return List of all guests' names in the RSVP list
     */
    public List<Name> getAllPersonsNames() {
        return asUnmodifiableObservableList().stream()
            .map(person -> person.getName())
            .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniquePersonList)) {
            return false;
        }

        UniquePersonList otherUniquePersonList = (UniquePersonList) other;
        return internalList.equals(otherUniquePersonList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns the number of persons in the list.
     *
     * @return The number of persons in the list
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Returns true if the list contains no persons.
     *
     * @return True if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return internalList.size() <= 0;
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Person> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSamePerson(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
