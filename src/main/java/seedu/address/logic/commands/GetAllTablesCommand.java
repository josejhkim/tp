package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.table.Table;
import seedu.address.model.wedding.Wedding;

/**
 * Lists all tables in the current wedding.
 */
public class GetAllTablesCommand extends Command {

    public static final String COMMAND_WORD = "getTables";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all tables in the current wedding.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_NO_CURRENT_WEDDING = "No current wedding set. Use createWedding command first.";
    public static final String MESSAGE_NO_TABLES = "No tables found for this wedding.";
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

        List<Table> tables = model.getCurrentWedding().getTableList()
            .asUnmodifiableObservableList();

        // Handle case where no tables exist
        if (tables.isEmpty()) {
            return new CommandResult(MESSAGE_NO_TABLES);
        }

        // Format the table list output
        StringBuilder result = new StringBuilder();
        for (Table table : tables) {
            result.append(String.format("Table ID: %d | Capacity: %d | Guests: %d\n",
                    table.getTableId(), table.getCapacity(), table.getGuests().size()));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, result.toString().trim()));
    }
}
