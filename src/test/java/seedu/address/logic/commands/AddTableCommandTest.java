package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.table.Table;
import seedu.address.model.wedding.Wedding;

/**
 * Tests for AddTableCommand.
 */
public class AddTableCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        model.addWedding(new Wedding("Test Wedding"));
        model.setCurrentWeddingByName("Test Wedding");
    }

    @Test
    public void execute_validTable_addSuccessful() throws CommandException {
        AddTableCommand addTableCommand = new AddTableCommand(1, 8);
        CommandResult result = addTableCommand.execute(model);

        String expectedMessage = String.format("Table added: Table ID: %d, Capacity: %d", 1, 8);
        assertEquals(expectedMessage, result.getFeedbackToUser());

        Table createdTable = model.getCurrentWedding().getTableList().asUnmodifiableObservableList().get(0);
        assertEquals(1, createdTable.getTableId());
        assertEquals(8, createdTable.getCapacity());
    }

    @Test
    public void execute_duplicateTable_throwsCommandException() throws CommandException {
        AddTableCommand cmd1 = new AddTableCommand(1, 8);
        cmd1.execute(model);

        AddTableCommand cmd2 = new AddTableCommand(1, 10);
        CommandException thrown = assertThrows(CommandException.class, () -> cmd2.execute(model));
        assertEquals("A table with this ID already exists.", thrown.getMessage());
    }

    // @Test
    // public void execute_noCurrentWedding_throwsCommandException() {
    //     model.deleteCurrentWedding();
    //     AddTableCommand cmd = new AddTableCommand(2, 6);
    //
    //     CommandException thrown = assertThrows(CommandException.class, () -> cmd.execute(model));
    //     assertEquals("No wedding is currently set. Use `setWedding` first.", thrown.getMessage());
    // }

    @Test
    public void execute_invalidCapacity_throwsCommandException() {
        AddTableCommand cmd = new AddTableCommand(3, -5);
        CommandException thrown = assertThrows(CommandException.class, () -> cmd.execute(model));
        assertEquals("Invalid table: The table capacity should be a positive integer", thrown.getMessage());
    }

    @Test
    public void execute_zeroTableId_throwsCommandException() {
        AddTableCommand cmd = new AddTableCommand(0, 5);
        CommandException thrown = assertThrows(CommandException.class, () -> cmd.execute(model));
        assertEquals("Invalid table: The table ID should be a positive integer", thrown.getMessage());
    }

    // @Test
    // public void execute_capacityTooLarge_throwsCommandException() {
    //     AddTableCommand cmd = new AddTableCommand(4, Table.MAX_CAPACITY + 1);
    //     CommandException thrown = assertThrows(CommandException.class, () -> cmd.execute(model));
    //     assertEquals("Invalid table: The table capacity should be a positive integer", thrown.getMessage());
    // }
}
