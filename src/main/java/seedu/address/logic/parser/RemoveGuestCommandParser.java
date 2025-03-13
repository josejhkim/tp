package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUEST_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.stream.Stream;

import seedu.address.logic.commands.RemoveGuestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Phone;
/**
 * Parses input arguments and creates a new RemoveGuestCommand object
 */
public class RemoveGuestCommandParser implements Parser<RemoveGuestCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RemoveGuestCommand
     * and returns a RemoveGuestCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public RemoveGuestCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_PHONE, PREFIX_GUEST_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_PHONE) && !arePrefixesPresent(argMultimap, PREFIX_GUEST_ID)
            || arePrefixesPresent(argMultimap, PREFIX_PHONE) && arePrefixesPresent(argMultimap, PREFIX_GUEST_ID)
            || !argMultimap.getPreamble().isEmpty()) {

            String errorMessage = MESSAGE_INVALID_COMMAND_FORMAT;
            if (!arePrefixesPresent(argMultimap, PREFIX_PHONE) && !arePrefixesPresent(argMultimap, PREFIX_GUEST_ID)) {
                errorMessage = "Missing required prefix: either " + PREFIX_PHONE + " or " + PREFIX_GUEST_ID;
            } else if (arePrefixesPresent(argMultimap, PREFIX_PHONE)
                && arePrefixesPresent(argMultimap, PREFIX_GUEST_ID)) {
                errorMessage = "Only one of " + PREFIX_PHONE + " or " + PREFIX_GUEST_ID + " should be provided";
            } else if (!argMultimap.getPreamble().isEmpty()) {
                errorMessage = "Unexpected preamble: " + argMultimap.getPreamble();
            }

            throw new ParseException(String.format(errorMessage, RemoveGuestCommand.MESSAGE_USAGE));
        }
        Phone phone = null;
        Integer guestId = null;


        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        } else if (argMultimap.getValue(PREFIX_GUEST_ID).isPresent()) {
            guestId = Integer.parseInt(argMultimap.getValue(PREFIX_GUEST_ID).get());
        }

        return new RemoveGuestCommand(phone, guestId);
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
