package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.table.Table;
import seedu.address.model.table.TableList;
import seedu.address.model.wedding.Wedding;

/**
 * Adds a new table to the current wedding.
 */
public class AddTableCommand extends Command {
    public static final String COMMAND_WORD = "addTable";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new table to the current wedding.\n"
            + "Parameters: tableId/ID capacity/CAPACITY\n"
            + "Example: " + COMMAND_WORD + " tableId/1 capacity/8";

    public static final String MESSAGE_NO_CURRENT_WEDDING =
            "No current wedding set. Use setWedding command first.";
    public static final String MESSAGE_SUCCESS = "New table added: %1$s";
    public static final String MESSAGE_DUPLICATE_TABLE = "A table with ID %d already exists!";

    private final int tableId;
    private final int capacity;

    /**
     * Constructs an {@code AddTableCommand} to add a table with the given ID and capacity.
     */
    public AddTableCommand(int tableId, int capacity) {
        this.tableId = tableId;
        this.capacity = capacity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // 1) Check if there's a current wedding
        Wedding currentWedding = model.getCurrentWedding();
        if (currentWedding == null) {
            throw new CommandException(MESSAGE_NO_CURRENT_WEDDING);
        }

        // 2) Check if table ID is duplicated
        TableList tableList = currentWedding.getTableList();
        Table existingTable = tableList.findTableById(tableId);
        if (existingTable != null) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_TABLE, tableId));
        }

        // 3) Add the new table
        Table newTable = new Table(tableId, capacity);
        tableList.addTable(newTable);

        // 4) Return success
        return new CommandResult(String.format(MESSAGE_SUCCESS, newTable));
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof AddTableCommand
                && tableId == ((AddTableCommand) other).tableId
                && capacity == ((AddTableCommand) other).capacity);
    }
}
