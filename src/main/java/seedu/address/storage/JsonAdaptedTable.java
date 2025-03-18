package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.table.Table;
public class JsonAdaptedTable {
    private final int tableId;
    private final int capacity;
    private final List<Integer> guestIds;

    @JsonCreator
    public JsonAdaptedTable(@JsonProperty("tableId") int tableId,
                            @JsonProperty("capacity") int capacity,
                            @JsonProperty("guestIds") List<Integer> guestIds) {
        this.tableId = tableId;
        this.capacity = capacity;
        this.guestIds = (guestIds != null) ? guestIds : new ArrayList<>(); // ✅ Prevents NullPointerException
    }

    public JsonAdaptedTable(Table source) {
        this.tableId = source.getTableId();
        this.capacity = source.getCapacity();
        this.guestIds = (source.getGuestIds() != null) ? new ArrayList<>(source.getGuestIds()) : new ArrayList<>(); // ✅ Fixes null issue
    }

    public Table toModelType() {
        Table table = new Table(tableId, capacity);
        for (int guestId : guestIds) {
            table.addGuestId(guestId);
        }
        return table;
    }
}
