package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import seedu.address.model.table.Table;
import seedu.address.model.table.exceptions.TableNotFoundException;
import seedu.address.model.wedding.Wedding;

public class DeletePersonFromTableCommandTest {
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

        Person guest = new Person(name,
            new Phone("12345678"),
            new Email("johndoe@example.com"),
            new Address("123 Street"),
            new HashSet<>(),
            new DietaryRestriction(DietaryRestriction.TypicalRestriction.NONE),
            new Rsvp(Rsvp.Status.YES),
            null);

        currentWedding.addPerson(guest);
        currentWedding.addPersonToTable(guest, 1);
    }

    @Test
    public void execute_deleteGuestFromTable_success() throws Exception {

        DeletePersonFromTableCommand command = new DeletePersonFromTableCommand(name, 1);

        CommandResult result = command.execute(model);

        assertEquals(String.format(DeletePersonFromTableCommand.MESSAGE_REMOVED_GUEST_FROM_TABLE_SUCCESS,
                name.fullName, 1),
            result.getFeedbackToUser());
    }

    @Test
    public void execute_deleteGuestFromTable_failure() {
        AddPersonToTableCommand command = new AddPersonToTableCommand(name, 3);

        assertThrows(TableNotFoundException.class, () -> command.execute(model));
    }
}
