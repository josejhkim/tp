package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.address.model.person.DietaryRestriction.TypicalRestriction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonBuilder;

public class DietaryRestrictionFilterTest {

    // Fields used by tests for matching / non-matching scenarios.
    private DietaryRestriction vegetarian;
    private DietaryRestrictionFilter vegetarianFilter;

    TypicalRestriction typicalVegetarian = TypicalRestriction.VEGETARIAN;
    TypicalRestriction typicalVegan = TypicalRestriction.VEGAN;

    @BeforeEach
    public void setUp() {
        // Initialise the "Vegetarian" restriction and filter before each test.
        vegetarian = new DietaryRestriction(typicalVegetarian);
        vegetarianFilter = new DietaryRestrictionFilter(vegetarian);
    }

    @Test
    public void equals() {
        // For the equals test we create separate instances using TypicalRestriction enums.
        TypicalRestriction typicalVegetarian = TypicalRestriction.VEGETARIAN;
        TypicalRestriction typicalVegan = TypicalRestriction.VEGAN;

        DietaryRestriction vegetarianRestriction = new DietaryRestriction(typicalVegetarian);
        DietaryRestriction veganRestriction = new DietaryRestriction(typicalVegan);

        DietaryRestrictionFilter vegetarianFilterLocal = new DietaryRestrictionFilter(vegetarianRestriction);
        DietaryRestrictionFilter veganFilter = new DietaryRestrictionFilter(veganRestriction);
        DietaryRestrictionFilter vegetarianFilterCopy = new DietaryRestrictionFilter(vegetarianRestriction);

        // same object -> returns true
        assertEquals(vegetarianFilterLocal, vegetarianFilterLocal);

        // same values -> returns true
        assertEquals(vegetarianFilterLocal, vegetarianFilterCopy);

        // different types -> returns false
        assertNotEquals(1, vegetarianFilterLocal);

        // null -> returns false
        assertNotEquals(null, vegetarianFilterLocal);

        // different dietary restrictions -> returns false
        assertNotEquals(vegetarianFilterLocal, veganFilter);
    }

    @Test
    public void test_dietaryRestrictionMatches_returnsTrue() {
        // Build a Person with a matching dietary restriction ("Vegetarian").
        Person person = new PersonBuilder().withDietaryRestriction(typicalVegetarian).build();
        assertTrue(vegetarianFilter.test(person));
    }

    @Test
    public void test_dietaryRestrictionDoesNotMatch_returnsFalse() {
        // Build a Person with a different dietary restriction ("Vegan").
        Person person = new PersonBuilder().withDietaryRestriction(typicalVegan).build();
        assertFalse(vegetarianFilter.test(person));
    }
}
