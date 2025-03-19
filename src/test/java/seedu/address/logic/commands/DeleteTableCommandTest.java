package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.table.Table;
import seedu.address.model.table.UniqueTableList;
import seedu.address.model.wedding.Wedding;


/**
 * Unit tests for {@link DeleteTableCommand}.
 */
public class DeleteTableCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        model.setCurrentWedding(new Wedding("Test Wedding"));
    }

    @Test
    public void execute_validTable_deletionSuccessful() throws CommandException {
        UniqueTableList tableList = model.getCurrentWedding().getTableList();
        tableList.addTable(new Table(1, 8));

        DeleteTableCommand command = new DeleteTableCommand(1);
        CommandResult result = command.execute(model);

        String expectedMessage = "Table deleted: Table{ID=1, Capacity=8, Guest Names=[]}";
        assertEquals(expectedMessage, result.getFeedbackToUser());

        // Ensure the table was deleted
        assertEquals(0, tableList.asUnmodifiableObservableList().size());
    }

    @Test
    public void execute_nonExistentTable_throwsCommandException() {
        DeleteTableCommand command = new DeleteTableCommand(5);
        assertThrows(CommandException.class, () -> command.execute(model),
                "Table with ID 5 not found.");
    }

    @Test
    public void execute_noCurrentWedding_throwsCommandException() {
        model.setCurrentWedding(null);
        DeleteTableCommand command = new DeleteTableCommand(2);

        // Expect a CommandException with "No current wedding set. Use setWedding command first."
        assertThrows(CommandException.class, () -> command.execute(model),
                "No current wedding set. Use setWedding command first.");
    }
}
