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

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given weddings.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("weddings") List<JsonAdaptedWedding> weddings) {
        this.weddings.addAll(weddings != null ? weddings : new ArrayList<>());
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        weddings.addAll(source.getWeddingList().stream().map(JsonAdaptedWedding::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        // Create a completely empty AddressBook to avoid any default weddings
        AddressBook addressBook = new AddressBook();

        // Clear all existing weddings (including the default Wedding)
        // We want to start with a completely clean slate
        for (Wedding wedding : addressBook.getWeddingList()) {
            addressBook.deleteWedding(wedding);
        }

        // Add all weddings from JSON
        for (JsonAdaptedWedding jsonAdaptedWedding : weddings) {
            Wedding wedding = jsonAdaptedWedding.toModelType();
            if (addressBook.hasWedding(wedding)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_WEDDING);
            }
            addressBook.addWedding(wedding);
        }

        //If there are no weddings in the JSON but we removed the default wedding,
        //create a new default wedding to ensure the application has at least one wedding
        //if (weddings.isEmpty()) {
        //    Wedding defaultWedding = new Wedding("Wedding");
        //    addressBook.addWedding(defaultWedding);
        //    addressBook.setCurrentWedding(defaultWedding);
        //} else {
        //    Set the first wedding as the current one
        //        addressBook.setCurrentWedding(addressBook.getWeddingList().get(0));
        //}

        return addressBook;
    }
}
