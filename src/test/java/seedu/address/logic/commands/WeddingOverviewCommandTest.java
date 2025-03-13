package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.wedding.Wedding;

public class WeddingOverviewCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void execute_weddingOverview_success() throws Exception {
        Wedding wedding = new Wedding("John and Jane's Wedding");
        model.addWedding(wedding);
        model.setCurrentWedding(wedding);
        WeddingOverviewCommand command = new WeddingOverviewCommand("John and Jane's Wedding");

        CommandResult result = command.execute(model);

        assertEquals(String.format(WeddingOverviewCommand.MESSAGE_SUCCESS,
            wedding.getName(), 0, ""), result.getFeedbackToUser());
    }

    @Test
    public void execute_noCurrentWedding_success() throws Exception {
        WeddingOverviewCommand command = new WeddingOverviewCommand("John and Jane's Wedding");

        assertThrows(CommandException.class, () -> command.execute(model));
    }
}
