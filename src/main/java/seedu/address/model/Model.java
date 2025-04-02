package seedu.address.model;

import java.beans.PropertyChangeListener;
import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.table.Table;
import seedu.address.model.wedding.Wedding;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Table> PREDICATE_SHOW_ALL_TABLES = unused -> true;


    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    //=========== Person ==================================================================================

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    Person findPersonByName(Name name);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    //=========== Table ==================================================================================
    ObservableList<Table> getFilteredTableList();

    void updateFilteredTableList(Predicate<Table> predicate);

    void addTable(Table table);

    void deleteTable(Table table);

    void deleteTableById(int tableId);

    void addPersonToTable(Person p, Table table);

    void addPersonToTableById(Person p, int tableId);

    void deletePersonFromTable(Person p, Table table);

    void deletePersonFromTableById(Person p, int tableId);

    void setTable(Table target, Table editedTable);

    Table findTableById(int tableId);

    boolean hasTable(int tableId);

    boolean hasCurrentWedding();

    //=========== Wedding ==================================================================================
    /**
     * Adds the given wedding.
     * {@code wedding} must not already exist in the address book.
     */
    void addWedding(Wedding wedding);

    void deleteCurrentWedding();

    /**
     * Deletes the given wedding.
     * The wedding must exist in the address book.
     */
    void deleteWedding(Wedding wedding);

    void deleteWeddingByName(String weddingName);

    /**
     * Sets the current wedding.
     */
    void setCurrentWedding(Wedding wedding);

    void setCurrentWeddingByName(String weddingName);
    /**
     * Returns the current wedding.
     */
    Wedding getCurrentWedding();

    Wedding findWeddingByName(String weddingName);

    /**
     * Adds a property change listener to listen for model events
     * @param listener the listener to add
     */
    void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Removes a property change listener
     * @param listener the listener to remove
     */
    void removePropertyChangeListener(PropertyChangeListener listener);

    boolean hasWeddingWithName(String weddingName);
}
