package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    private Wedding testWedding;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();

        // Explicitly initialize TableList and RsvpList
        testWedding = new Wedding("John and Jane's Wedding");

        model.addWedding(testWedding);
        model.setCurrentWedding(testWedding);
    }

    @Test
    public void execute_existingWeddingOverview_success() throws CommandException {
        WeddingOverviewCommand command = new WeddingOverviewCommand();

        CommandResult result = command.execute(model);

        // ✅ Format the guest list properly
        String formattedGuestList = testWedding.getUniquePersonList().isEmpty()
                ? "No guests added yet."
                : testWedding.getUniquePersonList().toString();

        String expectedMessage = String.format(WeddingOverviewCommand.MESSAGE_SUCCESS,
                "John and Jane's Wedding",
                testWedding.getTableList().asUnmodifiableObservableList().size(),
                testWedding.getUniquePersonList().size(),
                formattedGuestList);

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    // @Test
    // public void execute_noExistingWedding_throwsCommandException() {
    //     model.setCurrentWedding(null); // Ensure no wedding exists
    //
    //     WeddingOverviewCommand command = new WeddingOverviewCommand();
    //
    //     // Expect CommandException because no wedding exists
    //     assertThrows(CommandException.class, () -> command.execute(model),
    //             WeddingOverviewCommand.MESSAGE_NO_WEDDING);
    // }
}
