package seedu.address.storage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.table.Table;


/**
 * Jackson-friendly version of {@link Table}.
 */
class JsonAdaptedTable {

    private final int tableId;
    private final int capacity;

    /**
     * Constructs a {@code JsonAdaptedTable} with the given details.
     */
    @JsonCreator
    public JsonAdaptedTable(@JsonProperty("tableId") int tableId,
                            @JsonProperty("capacity") int capacity) {
        this.tableId = tableId;
        this.capacity = capacity;
    }

    /**
     * Converts this JSON-friendly adapted table object into the model's {@code Table}.
     */
    public Table toModelType() throws IllegalValueException {
        return new Table(tableId, capacity);
    }
}
