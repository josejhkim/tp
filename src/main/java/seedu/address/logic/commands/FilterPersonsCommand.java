package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.DietaryRestriction.TypicalRestriction;
import seedu.address.model.person.DietaryRestrictionFilter;
import seedu.address.model.person.Person;
import seedu.address.model.person.Rsvp.Status;
import seedu.address.model.person.RsvpFilter;

/**
 * Command to filter guests based on dietary restrictions or RSVP status or None.
 */
public class FilterPersonsCommand extends Command {
    public static final String COMMAND_WORD = "filterPersons";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all people belonging to filtered request.\n"
            + "Please pick the categories (Dietary restriction / RSVP) to filter by, or none to get unfiltered list.\n"
            + "Parameters: [d/DIETARY_RESTRICTION_FIELD] [r/RSVP_FIELD]\n"
            + "Example: " + COMMAND_WORD + " d/" + TypicalRestriction.values()[0].toString()
            + " r/" + Status.values()[0].toString();

    public static final String MESSAGE_SUCCESS = "Here are the people matching your filter.";
    final Predicate<Person> dietaryRestrictionFilter;
    final Predicate<Person> rsvpFilter;
    final Predicate<Person> combinedPredicate;

    /**
     * Constructs a FilterPersonsCommand with the specified dietary and RSVP filters.
     * At least one of the filters must be non-null.
     *
     * @param dietaryFilter the dietary restriction filter to apply; may be null
     * @param rsvpFilter the RSVP filter to apply; may be null
     */
    public FilterPersonsCommand(DietaryRestrictionFilter dietaryFilter, RsvpFilter rsvpFilter) {

        this.dietaryRestrictionFilter = dietaryFilter;
        this.rsvpFilter = rsvpFilter;

        Predicate<Person> predicate = person -> true;
        if (dietaryFilter != null) {
            predicate = predicate.and(dietaryFilter);
        }
        if (rsvpFilter != null) {
            predicate = predicate.and(rsvpFilter);
        }
        this.combinedPredicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(combinedPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FilterPersonsCommand)) {
            return false;
        }

        FilterPersonsCommand otherFilterPersonsCommand = (FilterPersonsCommand) other;
        return java.util.Objects.equals(this.dietaryRestrictionFilter,
                otherFilterPersonsCommand.dietaryRestrictionFilter)
            && java.util.Objects.equals(this.rsvpFilter, otherFilterPersonsCommand.rsvpFilter);
    }

    /**
     * Returns a string representation of the FilterPersonsCommand.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                        .add("combined predicate", combinedPredicate)
                        .toString();
    }
}
