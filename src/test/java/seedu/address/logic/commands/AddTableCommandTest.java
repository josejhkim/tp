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
 * Unit tests for AddTableCommand.
 */
public class AddTableCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void execute_noWeddingSet_throwsCommandException() {
        AddTableCommand command = new AddTableCommand(1, 5);

        CommandException exception = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(AddTableCommand.MESSAGE_NO_CURRENT_WEDDING, exception.getMessage());
    }

    @Test
    public void execute_duplicateTableId_throwsCommandException() throws CommandException {
        Wedding wedding = new Wedding("Test Wedding");
        model.addWedding(wedding);
        model.setCurrentWedding(wedding);

        // Add table manually
        model.addTable(new Table(1, 4));

        AddTableCommand command = new AddTableCommand(1, 6); // duplicate tableId

        CommandException exception = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(String.format(AddTableCommand.MESSAGE_DUPLICATE_TABLE, 1), exception.getMessage());
    }

    @Test
    public void execute_validInput_addsTableSuccessfully() throws Exception {
        Wedding wedding = new Wedding("Test Wedding");
        model.addWedding(wedding);
        model.setCurrentWedding(wedding);

        AddTableCommand command = new AddTableCommand(2, 8);

        CommandResult result = command.execute(model);
        assertEquals(String.format(AddTableCommand.MESSAGE_SUCCESS, 2, 8), result.getFeedbackToUser());
    }

    @Test
    public void execute_invalidTable_throwsCommandException() {
        Wedding wedding = new Wedding("Test Wedding");
        model.addWedding(wedding);
        model.setCurrentWedding(wedding);

        AddTableCommand command = new AddTableCommand(3, -1); // invalid capacity

        CommandException exception = assertThrows(CommandException.class, () -> command.execute(model));

        assertEquals("Invalid table configuration: "
                + "The table capacity should be a positive integer", exception.getMessage());
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        AddTableCommand cmd1 = new AddTableCommand(1, 6);
        AddTableCommand cmd2 = new AddTableCommand(1, 6);
        assertEquals(cmd1, cmd2);
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        AddTableCommand cmd1 = new AddTableCommand(1, 6);
        AddTableCommand cmd2 = new AddTableCommand(2, 6);
        AddTableCommand cmd3 = new AddTableCommand(1, 4);
        assert (!cmd1.equals(cmd2));
        assert (!cmd1.equals(cmd3));
    }
}
