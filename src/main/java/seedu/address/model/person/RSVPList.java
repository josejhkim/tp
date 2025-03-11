package seedu.address.model.person;

import seedu.address.model.person.UniquePersonList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A list of guests who have been RSVPed to the event.
 * Contains both guests who have RSVPed "yes" and "no".
 */
public class RSVPList extends UniquePersonList {
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

    public Guest getGuestByPhone(Phone phone) {
        return getAllGuests().stream()
            .filter(guest -> guest.getPhone().equals(phone))
            .findFirst()
            .orElse(null);
    }

    public Guest getGuestByGuestId(Integer guestId) {
        return getAllGuests().stream()
            .filter(guest -> guest.getGuestId().equals(guestId))
            .findFirst()
            .orElse(null);
    }
}