package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Address;
import seedu.address.model.person.DietaryRestriction;
import seedu.address.model.person.Email;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rsvp;
import seedu.address.model.wedding.Wedding;

public class RemoveGuestCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        Wedding currentWedding = new Wedding("John and Jane's Wedding");
        model.addWedding(currentWedding);
        model.setCurrentWedding(currentWedding);
    }

    @Test
    public void execute_removeGuest_success() throws Exception {
        Guest guest = new Guest(
            new Name("John Doe"),
            new Email("johndoe@example.com"),
            new Address("123 Street"),
            new Phone("12345678"),
            new DietaryRestriction("None"),
            new Rsvp(Rsvp.Status.YES));
        model.getCurrentWedding().addGuest(guest);
        RemoveGuestCommand command = new RemoveGuestCommand(new Phone("12345678"), 0);

        CommandResult result = command.execute(model);

        assertEquals(String.format(RemoveGuestCommand.MESSAGE_SUCCESS, guest), result.getFeedbackToUser());
    }

    @Test
    public void execute_removeGuest_failure() {
        RemoveGuestCommand command = new RemoveGuestCommand(new Phone("12345674"), 0);

        assertThrows(CommandException.class, () -> command.execute(model));
    }
}
