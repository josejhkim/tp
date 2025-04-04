package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Address;
import seedu.address.model.person.DietaryRestriction;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rsvp;
import seedu.address.model.table.Table;
import seedu.address.model.wedding.Wedding;

public class DeletePersonFromTableCommandTest {

    private Model model;
    private Name name = new Name("John Doe");
    private Person guest;

    @BeforeEach
    public void setUp() throws CommandException {
        model = new ModelManager();
        Wedding currentWedding = new Wedding("John and Jane's Wedding");
        model.addWedding(currentWedding);
        model.setCurrentWeddingByName("John and Jane's Wedding");
        model.addTable(new Table(1, 10));
        model.addTable(new Table(2, 10));

        guest = new Person(name,
                new Phone("12345678"),
                new Email("johndoe@example.com"),
                new Address("123 Street"),
                new HashSet<>(),
                new DietaryRestriction(DietaryRestriction.TypicalRestriction.NONE),
                new Rsvp(Rsvp.Status.YES),
                -1);

        model.addPerson(guest);
        model.addPersonToTableById(guest, 1);
    }

    @Test
    public void execute_validGuestAtTable_success() throws Exception {
        DeletePersonFromTableCommand command = new DeletePersonFromTableCommand(name, 1);
        CommandResult result = command.execute(model);

        assertEquals(String.format(DeletePersonFromTableCommand.MESSAGE_REMOVED_GUEST_FROM_TABLE_SUCCESS,
                name.fullName, 1), result.getFeedbackToUser());
    }

    @Test
    public void execute_guestNotAtTable_throwsCommandException() throws Exception {
        DeletePersonFromTableCommand command = new DeletePersonFromTableCommand(name, 2);
        CommandException ex = assertThrows(CommandException.class, () -> command.execute(model));

        assertEquals("Person 'John Doe' is not assigned to Table 2.", ex.getMessage());
    }

    @Test
    public void execute_tableDoesNotExist_throwsCommandException() {
        DeletePersonFromTableCommand command = new DeletePersonFromTableCommand(name, 999);
        CommandException ex = assertThrows(CommandException.class, () -> command.execute(model));

        assertEquals("Table with ID 999 does not exist.", ex.getMessage());
    }

    @Test
    public void execute_guestDoesNotExistInGuestList_throwsCommandException() {
        Name ghost = new Name("Ghost");
        DeletePersonFromTableCommand command = new DeletePersonFromTableCommand(ghost, 1);
        CommandException ex = assertThrows(CommandException.class, () -> command.execute(model));

        assertEquals("Person 'Ghost' not found in the guest list.", ex.getMessage());
    }

    @Test
    public void execute_noCurrentWeddingSet_throwsCommandException() throws Exception {
        // Reinitialize model with no wedding
        model = new ModelManager();
        DeletePersonFromTableCommand command = new DeletePersonFromTableCommand(name, 1);

        CommandException ex = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals("No current wedding set. Please use 'setWedding' first.", ex.getMessage());
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        DeletePersonFromTableCommand cmd1 = new DeletePersonFromTableCommand(name, 1);
        DeletePersonFromTableCommand cmd2 = new DeletePersonFromTableCommand(name, 1);
        assertEquals(cmd1, cmd2);
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        DeletePersonFromTableCommand cmd1 = new DeletePersonFromTableCommand(name, 1);
        DeletePersonFromTableCommand cmd2 = new DeletePersonFromTableCommand(new Name("Jane Doe"), 1);
        DeletePersonFromTableCommand cmd3 = new DeletePersonFromTableCommand(name, 2);

        assert (!cmd1.equals(cmd2));
        assert (!cmd1.equals(cmd3));
    }
}
