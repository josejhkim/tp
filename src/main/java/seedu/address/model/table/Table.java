package seedu.address.model.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Guest;

/**
 * Represents a table at a wedding event.
 */
public class Table {
    private final int tableId;
    private int capacity;
    private List<Guest> guestList;

    /**
     * Constructs a Table with an ID and capacity.
     *
     * @param tableId  The ID of the table.
     * @param capacity The maximum number of guests the table can hold.
     */
    public Table(int tableId, int capacity) {
        this.tableId = tableId;
        this.capacity = capacity;
        this.guestList = new ArrayList<>();
    }

    /**
     * Returns the table ID.
     *
     * @return The table ID.
     */
    public int getTableId() {
        return tableId;
    }

    /**
     * Returns the table capacity.
     *
     * @return The table capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the table capacity.
     *
     * @param capacity The new capacity of the table.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns the guest list for this table.
     *
     * @return A list of guests assigned to the table.
     */
    public List<Guest> getGuestList() {
        return guestList;
    }

    /**
     * Returns a string representation of the table.
     *
     * @return A formatted string containing table ID and capacity.
     */
    @Override
    public String toString() {
        return "Table ID: " + tableId + ", Capacity: " + capacity;
    }

    /**
     * Checks if this table is equal to another table.
     *
     * @param other The object to compare.
     * @return True if both tables have the same table ID and capacity.
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
        return tableId == table.tableId && capacity == table.capacity;
    }

    /**
     * Returns the hash code of the table.
     *
     * @return The hash code based on table ID and capacity.
     */
    @Override
    public int hashCode() {
        return Objects.hash(tableId, capacity);
    }

    /**
     * Converts the table object to a JSON-like string representation.
     *
     * @return A JSON-formatted string of the table.
     */
    public String toJson() {
        return "{ \"tableId\": " + tableId + ", \"capacity\": " + capacity + " }";
    }

    /**
     * Converts a JSON-formatted string into a Table object.
     *
     * @param json The JSON string representing a table.
     * @return A Table object with extracted values.
     */
    public static Table fromJson(String json) {
        String[] parts = json.replace("{", "").replace("}", "").replace("\"", "").split(",");
        int tableId = Integer.parseInt(parts[0].split(":")[1].trim());
        int capacity = Integer.parseInt(parts[1].split(":")[1].trim());
        return new Table(tableId, capacity);
    }
}
