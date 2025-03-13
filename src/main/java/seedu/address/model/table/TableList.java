package seedu.address.model.table;

import java.util.ArrayList;
import java.util.List;

public class TableList {
    private List<Table> tables;

    public TableList() {
        this.tables = new ArrayList<>();
    }

    public void addTable(Table table) {
        tables.add(table);
    }

    public boolean hasTable(Table table) {
        return tables.contains(table);
    }

    public void deleteTable(int tableId) {
        tables.removeIf(table -> table.getTableId() == tableId);
    }

    public List<Table> getTables() {
        return tables;
    }
}
