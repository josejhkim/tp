package seedu.address.model.table;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a seating table in a wedding.
 * Each table has a unique ID, a seating capacity, and a list of assigned guest names.
 * This class is immutable, meaning once a table is created, its properties cannot be modified.
 */
public final class Table {

    /** The unique identifier for the table. */
    private final int tableId;

    /** The maximum seating capacity of the table. */
    private final int capacity;

    /** A list of guest names assigned to this table. */
    private final List<String> guestIds;

    /**
     * Constructs a new {@code Table} with the given ID and capacity.
     * This constructor initializes an empty guest list.
     *
     * @param tableId  The unique identifier for the table. Must be a positive integer.
     * @param capacity The seating capacity of the table. Must be a positive integer.
     * @throws IllegalArgumentException if {@code tableId} or {@code capacity} is not positive.
     */
    public Table(int tableId, int capacity) {
        if (tableId <= 0 || capacity <= 0) {
            throw new IllegalArgumentException("Table ID and capacity must be positive integers.");
        }
        this.tableId = tableId;
        this.capacity = capacity;
        this.guestIds = Collections.emptyList(); // Immutable empty list
    }

    /**
     * Constructs a new {@code Table} with the given ID, capacity, and assigned guest names.
     *
     * @param tableId  The unique identifier for the table. Must be a positive integer.
     * @param capacity The seating capacity of the table. Must be a positive integer.
     * @param guestIds A list of guest names assigned to the table.
     * @throws IllegalArgumentException if {@code tableId} or {@code capacity} is not positive.
     */
    public Table(int tableId, int capacity, List<String> guestIds) {
        if (tableId <= 0 || capacity <= 0) {
            throw new IllegalArgumentException("Table ID and capacity must be positive integers.");
        }
        this.tableId = tableId;
        this.capacity = capacity;
        this.guestIds = Collections.unmodifiableList(guestIds); // Immutable list to prevent modification
    }

    /**
     * Returns the unique identifier for this table.
     *
     * @return The table ID.
     */
    public int getTableId() {
        return tableId;
    }

    /**
     * Returns the seating capacity of this table.
     *
     * @return The table's capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns an immutable list of guest names assigned to this table.
     *
     * @return A list of guest names assigned to the table.
     */
    public List<String> getGuestIds() {
        return guestIds;
    }

    /**
     * Returns an immutable list of guest names assigned to this table.
     * This is an alias for {@code getGuestIds()} to improve readability.
     *
     * @return A list of guest names assigned to the table.
     */
    public List<String> getGuestNames() {
        return guestIds;
    }

    /**
     * Checks whether this table is the same as another table based on the table ID.
     *
     * @param otherTable The table to compare.
     * @return {@code true} if both tables have the same ID, otherwise {@code false}.
     */
    public boolean isSameTable(Table otherTable) {
        return otherTable != null && this.tableId == otherTable.tableId;
    }

    /**
     * Returns a string representation of this table, showing its ID, capacity, and assigned guest names.
     *
     * @return A string representation of the table.
     */
    @Override
    public String toString() {
        return "Table{ID=" + tableId + ", Capacity=" + capacity + ", Guest Names=" + guestIds + "}";
    }

    /**
     * Compares this table to another object for equality.
     * Two tables are considered equal if they have the same table ID, capacity, and guest list.
     *
     * @param other The object to compare.
     * @return {@code true} if both tables are equal, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Table)) {
            return false;
        }
        Table table = (Table) other;
        return tableId == table.tableId && capacity == table.capacity && guestIds.equals(table.guestIds);
    }

    /**
     * Returns a hash code for this table, based on its ID, capacity, and guest list.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(tableId, capacity, guestIds);
    }
}
