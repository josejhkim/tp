package seedu.address.model.wedding;

import java.util.Objects;

import seedu.address.model.person.Guest;
import seedu.address.model.person.RsvpList;
import seedu.address.model.table.TableList;
/**
 * Represents a Wedding in the address book.
 */
public class Wedding {
    private final String name;
    private final RsvpList rsvpList;
    private final TableList tableList; // All tables for seating
    /**
     * Constructs a {@code Wedding}.
     *
     * @param name A wedding name.
     */
    public Wedding(String name) {
        this.name = name;
        this.rsvpList = new RsvpList();
        this.tableList = new TableList();
    }

    public String getName() {
        return name;
    }

    public RsvpList getRsvpList() {
        return rsvpList;
    }

    public TableList getTableList() {
        return tableList;
    }

    public void addGuest(Guest guest) {
        rsvpList.add(guest);
    }

    public void removeGuest(Guest guest) {
        rsvpList.remove(guest);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Wedding)) {
            return false;
        }
        Wedding otherWedding = (Wedding) other;
        return Objects.equals(name, otherWedding.name)
            && Objects.equals(rsvpList, otherWedding.rsvpList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rsvpList);
    }

    @Override
    public String toString() {
        return "Wedding{name='" + name + "', rsvpList=" + rsvpList + '}';
    }
}
