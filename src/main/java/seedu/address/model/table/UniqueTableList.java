package seedu.address.model.table;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.table.exceptions.TableNotFoundException;

/**
 * Represents a unique list of tables in a wedding.
 * <p>
 * This class ensures that tables remain unique within the list.
 * It provides methods for adding, deleting, searching, and managing guests within tables.
 * </p>
 * <p>
 * This list does not allow duplicate tables based on {@code Table#isSameTable(Table)}.
 * </p>
 */
public class UniqueTableList implements Iterable<Table> {

    private final ObservableList<Table> internalList = FXCollections.observableArrayList();
    private final ObservableList<Table> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Creates a new empty UniqueTableList.
     */
    public UniqueTableList() {

    }

    /**
     * Creates a new UniqueTableList containing copies of the Tables in the given list.
     *
     * @param other The UniqueTableList to copy tables from
     */
    public UniqueTableList(UniqueTableList other) {
        for (Table t : other) {
            addTable(new Table(t));
        }
    }

    /**
     * Checks if a table with the same identity as {@code toCheck} exists in the list.
     *
     * @param toCheck The table to check.
     * @return {@code true} if the table exists, otherwise {@code false}.
     */
    public boolean contains(Table toCheck) {
        return internalList.stream().anyMatch(toCheck::isSameTable);
    }

    /**
     * Returns an iterator over the tables in this list.
     *
     * @return An iterator over tables in this list.
     */
    public Iterator<Table> iterator() {
        return internalList.iterator();
    }

    /**
     * Adds a new table to the list.
     * Ensures that the table does not already exist in the list.
     *
     * @param toAdd The table to add.
     * @throws IllegalArgumentException if a table with the same ID already exists.
     */
    public void addTable(Table toAdd) {
        if (contains(toAdd)) {
            throw new IllegalArgumentException("Table with ID " + toAdd.getTableId() + " already exists.");
        }
        internalList.add(toAdd);
    }

    /**
     * Deletes a table from the list by its ID.
     *
     * @param tableId The ID of the table to be deleted.
     * @throws TableNotFoundException if the table does not exist.
     */
    public void deleteTable(int tableId) {
        Table table = findTable(tableId);
        if (table != null) {
            internalList.remove(table);
            return;
        }
        throw new TableNotFoundException();
    }

    /**
     * Deletes a table from the list.
     *
     * @param table The table to be deleted.
     */
    public void deleteTable(Table table) {
        internalList.remove(table);
    }

    /**
     * Finds a table in the list by its ID.
     *
     * @param tableId The ID of the table to find.
     * @return An {@code Optional} containing the table if found, otherwise an empty {@code Optional}.
     */
    public Table findTable(int tableId) throws TableNotFoundException {
        try {
            return internalList.stream().filter(table -> table.getTableId() == tableId)
                .findFirst().get();
        } catch (NoSuchElementException nsee) {
            throw new TableNotFoundException();
        }
    }

    /**
     * Finds a table in the list with the same table ID as the provided table.
     *
     * @param table The table containing the ID to find.
     * @return The table if found, otherwise null.
     */
    public Table findTable(Table table) {
        try {
            return internalList.stream().filter(t -> t.getTableId() == table.getTableId())
                .findFirst().get();
        } catch (NoSuchElementException nsee) {
            return null;
        }
    }

    /**
     * Checks whether a table with the given ID exists in the list.
     *
     * @param tableId The ID of the table to check.
     * @return {@code true} if the table exists, otherwise {@code false}.
     */
    public boolean hasTable(int tableId) {
        return internalList.stream().anyMatch(table -> table.getTableId() == tableId);
    }

    /**
     * Assigns a guest to a table based on their name.
     * <p>
     * The method ensures that the table exists and that the guest is present in the RSVP list.
     * If the table is full, an exception is thrown.
     * </p>
     *
     * @param tableId  The ID of the table.
     * @param guest The new guest to be added in this table.
     * @throws TableNotFoundException if the table does not exist.
     * @throws IllegalArgumentException if the guest is not found or the table is full.
     */
    public void assignGuestToTable(int tableId, Person guest) {
        Table table = findTable(tableId);
        if (table == null) {
            throw new TableNotFoundException();
        }

        UniquePersonList personList = new UniquePersonList();

        for (Person g : table.getGuests()) {
            personList.add(g);
        }

        personList.add(guest);

        if (personList.size() >= table.getCapacity()) {
            throw new IllegalArgumentException("Table is full.");
        }

        Table updatedTable = new Table(table.getTableId(), table.getCapacity(), personList);
        internalList.set(internalList.indexOf(table), updatedTable);
    }

    /**
     * Assigns a guest to a specified table.
     *
     * @param table The table to assign the guest to.
     * @param guest The guest to be assigned.
     */
    public void assignGuestToTable(Table table, Person guest) {
        assignGuestToTable(table.getTableId(), guest);
    }

    /**
     * Removes a guest from a table.
     * <p>
     * The method finds the specified table, removes the guest, and updates the table list.
     * </p>
     *
     * @param tableId  The ID of the table.
     * @param guest  The guest to be added.
     * @throws TableNotFoundException if the table does not exist.
     */
    public void deleteGuestFromTable(int tableId, Person guest) {
        Table table = findTable(tableId);
        if (table == null) {
            throw new TableNotFoundException();
        }

        UniquePersonList personList = new UniquePersonList();

        table.getGuests().stream()
                .filter(g -> !g.equals(guest))
                .forEach(g -> personList.add(g));

        Table updatedTable = new Table(table.getTableId(), table.getCapacity(), personList);
        internalList.set(internalList.indexOf(table), updatedTable);
    }

    /**
     * Sets a target table to an edited table with the same identity.
     * The target table must exist in the list.
     *
     * @param target The table to be replaced.
     * @param editedTable The edited version of the table.
     * @throws TableNotFoundException if the target table is not found.
     * @throws DuplicatePersonException if the edited table is a duplicate of an existing table.
     */
    public void setTable(Table target, Table editedTable) {
        requireAllNonNull(target, editedTable);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TableNotFoundException();
        }

        if (!target.isSameTable(editedTable) && contains(editedTable)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedTable);
    }

    /**
     * Returns the list of tables as an unmodifiable {@code ObservableList}.
     * This ensures that the list cannot be modified externally.
     *
     * @return An unmodifiable list of tables.
     */
    public ObservableList<Table> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UniqueTableList)) {
            return false;
        }
        UniqueTableList otherUniqueTableList = (UniqueTableList) other;

        return internalList.equals(otherUniqueTableList.internalList);
    }

    /**
     * Returns the number of tables in this list.
     *
     * @return The number of tables in this list.
     */
    public int size() {
        return internalList.size();
    }
}
