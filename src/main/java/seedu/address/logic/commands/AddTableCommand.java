package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.table.Table;
import seedu.address.model.wedding.Wedding;

/**
 * Represents a command to add a table to the wedding seating plan.
 */
public class AddTableCommand extends Command {

    public static final String COMMAND_WORD = "addTable";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a table to the wedding.\n"
            + "Parameters: TABLE_ID CAPACITY\n"
            + "Example: " + COMMAND_WORD + " 1 6";

    public static final String MESSAGE_SUCCESS = "Table added: Table ID: %1$d, Capacity: %2$d";
    public static final String MESSAGE_NO_WEDDING = "No wedding is currently set. Use `setWedding` first.";
    public static final String MESSAGE_DUPLICATE_TABLE = "A table with this ID already exists.";

    private final int tableId;
    private final int capacity;

    /**
     * Creates an AddTableCommand with the specified table ID and capacity.
     *
     * @param tableId  The unique identifier for the table.
     * @param capacity The seating capacity of the table.
     */
    public AddTableCommand(int tableId, int capacity) {
        this.tableId = tableId;
        this.capacity = capacity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Wedding wedding = model.getCurrentWedding();
        if (wedding == null) {
            throw new CommandException(MESSAGE_NO_WEDDING);
        }

        if (wedding.getTableList() == null) {
            throw new CommandException("Table list is not initialized in the wedding.");
        }

        if (wedding.getTableList().hasTable(tableId)) {
            throw new CommandException(MESSAGE_DUPLICATE_TABLE);
        }

        Table table = new Table(tableId, capacity);
        wedding.getTableList().addTable(table);

        // Ensure the updated wedding is reflected in the model.
        model.setCurrentWedding(wedding);

        return new CommandResult(String.format(MESSAGE_SUCCESS, tableId, capacity));
    }
}
