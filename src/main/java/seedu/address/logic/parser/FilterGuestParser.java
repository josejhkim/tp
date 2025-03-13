package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;

import java.util.stream.Stream;

import seedu.address.logic.commands.FilterGuestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.category.Category;

/**
 * Parses input arguments and creates a new FilterGuestCommand object.
 */
public class FilterGuestParser implements Parser<FilterGuestCommand> {
    // Removed redundant user selection marker

    /**
     * Parses the given {@code String} of arguments in the context of the FilterGuestCommand
     * and returns a FilterGuestCommand object for execution.
     *
     * @param args the user input arguments.
     * @return a FilterGuestCommand object based on the parsed arguments.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public FilterGuestCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_FIELD);

        if (!arePrefixesPresent(argMultimap, PREFIX_CATEGORY, PREFIX_FIELD)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterGuestCommand.MESSAGE_USAGE));
        }
        String category = argMultimap.getValue(PREFIX_CATEGORY).get();
        String field = argMultimap.getValue(PREFIX_FIELD).get();
        Category filter = ParserUtil.parseFilter(category, field);

        return new FilterGuestCommand(filter);
    }

    /**
     * Checks if all required prefixes are present in the user input.
     *
     * @param argumentMultimap The map of prefixes to their corresponding argument values.
     * @param prefixes The prefixes to check for.
     * @return true if all required prefixes are present, false otherwise.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
