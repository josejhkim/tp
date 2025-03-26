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

        // The wedding’s table list should now have 1 table
        assertEquals(1, model.getCurrentWedding().getTableList().asUnmodifiableObservableList().size());

        Table createdTable = model.getCurrentWedding().getTableList().asUnmodifiableObservableList().get(0);
        assertEquals(1, createdTable.getTableId());
        assertEquals(8, createdTable.getCapacity());
    }

    @Test
    public void execute_duplicateTable_throwsCommandException() throws CommandException {
        // Add table with ID=1
        AddTableCommand cmd1 = new AddTableCommand(1, 8);
        cmd1.execute(model);

        // Attempt to add the same ID again
        AddTableCommand cmd2 = new AddTableCommand(1, 10);
        assertThrows(CommandException.class, () -> cmd2.execute(model),
                "A table with ID 1 already exists!");
    }

    // @Test
    // public void execute_noCurrentWedding_throwsCommandException() {
    //     model.setCurrentWedding(null);
    //     AddTableCommand cmd = new AddTableCommand(2, 6);
    //
    //     assertThrows(CommandException.class, () -> cmd.execute(model),
    //             "No current wedding set. Use setWedding command first.");
    // }
}
