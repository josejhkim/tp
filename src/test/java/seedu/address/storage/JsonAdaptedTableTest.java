package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.table.Table;

public class JsonAdaptedTableTest {

    @Test
    public void testToJson() {
        Table table = new Table(1, 10);
        String json = table.toJson();
        assertEquals("{ \"tableId\": 1, \"capacity\": 10 }", json);
    }

    @Test
    public void testFromJson() {
        String json = "{ \"tableId\": 2, \"capacity\": 8 }";
        Table table = Table.fromJson(json);
        assertEquals(2, table.getTableId());
        assertEquals(8, table.getCapacity());
    }
}
