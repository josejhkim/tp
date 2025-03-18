package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.table.Table;
import seedu.address.model.wedding.Wedding;

/**
 * Adds a table to the wedding.
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

    public AddTableCommand(int tableId, int capacity) {
        this.tableId = tableId;
        this.capacity = capacity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Wedding wedding = model.getCurrentWedding();
        if (wedding == null) {
            throw new CommandException("No wedding is currently set. Use `setWedding` first.");
        }

        // Debug existing tables before adding
        System.out.println("DEBUG: Checking tables before adding...");
        if (wedding.getTableList() == null) {
            throw new CommandException("ERROR: TableList is NULL! (Wedding.getTableList())");
        }

        // Debug check: Does table already exist?
        if (wedding.getTableList().hasTable(tableId)) {
            throw new CommandException("A table with this ID already exists.");
        }

        // Create a new table
        Table table = new Table(tableId, capacity);

        // Debug before adding
        System.out.println("DEBUG: Adding table -> " + table);
        wedding.getTableList().addTable(table);
        System.out.println("DEBUG: Tables after adding -> " + wedding.getTableList().getTables());

        // Ensure the model saves the updated wedding
        model.setCurrentWedding(wedding);

        // Debug final wedding state
        System.out.println("DEBUG: Wedding state after addTable -> " + wedding);

        return new CommandResult(String.format("Table added: Table ID: %d, Capacity: %d", tableId, capacity));
    }



}
