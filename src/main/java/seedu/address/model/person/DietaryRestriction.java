package seedu.address.model.person;

import java.util.Objects;

/**
 * Represents a Person's dietary restriction in the address book.
 */
public class DietaryRestriction {
    /**
     * Enum to represent typical dietary restrictions.
     */
    public enum TypicalRestriction {
        VEGETARIAN, VEGAN, HALAL, NONE
    }

    private final TypicalRestriction typicalRestriction;
    private final String customRestriction;
    /**
     * Constructs a {@code DietaryRestriction}.
     *
     * @param typicalRestriction A typical dietary restriction.
     */
    public DietaryRestriction(TypicalRestriction typicalRestriction) {
        this.typicalRestriction = typicalRestriction;
        this.customRestriction = null;
    }
    /**
     * Constructs a {@code DietaryRestriction}.
     *
     * @param customRestriction A custom dietary restriction.
     */
    public DietaryRestriction(String customRestriction) {
        this.typicalRestriction = TypicalRestriction.NONE;
        this.customRestriction = customRestriction;
    }

    public TypicalRestriction getTypicalRestriction() {
        return typicalRestriction;
    }

    public String getCustomRestriction() {
        return customRestriction;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DietaryRestriction)) {
            return false;
        }
        DietaryRestriction otherRestriction = (DietaryRestriction) other;
        return typicalRestriction == otherRestriction.typicalRestriction
            && Objects.equals(customRestriction, otherRestriction.customRestriction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typicalRestriction, customRestriction);
    }

    @Override
    public String toString() {
        return typicalRestriction != TypicalRestriction.NONE ? typicalRestriction.toString() : customRestriction;
    }
}
