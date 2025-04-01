package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Address;
import seedu.address.model.person.DietaryRestriction;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rsvp;
import seedu.address.model.wedding.Wedding;

public class SeeRsvpListCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        Wedding currentWedding = new Wedding("John and Jane's Wedding");
        model.addWedding(currentWedding);
        model.setCurrentWedding(currentWedding);
    }

    @Test
    public void execute_seeRsvpList_success() {
        Person guest = new Person(
            new Name("John Doe"),
            new Phone("12345678"),
            new Email("johndoe@example.com"),
            new Address("123 Street"),
            new HashSet<>(),
            new DietaryRestriction(DietaryRestriction.TypicalRestriction.NONE),
            new Rsvp(Rsvp.Status.YES),
            -1);

        model.addPerson(guest);
        GetRsvpListCommand command = new GetRsvpListCommand();

        CommandResult result = command.execute(model);

        assertEquals(String.format(GetRsvpListCommand.MESSAGE_SUCCESS, guest), result.getFeedbackToUser());
    }
}
