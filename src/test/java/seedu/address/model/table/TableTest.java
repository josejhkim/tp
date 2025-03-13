package seedu.address.model.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TableTest {

    @Test
    public void testConstructorAndGetters() {
        Table table = new Table(1, 10);
        assertEquals(1, table.getTableId());
        assertEquals(10, table.getCapacity());
    }

    @Test
    public void testSetCapacity() {
        Table table = new Table(1, 10);
        table.setCapacity(8);
        assertEquals(8, table.getCapacity());
    }

    @Test
    public void testEquals() {
        Table table1 = new Table(1, 10);
        Table table2 = new Table(1, 10);
        Table table3 = new Table(2, 8);

        assertEquals(table1, table2);
        assertNotEquals(table1, table3);
    }

    @Test
    public void testToString() {
        Table table = new Table(1, 10);
        assertEquals("Table ID: 1, Capacity: 10", table.toString());
    }

    @Test
    public void testToJsonAndFromJson() {
        Table table = new Table(1, 10);
        String json = table.toJson();
        Table restoredTable = Table.fromJson(json);
        assertEquals(table, restoredTable);
    }
}
