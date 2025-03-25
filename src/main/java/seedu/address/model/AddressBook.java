package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.table.Table;
import seedu.address.model.wedding.UniqueWeddingList;
import seedu.address.model.wedding.Wedding;
import seedu.address.model.wedding.exceptions.WeddingNotFoundException;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private Wedding currentWedding;

    private UniqueWeddingList uniqueWeddingList = new UniqueWeddingList();
//    /*
//     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
//     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
//     *
//     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
//     *   among constructors.
//     */
//    {
//        uniqueWeddingList = new UniqueWeddingList();
//    }

    public AddressBook() {
        
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        resetData(toBeCopied);
    }

    @Override
    public ObservableList<Wedding> getWeddingList() {
        return this.uniqueWeddingList.asUnmodifiableObservableList();
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(UniquePersonList persons) {
        this.currentWedding.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        // Create a new wedding list to avoid issues with default wedding
        uniqueWeddingList = new UniqueWeddingList();
        
        // Add all weddings from newData, ensuring no duplicates
        for (Wedding wedding : newData.getWeddingList()) {
            if (!hasWedding(wedding)) {
                Wedding toBeAdded = new Wedding(wedding);
                this.uniqueWeddingList.addWedding(toBeAdded);
            }
        }

        // Set current wedding if available
        if (newData.getCurrentWedding() != null && !uniqueWeddingList.asUnmodifiableObservableList().isEmpty()) {
            try {
                setCurrentWeddingByName(newData.getCurrentWedding().getName());
            } catch (WeddingNotFoundException e) {
                // If the wedding name doesn't exist, set to first wedding
                setCurrentWedding(uniqueWeddingList.asUnmodifiableObservableList().get(0));
            }
        }
    }

    //=========== Weddings ================================================================================

    public void addWedding(Wedding wedding) {
        this.uniqueWeddingList.addWedding(wedding);
    }

    public void createWedding(String weddingName) {
        Wedding newWedding = new Wedding(weddingName);
        addWedding(newWedding);
    }

    /**
     * Sets the active wedding in the address book.
     * Replaces any existing wedding.
     */
    public void setCurrentWedding(Wedding wedding) {
        requireNonNull(wedding);
        if (this.uniqueWeddingList.findWeddingByName(wedding.getName()) == null) {
            throw new WeddingNotFoundException();
        }
        this.currentWedding = wedding;
    }

    public void setCurrentWeddingByName(String weddingName) {
        Wedding weddingWithMatchingName = uniqueWeddingList.findWeddingByName(weddingName);

        setCurrentWedding(weddingWithMatchingName);
    }

    /**
     * Retrieves the currently active wedding.
     */
    public Wedding getCurrentWedding() {
        return this.currentWedding;
    }

    public void deleteWedding(Wedding wedding) {
        if (uniqueWeddingList.size() > 1) {
            uniqueWeddingList.deleteWedding(wedding);

            if (wedding.equals(this.currentWedding)) {
                this.currentWedding = uniqueWeddingList.asUnmodifiableObservableList().
                    stream().findAny().get();
            }
            return;
        }

        if (uniqueWeddingList.size() == 1) {
            uniqueWeddingList.deleteWedding(wedding);
            Wedding defaultWedding = new Wedding("Wedding");
            uniqueWeddingList.addWedding(defaultWedding);
            setCurrentWedding(defaultWedding);
        }
    }

    public void deleteCurrentWedding() {
        deleteWedding(currentWedding);
    }

    public void deleteWeddingByName(String weddingName) {
        Wedding weddingWithMatchingName = uniqueWeddingList.findWeddingByName(weddingName);
        deleteWedding(weddingWithMatchingName);
    }

    /**
     * Checks if a wedding exists.
     */
    public boolean hasWedding() {
        return currentWedding != null;
    }

    public boolean hasWedding(Wedding wedding) {
        return uniqueWeddingList.contains(wedding);
    }

    public void setWedding(Wedding wedding, Wedding target) {
        this.uniqueWeddingList.setWedding(wedding, target);
    }

    public void setWeddings(List<Wedding> weddings) {
        for (Wedding wedding : weddings) {
            Wedding toBeAdded = new Wedding(wedding);
            this.uniqueWeddingList.addWedding(toBeAdded);
        }
    }

    public void setWeddings(UniqueWeddingList uniqueWeddingList) {
        setWeddings(uniqueWeddingList.asUnmodifiableObservableList());
    }

    //=========== Persons ================================================================================

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return currentWedding.hasPerson(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person person) {
        //Old implementation
        requireNonNull(person);
        currentWedding.addPerson(person);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        currentWedding.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void deletePerson(Person key) {
        currentWedding.deletePerson(key);
    }

    public Person findPersonByName(Name name) {
        return currentWedding.findPersonByName(name);
    }

    //=========== Tables ================================================================================

    public boolean hasTable(Table table) {
        return currentWedding.hasTable(table);
    }

    public boolean hasTable(int tableId) {
        return currentWedding.hasTable(tableId);
    }

    public void addTable(Table table) {
        currentWedding.addTable(table);
    }

    public void deleteTable(Table table) {
        currentWedding.deleteTable(table);
    }

    public void deleteTable(int tableId) {
        currentWedding.deleteTable(tableId);
    }

    public void setTable(Table target, Table editedTable) {
        requireNonNull(editedTable);
        currentWedding.setTable(target, editedTable);
    }

    public void addPersonToTable(Person p, Table table) {
        this.currentWedding.addPersonToTable(p, table);
    }

    public void addPersonToTable(Person p, int tableId) {
        this.currentWedding.addPersonToTable(p, tableId);
    }

    public void deletePersonFromTable(Person p, Table table) {
        this.currentWedding.deletePersonFromTable(p, table);
    }

    public void deletePersonFromTable(Person p, int tableId) {
        this.currentWedding.deletePersonFromTable(p, tableId);
    }

    public Table getTable(int tableId) {
        return currentWedding.getTable(tableId);
    }

    //=========== Utils ================================================================================

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("weddings", uniqueWeddingList)
                .add("current wedding", currentWedding)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return currentWedding.getUniquePersonList().asUnmodifiableObservableList();
    }

    public ObservableList<Table> getTableList() {
        return currentWedding.getTableList().asUnmodifiableObservableList();
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
        return uniqueWeddingList.equals(otherAddressBook.uniqueWeddingList);

    }


    @Override
    public int hashCode() {
        return uniqueWeddingList.hashCode();
    }


}
