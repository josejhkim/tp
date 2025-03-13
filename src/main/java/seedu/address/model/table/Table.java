package seedu.address.model.table;

import seedu.address.model.person.Guest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Table {
    private final int tableId;
    private int capacity;
    private List<Guest> guestList;

    public Table(int tableId, int capacity) {
        this.tableId = tableId;
        this.capacity = capacity;
        this.guestList = new ArrayList<>();
    }

    public int getTableId() {
        return tableId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Guest> getGuestList() {
        return guestList;
    }

    public void addGuest(Guest guest) {
        if (guestList.size() < capacity) {
            guestList.add(guest);
        } else {
            System.out.println("Table is full. Cannot add more guests.");
        }
    }

    @Override
    public String toString() {
        return "Table ID: " + tableId + ", Capacity: " + capacity;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(tableId, capacity);
    }

    // Convert to JSON for storage (Future Use)
    public String toJson() {
        return "{ \"tableId\": " + tableId + ", \"capacity\": " + capacity + " }";
    }

    // Convert from JSON to Table object (Future Use)
    public static Table fromJson(String json) {
        String[] parts = json.replace("{", "").replace("}", "").replace("\"", "").split(",");
        int tableId = Integer.parseInt(parts[0].split(":")[1].trim());
        int capacity = Integer.parseInt(parts[1].split(":")[1].trim());
        return new Table(tableId, capacity);
    }
}
