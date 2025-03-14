package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.category.DietaryRestriction;

public class DietaryRestrictionTest {

    @Test
    public void constructor_typicalRestriction_success() {
        DietaryRestriction restriction = new DietaryRestriction(DietaryRestriction.TypicalRestriction.VEGAN);
        assertEquals(DietaryRestriction.TypicalRestriction.VEGAN, restriction.getTypicalRestriction());
        assertEquals(null, restriction.getCustomRestriction());
    }

    @Test
    public void constructor_customRestriction_success() {
        String customRestriction = "Gluten-Free";
        DietaryRestriction restriction = new DietaryRestriction(customRestriction);
        assertEquals(DietaryRestriction.TypicalRestriction.NONE, restriction.getTypicalRestriction());
        assertEquals(customRestriction, restriction.getCustomRestriction());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        DietaryRestriction restriction = new DietaryRestriction(DietaryRestriction.TypicalRestriction.VEGAN);
        assertEquals(restriction, restriction);
    }

    @Test
    public void equals_differentObject_returnsFalse() {
        DietaryRestriction restriction1 = new DietaryRestriction(DietaryRestriction.TypicalRestriction.VEGAN);
        DietaryRestriction restriction2 = new DietaryRestriction(DietaryRestriction.TypicalRestriction.HALAL);
        assertNotEquals(restriction1, restriction2);
    }

    @Test
    public void hashCode_sameObject_returnsSameHashCode() {
        DietaryRestriction restriction = new DietaryRestriction(DietaryRestriction.TypicalRestriction.VEGAN);
        assertEquals(restriction.hashCode(), restriction.hashCode());
    }

    @Test
    public void toString_typicalRestriction_returnsString() {
        DietaryRestriction restriction = new DietaryRestriction(DietaryRestriction.TypicalRestriction.VEGAN);
        assertEquals("VEGAN", restriction.toString());
    }

    @Test
    public void toString_customRestriction_returnsString() {
        String customRestriction = "Gluten-Free";
        DietaryRestriction restriction = new DietaryRestriction(customRestriction);
        assertEquals(customRestriction, restriction.toString());
    }
}
