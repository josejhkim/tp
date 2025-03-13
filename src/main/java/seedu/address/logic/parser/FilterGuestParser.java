package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;

import java.util.stream.Stream;

import seedu.address.logic.commands.FilterGuestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.category.Category;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.ParserUtil;

public class FilterGuestParser implements Parser<FilterGuestCommand> {
    // Removed redundant user selection marker

    @Override
    public FilterGuestCommand parse(String args) throws ParseException {
            ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_FIELD);

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

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
