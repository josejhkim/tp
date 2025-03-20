package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.category.DietaryRestriction;
import seedu.address.model.person.category.Rsvp;
import seedu.address.model.table.Table;
import seedu.address.model.table.exceptions.TableNotFoundException;
import seedu.address.model.wedding.Wedding;

public class DeleteGuestFromTableCommandTest {
    private Model model;
    private Name name = new Name("John Doe");

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        Wedding currentWedding = new Wedding("John and Jane's Wedding");
        model.addWedding(currentWedding);
        model.setCurrentWedding(currentWedding);
        Table table = new Table(1, 10);
        currentWedding.getTableList().addTable(table);

        Guest guest = new Guest(name,
            new Phone("12345678"),
            new Email("johndoe@example.com"),
            new Address("123 Street"),
            new DietaryRestriction("None"),
            new Rsvp(Rsvp.Status.YES),
            null);

        currentWedding.getRsvpList().add(guest);
    }
    @Test
    public void execute_deleteGuestFromTable_success() throws Exception {

        DeleteGuestFromTableCommand command = new DeleteGuestFromTableCommand(name, 1);

        CommandResult result = command.execute(model);

        assertEquals(String.format(DeleteGuestFromTableCommand.MESSAGE_REMOVED_GUEST_FROM_TABLE_SUCCESS,
                name.fullName, 1),
            result.getFeedbackToUser());
    }

    @Test
    public void execute_deleteGuestFromTable_failure() {
        AddGuestToTableCommand command = new AddGuestToTableCommand(name, 3);

        assertThrows(TableNotFoundException.class, () -> command.execute(model));
    }
}
