package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.table.Table;
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
        this.guests = source.getUniquePersonList().asUnmodifiableObservableList().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList());

        // Convert all tables from TableList
        this.tables = source.getTableList().asUnmodifiableObservableList().stream()
                .map(JsonAdaptedTable::new)
                .collect(Collectors.toList());

    }

    public Wedding toModelType() throws IllegalValueException {
        Wedding wedding = new Wedding(name);

        // Restore tables
        for (JsonAdaptedTable jTable : tables) {
            wedding.getTableList().addTable(jTable.toModelType());
        }

        // Restore guests
        for (JsonAdaptedPerson jGuest : guests) {
            Person person = jGuest.toModelType();
            Person newPerson = new Person(person, -1);
            int tId = person.getTableId();

            if (tId > -1) {
                wedding.addPerson(newPerson);
                wedding.addPersonToTableById(newPerson, tId);
            } else {
                wedding.addPerson(person);
            }
        }

        return wedding;
    }
}
