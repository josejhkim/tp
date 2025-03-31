package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.table.Table;
import seedu.address.model.wedding.Wedding;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Table> filteredTables;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);

        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.filteredTables = new FilteredList<>(this.addressBook.getTableList());
    }

    /**
     * Creates a blank new ModelManager.
     */
    public ModelManager() {
        this.addressBook = new AddressBook();
        this.userPrefs = new UserPrefs();
        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.filteredTables = new FilteredList<>(this.addressBook.getTableList());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public ObservableList<Table> getFilteredTableList() {
        return filteredTables;
    }

    @Override
    public void updateFilteredTableList(Predicate<Table> predicate) {
        requireNonNull(predicate);
        filteredTables.setPredicate(predicate);
    }

    //=========== Persons ================================================================================

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.deletePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public Person findPersonByName(Name name) {
        return addressBook.findPersonByName(name);
    }

    //=========== Tables ================================================================================

    @Override
    public void addTable(Table table) {
        addressBook.addTable(table);
    }

    @Override
    public void deleteTable(Table table) {
        addressBook.deleteTable(table);
    }

    @Override
    public void deleteTableById(int tableId) {
        addressBook.deleteTable(tableId);
    }

    @Override
    public void setTable(Table target, Table editedTable) {
        requireAllNonNull(target, editedTable);
        addressBook.setTable(target, editedTable);
    }

    @Override
    public boolean hasTable(int tableId) {
        return addressBook.hasTable(tableId);
    }


    @Override
    public void addPersonToTable(Person p, Table table) {
        addressBook.addPersonToTable(p, table);
    }

    @Override
    public void addPersonToTableById(Person p, int tableId) {
        addressBook.addPersonToTable(p, tableId);
    }

    @Override
    public void deletePersonFromTable(Person p, Table table) {
        addressBook.deletePersonFromTable(p, table);
    }

    @Override
    public void deletePersonFromTableById(Person p, int tableId) {
        addressBook.deletePersonFromTable(p, tableId);
    }

    @Override
    public Table getTableById(int tableId) {
        return addressBook.getTable(tableId);
    }

    //=========== Wedding ================================================================================
    /**
     * Adds a Wedding to the system. Only one Wedding can exist at a time.
     */
    @Override
    public void addWedding(Wedding wedding) {
        requireNonNull(wedding);
        //        if (addressBook.getWedding() != null) {
        //            throw new IllegalStateException("A wedding already exists. Cannot create another.");
        //        }
        addressBook.addWedding(wedding);
    }

    @Override
    public void deleteCurrentWedding() {
        addressBook.deleteCurrentWedding();
    }

    @Override
    public void deleteWedding(Wedding wedding) {
        addressBook.deleteWedding(wedding);
    }

    @Override
    public void deleteWeddingByName(String weddingName) {
        addressBook.deleteWeddingByName(weddingName);
    }

    /**
     * Returns the current Wedding.
     */
    @Override
    public Wedding getCurrentWedding() {
        return addressBook.getCurrentWedding();
    }

    @Override
    public Wedding getWeddingByName(String weddingName) {
        return addressBook.getWeddingByName(weddingName);
    }

    @Override
    public void setCurrentWedding(Wedding wedding) {
        requireNonNull(wedding);
        addressBook.setCurrentWedding(wedding);
    }

    @Override
    public void setCurrentWeddingByName(String weddingName) {
        addressBook.setCurrentWeddingByName(weddingName);
    }

    @Override
    public boolean hasCurrentWedding() {
        return addressBook.hasCurrentWedding();
    }



    //=========== Other Utils ================================================================================

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
            && userPrefs.equals(otherModelManager.userPrefs)
            && filteredPersons.equals(otherModelManager.filteredPersons);
    }
}
