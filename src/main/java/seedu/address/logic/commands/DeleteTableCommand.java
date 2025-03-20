package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.table.Table;
import seedu.address.model.table.UniqueTableList;
import seedu.address.model.wedding.Wedding;

/**
 * Deletes a table from the current wedding.
 */
public class DeleteTableCommand extends Command {
    public static final String COMMAND_WORD = "deleteTable";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a table from the current wedding.\n"
            + "Parameters: tableId/TABLE_ID\n"
            + "Example: " + COMMAND_WORD + " tableId/1";

    public static final String MESSAGE_SUCCESS = "Table deleted: %1$s";
    public static final String MESSAGE_NO_CURRENT_WEDDING =
            "No current wedding set. Use setWedding command first.";
    public static final String MESSAGE_TABLE_NOT_FOUND = "Table with ID %d not found.";

    private final int tableId;

    public DeleteTableCommand(int tableId) {
        this.tableId = tableId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Wedding currentWedding = model.getCurrentWedding();
        if (currentWedding == null) {
            throw new CommandException(MESSAGE_NO_CURRENT_WEDDING);
        }

        UniqueTableList tableList = currentWedding.getTableList();
        Table table = tableList.findTableById(tableId);
        if (table == null) {
            throw new CommandException(String.format(MESSAGE_TABLE_NOT_FOUND, tableId));
        }

        tableList.deleteTable(tableId);

        return new CommandResult(String.format(MESSAGE_SUCCESS, table));
    }


    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof DeleteTableCommand
                && tableId == ((DeleteTableCommand) other).tableId);
    }
}
