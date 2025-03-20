package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteGuestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
/**
 * Parses input arguments and creates a new RemoveGuestCommand object
 */
public class DeleteGuestCommandParser implements Parser<DeleteGuestCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RemoveGuestCommand
     * and returns a RemoveGuestCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteGuestCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_PHONE, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_PHONE) && !arePrefixesPresent(argMultimap, PREFIX_NAME)
            || arePrefixesPresent(argMultimap, PREFIX_PHONE) && arePrefixesPresent(argMultimap, PREFIX_NAME)
            || !argMultimap.getPreamble().isEmpty()) {

            String errorMessage = MESSAGE_INVALID_COMMAND_FORMAT;
            if (!arePrefixesPresent(argMultimap, PREFIX_PHONE) && !arePrefixesPresent(argMultimap, PREFIX_NAME)) {
                errorMessage = "Missing required prefix: either " + PREFIX_PHONE + " or " + PREFIX_NAME;
            } else if (arePrefixesPresent(argMultimap, PREFIX_PHONE)
                && arePrefixesPresent(argMultimap, PREFIX_NAME)) {
                errorMessage = "Only one of " + PREFIX_PHONE + " or " + PREFIX_NAME + " should be provided";
            } else if (!argMultimap.getPreamble().isEmpty()) {
                errorMessage = "Unexpected preamble: " + argMultimap.getPreamble();
            }

            throw new ParseException(String.format(errorMessage, DeleteGuestCommand.MESSAGE_USAGE));
        }
        Phone phone = null;
        Name name = null;


        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        } else if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        }

        return new DeleteGuestCommand(phone, name);
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
