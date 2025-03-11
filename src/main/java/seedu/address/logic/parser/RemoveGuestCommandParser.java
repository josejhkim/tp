package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUEST_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.stream.Stream;

import seedu.address.logic.commands.RemoveGuestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Phone;

public class RemoveGuestCommandParser implements Parser<RemoveGuestCommand> {

    @Override
    public RemoveGuestCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_PHONE, PREFIX_GUEST_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_PHONE) && !arePrefixesPresent(argMultimap, PREFIX_GUEST_ID)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveGuestCommand.MESSAGE_USAGE));
        }
        Phone phone = null;
        int guestId = 0;


        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        } else if (argMultimap.getValue(PREFIX_GUEST_ID).isPresent()) {
            guestId = Integer.parseInt(argMultimap.getValue(PREFIX_GUEST_ID).get());
        }

        return new RemoveGuestCommand(phone, guestId);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}