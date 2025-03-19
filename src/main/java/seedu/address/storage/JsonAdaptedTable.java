package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.table.Table;

/**
 * Jackson-friendly version of {@link Table}.
 * <p>
 * This class is used for serializing and deserializing {@code Table} objects
 * to and from JSON format.
 * </p>
 */
public class JsonAdaptedTable {

    /** The unique identifier of the table. */
    private final int tableId;

    /** The seating capacity of the table. */
    private final int capacity;

    /** A list of guest names assigned to this table. */
    private final List<String> guestIds;

    /**
     * Constructs a {@code JsonAdaptedTable} using the given JSON properties.
     *
     * @param tableId The unique identifier of the table.
     * @param capacity The seating capacity of the table.
     * @param guestIds A list of guest names assigned to this table.
     */
    @JsonCreator
    public JsonAdaptedTable(@JsonProperty("tableId") int tableId,
                            @JsonProperty("capacity") int capacity,
                            @JsonProperty("guestIds") List<String> guestIds) {
        this.tableId = tableId;
        this.capacity = capacity;
        this.guestIds = (guestIds != null) ? guestIds : new ArrayList<>(); // ✅ Prevents NullPointerException
    }

    /**
     * Converts a given {@code Table} into this class for Jackson serialization.
     *
     * @param source The table to convert.
     */
    public JsonAdaptedTable(Table source) {
        this.tableId = source.getTableId();
        this.capacity = source.getCapacity();
        this.guestIds = (source.getGuestIds() != null)
                ? new ArrayList<>(source.getGuestIds()) : new ArrayList<>();
    }

    /**
     * Converts this Jackson-friendly adapted table object into the model's {@code Table} object.
     *
     * @return The converted {@code Table} object.
     */
    public Table toModelType() {
        return new Table(tableId, capacity, new ArrayList<>(guestIds));
    }
}
