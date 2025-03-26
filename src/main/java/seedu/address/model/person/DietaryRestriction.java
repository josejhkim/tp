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
        VEGETARIAN, VEGAN, HALAL, SHELLFISH, PEANUTS, EGGS, FISH,
        SOY, SESAME, NONE;
    }

    private final TypicalRestriction typicalRestriction;

    /**
     * Constructs a default {@code DietaryRestriction}
     * representing no restrictions
     */
    public DietaryRestriction() {
        this.typicalRestriction = TypicalRestriction.NONE;
    }

    /**
     * Constructs a {@code DietaryRestriction}.
     *
     * @param typicalRestriction A typical dietary restriction.
     */
    public DietaryRestriction(TypicalRestriction typicalRestriction) {
        this.typicalRestriction = typicalRestriction;
    }

    public TypicalRestriction getTypicalRestriction() {
        return typicalRestriction;
    }

    /**
     * Returns a string of all possible typical dietary restrictions.
     */
    public static String getPossibleValues() {
        StringBuilder possibleValues = new StringBuilder();
        for (TypicalRestriction restriction : TypicalRestriction.values()) {
            possibleValues.append(restriction.name()).append(", ");
        }
        // Remove the last comma and space
        if (!possibleValues.isEmpty()) {
            possibleValues.setLength(possibleValues.length() - 2);
        }
        return possibleValues.toString();
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
        return typicalRestriction == otherRestriction.typicalRestriction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typicalRestriction);
    }

    @Override
    public String toString() {
        return typicalRestriction.toString();
    }
}
