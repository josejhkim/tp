package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.wedding.Wedding;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_WEDDING = "Wedding list contains duplicate wedding(s).";

    private final List<JsonAdaptedWedding> weddings = new ArrayList<>();

    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("weddingList") List<JsonAdaptedWedding> weddings) {
        this.weddings.addAll(weddings);
    }

    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        weddings.addAll(source.getWeddingList().stream()
            .map(JsonAdaptedWedding::new)
            .collect(Collectors.toList()));
    }

    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        for (JsonAdaptedWedding jsonAdaptedWedding : weddings) {
            Wedding wedding = jsonAdaptedWedding.toModelType();
            if (addressBook.hasWedding(wedding)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_WEDDING);
            }
            addressBook.addWedding(wedding);
        }

        addressBook.setCurrentWedding(addressBook.getWeddingList().get(1));

        return addressBook;
    }
}
