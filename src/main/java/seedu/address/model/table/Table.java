package seedu.address.model.table;

import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Guest;
import seedu.address.model.person.Name;
import seedu.address.model.person.RsvpList;

/**
 * Represents a seating table in a wedding.
 * Each table has a unique ID, a seating capacity, and a list of assigned guest names.
 * This class is immutable, meaning once a table is created, its properties cannot be modified.
 */
public final class Table {

    public static final String ID_CONSTRAINTS = "The table ID should be a positive integer";
    public static final int MAX_CAPACITY = 10000;

    /** The unique identifier for the table. */
    private final int tableId;

    /** The maximum seating capacity of the table. */
    private final int capacity;

    private final RsvpList rsvpList;

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
        this.rsvpList = new RsvpList();
    }

    /**
     * Constructs a new {@code Table} with the given ID, capacity, and assigned guest names.
     *
     * @param tableId  The unique identifier for the table. Must be a positive integer.
     * @param capacity The seating capacity of the table. Must be a positive integer.
     * @throws IllegalArgumentException if {@code tableId} or {@code capacity} is not positive.
     */
    public Table(int tableId, int capacity, RsvpList rsvpList) {
        if (tableId <= 0 || capacity <= 0) {
            throw new IllegalArgumentException("Table ID and capacity must be positive integers.");
        }
        this.tableId = tableId;
        this.capacity = capacity;
        this.rsvpList = rsvpList;
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

    public List<Guest> getGuests() {
        return this.rsvpList.getAllGuests();
    }

    /**
     * Returns an immutable list of guest names assigned to this table.
     * This is an alias for {@code getGuestIds()} to improve readability.
     *
     * @return A list of guest names assigned to the table.
     */
    public List<Name> getGuestNames() {
        return this.rsvpList.getAllGuestNames();
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
        return "Table{ID=" + tableId + ", Capacity=" + capacity + ", Guest Names="
            + rsvpList + "}";
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
        return tableId == table.tableId && capacity == table.capacity
            && table.rsvpList.equals(((Table) other).rsvpList);
    }

    /**
     * Returns a hash code for this table, based on its ID, capacity, and guest list.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(tableId, capacity, rsvpList);
    }
}
