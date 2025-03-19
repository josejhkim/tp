package seedu.address.model.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Guest;
import seedu.address.model.person.RsvpList;
import seedu.address.model.table.exceptions.TableNotFoundException;

/**
 * Manages a unique list of tables.
 */
public class UniqueTableList {

    private final ObservableList<Table> internalList = FXCollections.observableArrayList();
    private final ObservableList<Table> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public boolean contains(Table toCheck) {
        return internalList.stream().anyMatch(toCheck::isSameTable);
    }

    public void addTable(Table toAdd) {
        if (contains(toAdd)) {
            throw new IllegalArgumentException("Table with ID " + toAdd.getTableId() + " already exists.");
        }
        internalList.add(toAdd);
    }

    public void deleteTable(int tableId) {
        Table table = findTableById(tableId).orElseThrow(TableNotFoundException::new);
        internalList.remove(table);
    }

    public Optional<Table> findTableById(int tableId) {
        return internalList.stream().filter(table -> table.getTableId() == tableId).findFirst();
    }

    /**
     * Returns true if a table with the given table ID exists in the list.
     *
     * @param tableId The table ID to check.
     * @return True if the table exists, false otherwise.
     */
    public boolean hasTable(int tableId) {
        return internalList.stream().anyMatch(table -> table.getTableId() == tableId);
    }

    public void assignGuestToTable(int tableId, String guestName, RsvpList rsvpList) {
        Table table = findTableById(tableId).orElseThrow(TableNotFoundException::new);
        Guest guest = rsvpList.getGuestByName(guestName)
                .orElseThrow(() -> new IllegalArgumentException("Guest named '" + guestName + "' not found."));

        if (table.getGuestNames().size() >= table.getCapacity()) {
            throw new IllegalArgumentException("Table is full.");
        }

        // Create a new table with the updated guest list
        List<String> newGuestNames = new ArrayList<>(table.getGuestNames());
        newGuestNames.add(guestName);

        Table updatedTable = new Table(table.getTableId(), table.getCapacity(), newGuestNames);
        internalList.set(internalList.indexOf(table), updatedTable);
    }




    public void removeGuestFromTable(int tableId, String guestId) {
        Table table = findTableById(tableId).orElseThrow(TableNotFoundException::new);

        List<String> newGuestIds = table.getGuestIds().stream()
                .filter(id -> id != guestId)
                .collect(Collectors.toList());

        Table updatedTable = new Table(table.getTableId(), table.getCapacity(), newGuestIds);
        internalList.set(internalList.indexOf(table), updatedTable);
    }


    public ObservableList<Table> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }


}
