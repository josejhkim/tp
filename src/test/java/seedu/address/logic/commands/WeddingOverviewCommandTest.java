package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.wedding.Wedding;

/**
 * Unit tests for {@link WeddingOverviewCommand}.
 */
public class WeddingOverviewCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        model.setCurrentWedding(new Wedding("John and Jane's Wedding"));
    }

    @Test
    public void execute_existingWeddingOverview_success() throws CommandException {
        WeddingOverviewCommand command = new WeddingOverviewCommand(); // ✅ No arguments

        CommandResult result = command.execute(model);

        // Ensure the wedding overview includes the correct details
        String expectedMessage = String.format(WeddingOverviewCommand.MESSAGE_SUCCESS,
                "John and Jane's Wedding",
                model.getCurrentWedding().getTableList().asUnmodifiableObservableList().size(),
                model.getCurrentWedding().getRsvpList().getAllGuests().size(),
                model.getCurrentWedding().getRsvpList().getAllGuests().toString());

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_noExistingWedding_throwsCommandException() {
        model.setCurrentWedding(null); // Ensure no wedding exists

        WeddingOverviewCommand command = new WeddingOverviewCommand(); // ✅ No arguments

        // Expect CommandException because no wedding exists
        assertThrows(CommandException.class, () -> command.execute(model),
                WeddingOverviewCommand.MESSAGE_NO_WEDDING);
    }
}
