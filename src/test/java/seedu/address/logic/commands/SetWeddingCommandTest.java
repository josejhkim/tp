package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.wedding.Wedding;

public class SetWeddingCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void execute_setWedding_success() throws Exception {
        Wedding currentWedding = new Wedding("John and Jane's Wedding");
        model.addWedding(currentWedding);
        SetWeddingCommand command = new SetWeddingCommand(currentWedding.getName());

        CommandResult result = command.execute(model);

        assertEquals(String.format(SetWeddingCommand.MESSAGE_SUCCESS,
            currentWedding.getName()), result.getFeedbackToUser());
    }

    @Test
    public void execute_setWedding_failure() {
        SetWeddingCommand command = new SetWeddingCommand("Nonexistent Wedding");

        assertThrows(CommandException.class, () -> command.execute(model));
    }
}
