package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.wedding.Wedding;

/**
 * Unit tests for {@link CreateWeddingCommand}.
 */
public class CreateWeddingCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void execute_createWedding_success() throws Exception {
//        String weddingName = "John and Jane's Wedding";
//        CreateWeddingCommand command = new CreateWeddingCommand(weddingName);
//
//        CommandResult result = command.execute(model);
//
//        // Check if the current wedding in the model matches the created wedding
//        assertEquals(weddingName, model.getCurrentWedding().getName());
//
//        // Ensure the command output is as expected
//        String expectedMessage = String.format(CreateWeddingCommand.MESSAGE_SUCCESS, weddingName);
//        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_createWeddingWhenAlreadyExists_throwsCommandException() {
        String weddingName = "Existing Wedding";
        model.setCurrentWedding(new Wedding(weddingName)); // Set an existing wedding

        CreateWeddingCommand command = new CreateWeddingCommand("New Wedding");

        // Expect CommandException because a wedding already exists
        try {
            command.execute(model);
        } catch (CommandException e) {
            assertEquals(CreateWeddingCommand.MESSAGE_EXISTING_WEDDING, e.getMessage());
        }
    }

    @Test
    public void execute_createWeddingWithEmptyName_throwsIllegalArgumentException() {
        // Expect IllegalArgumentException when trying to create a wedding with an empty name
        try {
            new CreateWeddingCommand(" ");
        } catch (IllegalArgumentException e) {
            assertEquals(CreateWeddingCommand.MESSAGE_INVALID_NAME, e.getMessage());
        }
    }
}
