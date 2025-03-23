package seedu.address.model.person;

import java.util.function.Predicate;

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
