package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIETARY_RESTRICTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RSVP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.DietaryRestriction;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rsvp;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddGuestCommand object
 */
public class AddCommandParser implements Parser<AddPersonCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddGuestCommand
     * and returns a AddGuestCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddPersonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_TAG, PREFIX_DIETARY_RESTRICTION, PREFIX_RSVP);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
            PREFIX_ADDRESS, PREFIX_DIETARY_RESTRICTION, PREFIX_RSVP)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddPersonCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
        //Getting all the details of the guest
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(
            argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(
            argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(
            argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        DietaryRestriction dietaryRestriction = ParserUtil.parseDietaryRestriction(
            argMultimap.getValue(PREFIX_DIETARY_RESTRICTION).get());
        Rsvp rsvp = ParserUtil.parseRsvp(
            argMultimap.getValue(PREFIX_RSVP).get());

        Person guest = new Person(name, phone, email, address, tagList, dietaryRestriction, rsvp, null);

        return new AddPersonCommand(guest);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
