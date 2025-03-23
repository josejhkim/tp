package seedu.address.model.person;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * A list of persons who have been RSVPed to the event.
 * Contains both persons who have RSVPed "yes" and "no".
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
            throw new IllegalArgumentException("Only persons can be removed from the RSVP list.");
        }
        super.remove(toRemove);
    }

    public void setPerson(Person target, Person editedPerson) {
        super.setPerson(target, editedPerson);
    }

    /*
    * Returns all persons in the RSVP list.
     */
    public List<Person> getAllPersons() {
        return asUnmodifiableObservableList().stream()
            .filter(person -> person instanceof Person)
            .map(person -> (Person) person)
            .collect(Collectors.toList());
    }

    /**
     * Returns all persons' names in the RSVP list.
     * @return List of all persons' names in the RSVP list
     */
    public List<Name> getAllPersonNames() {
        return getAllPersons().stream()
            .map(person -> person.getName())
            .collect(Collectors.toList());
    }

    public Person getPersonByPhone(Phone phone) throws CommandException {
        return getAllPersons().stream()
            .filter(person -> person.getPhone().equals(phone))
            .findFirst()
            .orElseThrow(() -> new CommandException(
                "Person with phone " + phone + " not found"));
    }

    public int size() {
        return this.getAllPersons().size();
    }
}
