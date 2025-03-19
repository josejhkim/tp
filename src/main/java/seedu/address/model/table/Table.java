package seedu.address.model.table;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a seating table in a wedding.
 * Each table has a unique ID, a seating capacity, and a list of assigned guest IDs.
 * This class is immutable.
 */
public final class Table {

    private final int tableId;
    private final int capacity;
    private final List<String> guestIds;

    public Table(int tableId, int capacity) {
        if (tableId <= 0 || capacity <= 0) {
            throw new IllegalArgumentException("Table ID and capacity must be positive integers.");
        }
        this.tableId = tableId;
        this.capacity = capacity;
        this.guestIds = Collections.emptyList();
    }

    public Table(int tableId, int capacity, List<String> guestIds) {
        if (tableId <= 0 || capacity <= 0) {
            throw new IllegalArgumentException("Table ID and capacity must be positive integers.");
        }
        this.tableId = tableId;
        this.capacity = capacity;
        this.guestIds = Collections.unmodifiableList(guestIds);
    }

    public int getTableId() { return tableId; }
    public int getCapacity() { return capacity; }
    public List<String> getGuestIds() { return guestIds; }

    public List<String> getGuestNames() {
        return guestIds;
    }


    public boolean isSameTable(Table otherTable) {
        return otherTable != null && this.tableId == otherTable.tableId;
    }

    @Override
    public String toString() {
        return "Table{ID=" + tableId + ", Capacity=" + capacity + ", Guest IDs=" + guestIds + "}";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Table)) return false;
        Table table = (Table) other;
        return tableId == table.tableId && capacity == table.capacity && guestIds.equals(table.guestIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, capacity, guestIds);
    }
}
