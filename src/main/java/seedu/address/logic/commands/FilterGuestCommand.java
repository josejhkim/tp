package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import java.util.function.Predicate;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.DietaryRestriction;
import seedu.address.model.person.Person;

/**
 * Command to filter guests based on dietary restrictions and/or RSVP status .
 */
public class FilterGuestCommand extends Command {
    public static final String COMMAND_WORD = "filterGuest";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all guest(s) belonging to filtered request.\n"
            + "Please pick at least one of the categories (Dietary restriction / RSVP) to filter by.\n"
            + "Parameters: [d/DIETARY_RESTRICTION_FIELD] [r/RSVP_FIELD]\n"
            + "Example: " + COMMAND_WORD + " d/" + DietaryRestriction.TypicalRestriction.values()[0].toString()
            + " r/" + DietaryRestriction.TypicalRestriction.values()[0].toString();

    public static final String MESSAGE_WEDDING_NOT_FOUND = "Wedding not found.";
    public static final String MESSAGE_SUCCESS = "Here are your filtered guests.";
    public static final String MESSAGE_INVALID_RSVP_STATUS = "Please enter a valid RSVP status.";
    public static final String MESSAGE_INVALID_DIETARY_RESTRICTION = "Please enter a valid dietary restriction.";
    private final Predicate<Person> predicate;

    public FilterGuestCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    /**
     * Returns a string representation of the FilterGuestCommand.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        // TODO
        return "FilterGuestList";
    }
}
