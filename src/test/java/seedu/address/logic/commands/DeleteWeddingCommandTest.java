package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.wedding.Wedding;

public class DeleteWeddingCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        Wedding currentWedding = new Wedding("John and Jane's Wedding");
        model.addWedding(currentWedding);
        model.setCurrentWedding(currentWedding);
    }

    @Test
    public void execute_deleteWedding_success() throws Exception {
        Wedding wedding = model.getCurrentWedding();
        DeleteWeddingCommand command = new DeleteWeddingCommand(wedding.getName());

        CommandResult result = command.execute(model);

        assertEquals(String.format(DeleteWeddingCommand.MESSAGE_SUCCESS, wedding), result.getFeedbackToUser());
    }

    @Test
    public void execute_deleteWedding_failure() {
        DeleteWeddingCommand command = new DeleteWeddingCommand("Nonexistent Wedding");

        assertThrows(CommandException.class, () -> command.execute(model));
    }
}
