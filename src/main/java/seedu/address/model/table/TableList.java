package seedu.address.model.table;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tables at a wedding event.
 */
public class TableList {
    private final List<Table> tables;

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
     * Finds a table by its ID.
     *
     * @param tableId The ID of the table to find.
     * @return The matching Table if found, otherwise null.
     */
    public Table findTableById(int tableId) {
        for (Table t : tables) {
            if (t.getTableId() == tableId) {
                return t;
            }
        }
        return null; // Not found
    }

    /**
     * Deletes a table from the list based on the table ID.
     *
     * @param tableId The ID of the table to be deleted.
     */
    public void deleteTable(int tableId) {
        tables.removeIf(t -> t.getTableId() == tableId);
    }

    /**
     * Returns the list of tables.
     *
     * @return A list of all tables in this TableList.
     */
    public List<Table> getTables() {
        return tables;
    }
}
