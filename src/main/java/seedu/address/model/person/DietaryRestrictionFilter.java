package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * A predicate that tests if a {@link Person}'s dietary restriction matches a specified dietary restriction.
 * <p>
 * This predicate is used to filter {@code Person} objects based on their dietary restrictions. It implements
 * the {@link java.util.function.Predicate} interface, making it suitable for use in stream filtering and other
 * contexts.
 * </p>
 *
 * @see Person#getDietaryRestriction()
 */
public class DietaryRestrictionFilter implements Predicate<Person> {
    private final DietaryRestriction dietaryRestrictionFilter;

    public DietaryRestrictionFilter(DietaryRestriction dietaryRestriction) {
        this.dietaryRestrictionFilter = dietaryRestriction;
    }

    @Override
    public boolean test(Person person) {
        return person.getDietaryRestriction().equals(dietaryRestrictionFilter);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DietaryRestrictionFilter)) {
            return false;
        }

        DietaryRestrictionFilter otherDietaryRestrictionFilter = (DietaryRestrictionFilter) other;
        return dietaryRestrictionFilter.equals(otherDietaryRestrictionFilter.dietaryRestrictionFilter);
    }
}
