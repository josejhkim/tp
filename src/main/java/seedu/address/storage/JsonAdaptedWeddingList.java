package seedu.address.storage;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.wedding.UniqueWeddingList;

class JsonAdaptedWeddingList {

    private final List<JsonAdaptedWedding> weddings;

    @JsonCreator
    public JsonAdaptedWeddingList(@JsonProperty("weddings") List<JsonAdaptedWedding> weddings) {
        this.weddings = weddings;
    }

    public JsonAdaptedWeddingList(UniqueWeddingList source) {
        // Convert all weddings from RsvpList
        this.weddings = source.asUnmodifiableObservableList().stream().map(JsonAdaptedWedding::new)
                .collect(Collectors.toList());
    }

    public UniqueWeddingList toModelType() throws IllegalValueException {
        UniqueWeddingList weddingList = new UniqueWeddingList();

        // Restore guests
        for (JsonAdaptedWedding jWedding : weddings) {
            weddingList.addWedding(jWedding.toModelType());
        }

        return weddingList;
    }
}
