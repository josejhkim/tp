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

    /**
     * Manages the list of weddings in the address book.
     */
    private UniqueWeddingList uniqueWeddingList;

    private Wedding currentWedding;

    /**
     * Initializes the address book with a default wedding and sets it as the current wedding.
     */
    public AddressBook() {
        this.currentWedding = new Wedding("Wedding");
        this.addWedding(this.currentWedding);
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     *
     * @param toBeCopied the ReadOnlyAddressBook to copy from
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
     *
     * @param persons the UniquePersonList to set
     */
    public void setPersons(UniquePersonList persons) {
        this.currentWedding.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     *
     * @param newData the new data to reset to
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        uniqueWeddingList = new UniqueWeddingList();

        setWeddings(newData.getWeddingList());

        if (newData.getCurrentWedding() != null) {
            setCurrentWeddingByName(newData.getCurrentWedding().getName());
        }
    }

    //=========== Weddings ================================================================================

    /**
     * Adds a wedding to the address book.
     *
     * @param wedding the Wedding to be added
     */
    public void addWedding(Wedding wedding) {
        this.uniqueWeddingList.addWedding(wedding);
    }

    /**
     * Creates a new wedding with the specified name and adds it to the address book.
     *
     * @param weddingName the name of the new wedding
     */
    public void createWedding(String weddingName) {
        Wedding newWedding = new Wedding(weddingName);
        addWedding(newWedding);
    }

    /**
     * Sets the active wedding in the address book.
     * Replaces any existing wedding.
     *
     * @param wedding the Wedding to set as current
     * @throws WeddingNotFoundException if the wedding does not exist in the address book
     */
    public void setCurrentWedding(Wedding wedding) {
        requireNonNull(wedding);
        if (this.uniqueWeddingList.findWeddingByName(wedding.getName()) == null) {
            throw new WeddingNotFoundException();
        }
        this.currentWedding = wedding;
    }

    /**
     * Sets the current wedding by its name.
     *
     * @param weddingName the name of the wedding to set as current
     */
    public void setCurrentWeddingByName(String weddingName) {
        Wedding weddingWithMatchingName = uniqueWeddingList.findWeddingByName(weddingName);

        setCurrentWedding(weddingWithMatchingName);
    }

    /**
     * Retrieves the currently active wedding.
     *
     * @return the current Wedding
     */
    public Wedding getCurrentWedding() {
        return this.currentWedding;
    }

    /**
     * Deletes the specified wedding from the address book.
     * If it is the current wedding, it sets the current wedding to another one if available.
     *
     * @param wedding the Wedding to delete
     */
    public void deleteWedding(Wedding wedding) {
        if (uniqueWeddingList.size() > 1) {
            uniqueWeddingList.deleteWedding(wedding);

            if (wedding.equals(this.currentWedding)) {
                this.currentWedding = uniqueWeddingList.asUnmodifiableObservableList().stream().findAny().get();
            }
            return;
        }

        if (uniqueWeddingList.size() == 1) {
            uniqueWeddingList.deleteWedding(wedding);
            Wedding defaultWedding = new Wedding("Wedding");
            uniqueWeddingList.addWedding(wedding);
            setCurrentWedding(wedding);
        }
    }

    /**
     * Deletes the current wedding from the address book.
     */
    public void deleteCurrentWedding() {
        deleteWedding(currentWedding);
    }

    /**
     * Deletes a wedding by its name.
     *
     * @param weddingName the name of the wedding to delete
     */
    public void deleteWeddingByName(String weddingName) {
        Wedding weddingWithMatchingName = uniqueWeddingList.findWeddingByName(weddingName);
        deleteWedding(weddingWithMatchingName);
    }

    /**
     * Checks if a wedding exists.
     *
     * @return true if a wedding exists, false otherwise
     */
    public boolean hasWedding() {
        return currentWedding != null;
    }

    /**
     * Checks if a specific wedding exists in the address book.
     *
     * @param wedding the Wedding to check for
     * @return true if the wedding exists, false otherwise
     */
    public boolean hasWedding(Wedding wedding) {
        return uniqueWeddingList.contains(wedding);
    }

    /**
     * Sets a wedding in the address book.
     *
     * @param wedding the Wedding to set
     * @param target the Wedding to be replaced
     */
    public void setWedding(Wedding wedding, Wedding target) {
        this.uniqueWeddingList.setWedding(wedding, target);
    }

    /**
     * Sets multiple weddings in the address book.
     *
     * @param weddings the List of Weddings to set
     */
    public void setWeddings(List<Wedding> weddings) {
        for (Wedding wedding : weddings) {
            Wedding toBeAdded = new Wedding(wedding);
            this.uniqueWeddingList.addWedding(toBeAdded);
        }
    }

    /**
     * Sets multiple weddings from a UniqueWeddingList.
     *
     * @param uniqueWeddingList the UniqueWeddingList to set weddings from
     */
    public void setWeddings(UniqueWeddingList uniqueWeddingList) {
        setWeddings(uniqueWeddingList.asUnmodifiableObservableList());
    }

    //=========== Persons ================================================================================

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     *
     * @param person the Person to check for
     * @return true if the person exists, false otherwise
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return currentWedding.hasPerson(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     *
     * @param person the Person to add
     */
    public void addPerson(Person person) {
        requireNonNull(person);
        currentWedding.addPerson(person);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     *
     * @param target the Person to be replaced
     * @param editedPerson the new Person to replace the target
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        currentWedding.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     *
     * @param key the Person to remove
     */
    public void deletePerson(Person key) {
        currentWedding.deletePerson(key);
    }

    /**
     * Finds a person by their name.
     *
     * @param name the Name of the person to find
     * @return the Person with the matching name
     */
    public Person findPersonByName(Name name) {
        return currentWedding.findPersonByName(name);
    }

    //=========== Tables ================================================================================

    /**
     * Checks if a table exists in the current wedding.
     *
     * @param table the Table to check for
     * @return true if the table exists, false otherwise
     */
    public boolean hasTable(Table table) {
        return currentWedding.hasTable(table);
    }

    /**
     * Checks if a table exists by its ID.
     *
     * @param tableId the ID of the table to check for
     * @return true if the table exists, false otherwise
     */
    public boolean hasTable(int tableId) {
        return currentWedding.hasTable(tableId);
    }

    /**
     * Adds a table to the current wedding.
     *
     * @param table the Table to add
     */
    public void addTable(Table table) {
        currentWedding.addTable(table);
    }

    /**
     * Deletes a specified table from the current wedding.
     *
     * @param table the Table to delete
     */
    public void deleteTable(Table table) {
        currentWedding.deleteTable(table);
    }

    /**
     * Deletes a table from the current wedding by its ID.
     *
     * @param tableId the ID of the table to delete
     */
    public void deleteTable(int tableId) {
        currentWedding.deleteTable(tableId);
    }

    /**
     * Sets a table in the current wedding.
     *
     * @param target the Table to be replaced
     * @param editedTable the new Table to replace the target
     */
    public void setTable(Table target, Table editedTable) {
        requireNonNull(editedTable);
        currentWedding.setTable(target, editedTable);
    }

    /**
     * Adds a person to a specified table.
     *
     * @param p the Person to add
     * @param table the Table to add the person to
     */
    public void addPersonToTable(Person p, Table table) {
        this.currentWedding.addPersonToTable(p, table);
    }

    /**
     * Adds a person to a specified table by its ID.
     *
     * @param p the Person to add
     * @param tableId the ID of the Table to add the person to
     */
    public void addPersonToTable(Person p, int tableId) {
        this.currentWedding.addPersonToTable(p, tableId);
    }

    /**
     * Deletes a person from a specified table.
     *
     * @param p the Person to delete
     * @param table the Table to delete the person from
     */
    public void deletePersonFromTable(Person p, Table table) {
        this.currentWedding.deletePersonFromTable(p, table);
    }

    /**
     * Deletes a person from a specified table by its ID.
     *
     * @param p the Person to delete
     * @param tableId the ID of the Table to delete the person from
     */
    public void deletePersonFromTable(Person p, int tableId) {
        this.currentWedding.deletePersonFromTable(p, tableId);
    }

    /**
     * Retrieves a table by its ID.
     *
     * @param tableId the ID of the table to retrieve
     * @return the Table with the matching ID
     */
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
