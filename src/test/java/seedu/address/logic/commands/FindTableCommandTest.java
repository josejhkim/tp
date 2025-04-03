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

public class FindTableCommandTest {

    private Model model;
    private final Table testTable = new Table(3, 5);
    private final Name guestName = new Name("Guest A");

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        Wedding wedding = new Wedding("Test Wedding");
        model.addWedding(wedding);
        model.setCurrentWedding(wedding);

        model.addTable(testTable);

        Person guest = new Person(guestName, new Phone("11111111"), new Email("guest@example.com"),
                new Address("123 Test Street"), new HashSet<>(),
                new DietaryRestriction(DietaryRestriction.TypicalRestriction.NONE),
                new Rsvp(Rsvp.Status.YES));
        model.addPerson(guest);
        model.addPersonToTableById(guest, 3);
    }

    @Test
    public void execute_validTableId_success() throws Exception {
        FindTableCommand command = new FindTableCommand(3);

        CommandResult result = command.execute(model);
        String expectedMessage = String.format(
                FindTableCommand.MESSAGE_SUCCESS,
                3, testTable.getCapacity(), 1); // 1 guest added in setup

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_tableNotFound_throwsCommandException() {
        FindTableCommand command = new FindTableCommand(99);

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(String.format(FindTableCommand.MESSAGE_TABLE_NOT_FOUND, 99), thrown.getMessage());
    }

    @Test
    public void execute_noWeddingSet_throwsCommandException() {
        model.deleteCurrentWedding();
        FindTableCommand command = new FindTableCommand(1);

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(FindTableCommand.MESSAGE_NO_CURRENT_WEDDING, thrown.getMessage());
    }

    @Test
    public void equals_sameTableId_returnsTrue() {
        FindTableCommand cmd1 = new FindTableCommand(3);
        FindTableCommand cmd2 = new FindTableCommand(3);
        assertEquals(cmd1, cmd2);
    }

    @Test
    public void equals_differentTableId_returnsFalse() {
        FindTableCommand cmd1 = new FindTableCommand(3);
        FindTableCommand cmd2 = new FindTableCommand(4);
        assert (!cmd1.equals(cmd2));
    }
}
