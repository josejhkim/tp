package seedu.address.model.table;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tables at a wedding event.
 * Provides methods to add, check, delete, and retrieve tables.
 */
public class TableList {
    private List<Table> tables;

    /**
     * Constructs an empty list of tables.
     */
    public TableList() {
        this.tables = new ArrayList<>();
    }

    /**
     * Adds a table to the list.
     *
     * @param table The table to be added.
     */
    public void addTable(Table table) {
        tables.add(table);
    }

    /**
     * Checks if a table exists in the list.
     *
     * @param table The table to check.
     * @return True if the table exists, false otherwise.
     */
    public boolean hasTable(Table table) {
        return tables.contains(table);
    }

    /**
     * Deletes a table from the list based on the table ID.
     *
     * @param tableId The ID of the table to be deleted.
     */
    public void deleteTable(int tableId) {
        tables.removeIf(table -> table.getTableId() == tableId);
    }

    /**
     * Retrieves the list of tables.
     *
     * @return A list of all tables.
     */
    public List<Table> getTables() {
        return tables;
    }
}
