package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.table.Table;
import seedu.address.model.table.TableList;
import seedu.address.model.wedding.Wedding;

/**
 * Lists all tables in the current wedding.
 */
public class GetAllTablesCommand extends Command {

    /** Command word to fetch all tables. */
    public static final String COMMAND_WORD = "getTables";

    /** Usage instructions for help. */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all tables in the current wedding.\n"
            + "Example: " + COMMAND_WORD;

    /** Error message when no wedding is set. */
    public static final String MESSAGE_NO_CURRENT_WEDDING = "No current wedding set. Use createWedding command first.";

    /** Message for when there are no tables. */
    public static final String MESSAGE_NO_TABLES = "No tables found for this wedding.";

    /** Success message for displaying tables. */
    public static final String MESSAGE_SUCCESS = "List of tables:\n%1$s";

    /**
     * Executes the command to fetch all tables.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} with the list of tables.
     * @throws CommandException if there is no current wedding set.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Get the current wedding
        Wedding currentWedding = model.getCurrentWedding();
        if (currentWedding == null) {
            throw new CommandException(MESSAGE_NO_CURRENT_WEDDING);
        }

        // Get all tables
        TableList tableList = currentWedding.getTableList();
        List<Table> tables = tableList.getTables();

        // Handle case where no tables exist
        if (tables.isEmpty()) {
            return new CommandResult(MESSAGE_NO_TABLES);
        }

        // Format the table list output
        StringBuilder result = new StringBuilder();
        for (Table table : tables) {
            result.append(String.format("Table ID: %d | Capacity: %d | Guests: %d\n",
                    table.getTableId(), table.getCapacity(), table.getGuestList().size()));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, result.toString().trim()));
    }
}
