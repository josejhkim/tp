package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.wedding.Wedding;

class JsonAdaptedWedding {

    private final String name;
    private final List<JsonAdaptedPerson> guests;
    private final List<JsonAdaptedTable> tables;

    @JsonCreator
    public JsonAdaptedWedding(@JsonProperty("name") String name,
                              @JsonProperty("guests") List<JsonAdaptedPerson> guests,
                              @JsonProperty("tables") List<JsonAdaptedTable> tables) {
        this.name = name;
        this.guests = (guests != null) ? guests : new ArrayList<>();
        this.tables = (tables != null) ? tables : new ArrayList<>();
    }

    public JsonAdaptedWedding(Wedding source) {
        this.name = source.getName();

        // Convert all guests from RsvpList
        this.guests = source.getRsvpList().getAllGuests().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList());

        // Convert all tables from TableList
        this.tables = source.getTableList().asUnmodifiableObservableList().stream()
                .map(JsonAdaptedTable::new)
                .collect(Collectors.toList());

    }

    public Wedding toModelType() throws IllegalValueException {
        Wedding wedding = new Wedding(name);

        // Restore guests
        for (JsonAdaptedPerson jGuest : guests) {
            wedding.getRsvpList().add(jGuest.toModelType());
        }

        // Restore tables
        for (JsonAdaptedTable jTable : tables) {
            wedding.getTableList().addTable(jTable.toModelType());
        }

        return wedding;
    }
}
