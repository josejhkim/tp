package seedu.address.model.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Guest;

/**
 * Represents a seating table in a wedding.
 * Each table has a unique table ID, a seating capacity, and a list of guests currently assigned.
 */
public class Table {

    /** Unique identifier for the table. */
    private final int tableId;

    /** Maximum seating capacity for the table. */
    private int capacity;

    /** List of guests assigned to this table. */
    private final List<Guest> guestList;

    /**
     * Constructs a {@code Table} with the given table ID and capacity.
     *
     * @param tableId The unique identifier for the table.
     * @param capacity The seating capacity for the table.
     */
    public Table(int tableId, int capacity) {
        this.tableId = tableId;
        this.capacity = capacity;
        this.guestList = new ArrayList<>();
    }

    /**
     * Returns the unique table ID for this table.
     *
     * @return The table ID.
     */
    public int getTableId() {
        return tableId;
    }

    /**
     * Returns the seating capacity of this table.
     *
     * @return The seating capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets a new seating capacity for this table.
     *
     * @param capacity The new seating capacity.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns the list of guests currently assigned to this table.
     *
     * @return A list of {@code Guest} objects assigned to the table.
     */
    public List<Guest> getGuestList() {
        return guestList;
    }

    /**
     * Returns a string representation of this table, showing its ID, capacity, and current guest count.
     * <p>
     * Example format: {@code "Table{ID=1, Capacity=8, Guests=0}"}.
     *
     * @return A formatted string representing the table.
     */
    @Override
    public String toString() {
        return "Table{ID=" + tableId
                + ", Capacity=" + capacity
                + ", Guests=" + guestList.size() + "}";
    }

    /**
     * Checks whether this table is equal to another object.
     * Two tables are considered equal if they have the same {@code tableId} and {@code capacity}.
     *
     * @param other The object to compare.
     * @return True if both tables share the same ID and capacity; false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Table)) {
            return false;
        }
        Table t = (Table) other;
        return tableId == t.tableId && capacity == t.capacity;
    }

    /**
     * Returns the hash code for this table, based on its ID and capacity.
     *
     * @return The hash code derived from table ID and capacity.
     */
    @Override
    public int hashCode() {
        return Objects.hash(tableId, capacity);
    }
}
