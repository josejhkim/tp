package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * A predicate that checks if a {@link Person}'s RSVP status matches a specified RSVP.
 * <p>
 * This predicate is used to filter {@code Person} objects based on their RSVP status.
 * It implements the {@link java.util.function.Predicate} interface, enabling it to be used
 * in stream filtering and other contexts where a predicate is required.
 * </p>
 * <p>
 * The predicate evaluates to {@code true} if the RSVP obtained from {@link Person#getRsvp()}
 * equals the RSVP provided to this filter, and {@code false} otherwise.
 * </p>
 *
 * @see Person#getRsvp()
 */
public class RsvpFilter implements Predicate<Person> {
    private final Rsvp rsvpFilter;

    public RsvpFilter(Rsvp rsvpFilter) {
        this.rsvpFilter = rsvpFilter;
    }

    @Override
    public boolean test(Person person) {
        return person.getRsvp().equals(rsvpFilter);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RsvpFilter)) {
            return false;
        }

        RsvpFilter otherRsvpFilter = (RsvpFilter) other;
        return rsvpFilter.equals(otherRsvpFilter.rsvpFilter);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", rsvpFilter).toString();
    }
}
