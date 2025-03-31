package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.table.Table;

/**
 * Finds and displays a table by its ID.
 */
public class FindTableCommand extends Command {

    public static final String COMMAND_WORD = "findTable";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds a table by its ID in the current wedding.\n"
            + "Parameters: tableId/TABLE_ID\n"
            + "Example: " + COMMAND_WORD + " tableId/1";

    public static final String MESSAGE_NO_CURRENT_WEDDING = "No current wedding set. Use createWedding command first.";
    public static final String MESSAGE_TABLE_NOT_FOUND = "Table with ID %d not found.";
    public static final String MESSAGE_SUCCESS = "Table found: Table ID: %d | Capacity: %d | Guests: %d";

    private final int tableId;

    /**
     * Constructs a {@code FindTableCommand} to find a table by its ID.
     * @param tableId The ID of the table to search for.
     */
    public FindTableCommand(int tableId) {
        this.tableId = tableId;
    }

    /**
     * Executes the command to find a table.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} with table details.
     * @throws CommandException if no wedding is set or table is not found.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Table table = model.findTableById(tableId);

        if (table == null) {
            throw new CommandException(String.format(MESSAGE_TABLE_NOT_FOUND, tableId));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS,
                table.getTableId(), table.getCapacity(), table.getAllPersons().size()));
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof FindTableCommand && this.tableId == ((FindTableCommand) other).tableId);
    }
}
