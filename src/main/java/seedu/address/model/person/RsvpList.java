package seedu.address.model.person;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * A list of guests who have been RSVPed to the event.
 * Contains both guests who have RSVPed "yes" and "no".
 */
public class RsvpList extends UniquePersonList {
    @Override
    public void add(Person toAdd) {
        if (!(toAdd instanceof Guest)) {
            throw new IllegalArgumentException("Only guests can be added to the RSVP list.");
        }
        super.add(toAdd);
    }

    @Override
    public boolean contains(Person toCheck) {
        if (!(toCheck instanceof Guest)) {
            return false;
        }
        return super.contains(toCheck);
    }

    @Override
    public void remove(Person toRemove) {
        if (!(toRemove instanceof Guest)) {
            throw new IllegalArgumentException("Only guests can be removed from the RSVP list.");
        }
        super.remove(toRemove);
    }
    /*
    * Returns all guests in the RSVP list.
     */
    public List<Guest> getAllGuests() {
        return asUnmodifiableObservableList().stream()
            .filter(person -> person instanceof Guest)
            .map(person -> (Guest) person)
            .collect(Collectors.toList());
    }

    public Guest getGuestByPhone(Phone phone) throws CommandException {
        return getAllGuests().stream()
            .filter(guest -> guest.getPhone().equals(phone))
            .findFirst()
            .orElseThrow(() -> new CommandException(
                "Guest with phone " + phone + " not found"));
    }

    public Guest getGuestByGuestId(Integer guestId) throws CommandException {
        return getAllGuests().stream()
            .filter(guest -> guest.getGuestId().equals(guestId))
            .findFirst()
            .orElseThrow(() -> new CommandException(
                "Guest with ID " + guestId + " not found"));
    }
}
