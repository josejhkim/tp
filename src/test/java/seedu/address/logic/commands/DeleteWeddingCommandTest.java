package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.wedding.Wedding;

/**
 * Unit tests for {@link DeleteWeddingCommand}.
 */
public class DeleteWeddingCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        model.addWedding(new Wedding("Test Wedding"));
        model.setCurrentWedding("Test Wedding");
    }

    @Test
    public void execute_deleteExistingWedding_success() throws CommandException {
        DeleteWeddingCommand command = new DeleteWeddingCommand("Test Wedding");

        CommandResult result = command.execute(model);

        String expectedMessage = String.format(DeleteWeddingCommand.MESSAGE_SUCCESS, "Test Wedding");
        assertEquals(expectedMessage, result.getFeedbackToUser());

        // Ensure wedding is deleted
        assertEquals(model.getAddressBook().getWeddingList().size(), 1);
        assertEquals(model.getAddressBook().getCurrentWedding().getName(), "Wedding");
    }

    // @Test
    // public void execute_noExistingWedding_throwsCommandException() {
    //     model.setCurrentWedding(null); // Ensure no wedding exists
    //
    //     DeleteWeddingCommand command = new DeleteWeddingCommand();
    //
    //     // Expect CommandException because no wedding exists
    //     assertThrows(CommandException.class, () -> command.execute(model),
    //             DeleteWeddingCommand.MESSAGE_NO_WEDDING);
    // }
}
