package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class CreateWeddingCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void execute_createWedding_success() throws Exception {
        CreateWeddingCommand command = new CreateWeddingCommand("John and Jane's Wedding");

        CommandResult result = command.execute(model);

        assertEquals(String.format(CreateWeddingCommand.MESSAGE_SUCCESS,
                model.findWeddingByName("John and Jane's Wedding")),
            result.getFeedbackToUser());
    }

}
