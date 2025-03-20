package seedu.address.model.wedding;

import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.RsvpList;
import seedu.address.model.person.exceptions.GuestNotFoundException;
import seedu.address.model.table.UniqueTableList;

/**
 * Represents a Wedding in the address book.
 */
public class Wedding {
    private final String name;
    private final RsvpList rsvpList;
    private final UniqueTableList tableList; // All tables for seating
    /**
     * Constructs a {@code Wedding}.
     *
     * @param name A wedding name.
     */
    public Wedding(String name) {
        this.name = name;
        this.rsvpList = new RsvpList();
        this.tableList = new UniqueTableList();
    }

    public String getName() {
        return name;
    }

    public RsvpList getRsvpList() {
        return rsvpList;
    }

    public UniqueTableList getTableList() {
        return tableList;
    }

    public void addGuest(Guest guest) {
        rsvpList.add(guest);
    }

    public void removeGuest(Guest guest) {
        rsvpList.remove(guest);
    }

    public void setGuest(Guest target, Guest editedGuest) {
        rsvpList.setGuest(target, editedGuest);
    }

    public Guest findGuestByPhone(Phone phone) throws CommandException {
        return rsvpList.getGuestByPhone(phone);
    }

    /**
     * Finds a guest from the wedding with the matching name.
     * Throws a GuestNotFoundException if there isn't any
     * @param name Name of the guest to find
     * @return A Guest object with the corresponding name
     * @throws GuestNotFoundException
     */
    public Guest findGuestByName(Name name) throws GuestNotFoundException {
        for (Guest g : rsvpList.getAllGuests()) {
            if (g.getName().equals(name)) {
                return g;
            }
        }
        throw new GuestNotFoundException("Guest with the given name doesn't exist!");
    }

    public boolean hasGuest(Guest guest) {
        return this.rsvpList.contains(guest);
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
            && Objects.equals(rsvpList, otherWedding.rsvpList)
            && Objects.equals(tableList, otherWedding.tableList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rsvpList, tableList);
    }

    @Override
    public String toString() {
        return "Wedding{name='" + name + "', rsvpList=" + rsvpList + '}';
    }
}
