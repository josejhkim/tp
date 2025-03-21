package seedu.address.model.person;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * A list of guests who have been RSVPed to the event.
 * Contains both guests who have RSVPed "yes" and "no".
 */
public class RsvpList extends UniquePersonList {

    @Override
    public boolean contains(Person toCheck) {
        if (!(toCheck instanceof Person)) {
            return false;
        }
        return super.contains(toCheck);
    }

    @Override
    public void remove(Person toRemove) {
        if (!(toRemove instanceof Person)) {
            throw new IllegalArgumentException("Only guests can be removed from the RSVP list.");
        }
        super.remove(toRemove);
    }

    public void setGuest(Person target, Person editedGuest) {
        super.setPerson(target, editedGuest);
    }

    /*
    * Returns all guests in the RSVP list.
     */
    public List<Person> getAllGuests() {
        return asUnmodifiableObservableList().stream()
            .filter(person -> person instanceof Person)
            .map(person -> (Person) person)
            .collect(Collectors.toList());
    }

    /**
     * Returns all guests' names in the RSVP list.
     * @return List of all guests' names in the RSVP list
     */
    public List<Name> getAllGuestNames() {
        return getAllGuests().stream()
            .map(guest -> guest.getName())
            .collect(Collectors.toList());
    }

    public Person getGuestByPhone(Phone phone) throws CommandException {
        return getAllGuests().stream()
            .filter(guest -> guest.getPhone().equals(phone))
            .findFirst()
            .orElseThrow(() -> new CommandException(
                "Person with phone " + phone + " not found"));
    }

    public int size() {
        return this.getAllGuests().size();
    }


}
