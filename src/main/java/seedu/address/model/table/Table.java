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

    public void addGuest(Guest guestName) {
        if (guestList.size() < capacity) {
            guestList.add(guestName);
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
        } if (!(other instanceof Table)) {
            return false;
        }
        Table table = (Table) other;
        return tableId == table.tableId && capacity == table.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, capacity);
    }
}

