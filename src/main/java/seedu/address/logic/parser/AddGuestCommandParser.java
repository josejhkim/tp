package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIETARY_RESTRICTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RSVP;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddGuestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.DietaryRestriction;
import seedu.address.model.person.Email;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.RSVP;

public class AddGuestCommandParser implements Parser<AddGuestCommand> {

    @Override
    public AddGuestCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DIETARY_RESTRICTION, PREFIX_RSVP);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DIETARY_RESTRICTION, PREFIX_RSVP)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGuestCommand.MESSAGE_USAGE));
        }
        //Getting all the details of the guest
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        DietaryRestriction dietaryRestriction = ParserUtil.parseDietaryRestriction(argMultimap.getValue(PREFIX_DIETARY_RESTRICTION).get());
        RSVP rsvp = ParserUtil.parseRSVP(argMultimap.getValue(PREFIX_RSVP).get());

        Guest guest = new Guest(name, email, address, phone, dietaryRestriction, rsvp);

        return new AddGuestCommand(guest);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}