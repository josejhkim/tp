package seedu.address.model.person;

import java.util.Objects;

public class DietaryRestriction {
    public enum TypicalRestriction {
        VEGETARIAN, VEGAN, HALAL, NONE
    }

    private final TypicalRestriction typicalRestriction;
    private final String customRestriction;

    public DietaryRestriction(TypicalRestriction typicalRestriction) {
        this.typicalRestriction = typicalRestriction;
        this.customRestriction = null;
    }

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
        return typicalRestriction == otherRestriction.typicalRestriction &&
                Objects.equals(customRestriction, otherRestriction.customRestriction);
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