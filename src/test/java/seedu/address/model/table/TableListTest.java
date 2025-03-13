package seedu.address.model.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TableListTest {

    private TableList tableList;
    private Table table1;
    private Table table2;

    @BeforeEach
    public void setUp() {
        tableList = new TableList();
        table1 = new Table(1, 8);
        table2 = new Table(2, 10);
    }

    @Test
    public void testAddTable() {
        tableList.addTable(table1);
        assertEquals(1, tableList.getTables().size());
    }

    @Test
    public void testHasTable() {
        tableList.addTable(table1);
        assertTrue(tableList.hasTable(table1));
        assertFalse(tableList.hasTable(table2));
    }

    @Test
    public void testDeleteTable() {
        tableList.addTable(table1);
        tableList.deleteTable(1);
        assertFalse(tableList.hasTable(table1));
    }
}
