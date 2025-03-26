package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIETARY_RESTRICTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RSVP;

import java.util.stream.Stream;

import seedu.address.logic.commands.FilterGuestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DietaryRestriction;
import seedu.address.model.person.DietaryRestrictionFilter;
import seedu.address.model.person.Rsvp;
import seedu.address.model.person.RsvpFilter;

/**
 * Parses input arguments and creates a new FilterGuestCommand object.
 */
public class FilterGuestParser implements Parser<FilterGuestCommand> {

    @Override
    public FilterGuestCommand parse(String args) throws ParseException {
        DietaryRestrictionFilter dietaryRestrictionFilter = null;
        RsvpFilter rsvpFilter = null;

        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DIETARY_RESTRICTION, PREFIX_RSVP);

        if (!anyPrefixesPresent(argumentMultimap, PREFIX_DIETARY_RESTRICTION, PREFIX_RSVP)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterGuestCommand.MESSAGE_USAGE));
        }

        if (argumentMultimap.getValue(PREFIX_DIETARY_RESTRICTION).isPresent()) {
            DietaryRestriction dietaryRestriction = ParserUtil.parseDietaryRestriction(
                    argumentMultimap.getValue(PREFIX_DIETARY_RESTRICTION).get());
            dietaryRestrictionFilter = new DietaryRestrictionFilter(dietaryRestriction);
        }

        if (argumentMultimap.getValue(PREFIX_RSVP).isPresent()) {
            Rsvp rsvp = ParserUtil.parseRsvp(argumentMultimap.getValue(PREFIX_RSVP).get());
            rsvpFilter = new RsvpFilter(rsvp);
        }

        return new FilterGuestCommand(dietaryRestrictionFilter, rsvpFilter);
    }

    /**
     * Checks if at least one prefix are present in the user input.
     *
     * @param argumentMultimap The map of prefixes to their corresponding argument values.
     * @param prefixes The prefixes to check for.
     * @return true if any required prefixes are present, false otherwise.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
