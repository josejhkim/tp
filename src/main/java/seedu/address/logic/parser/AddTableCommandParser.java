package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TABLE_ID;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddTableCommand object.
 */
public class AddTableCommandParser implements Parser<AddTableCommand> {

    @Override
    public AddTableCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TABLE_ID, PREFIX_CAPACITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_TABLE_ID, PREFIX_CAPACITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTableCommand.MESSAGE_USAGE));
        }

        int tableId = ParserUtil.parseTableId(argMultimap.getValue(PREFIX_TABLE_ID).get());
        int capacity = ParserUtil.parseCapacity(argMultimap.getValue(PREFIX_CAPACITY).get());

        return new AddTableCommand(tableId, capacity);
    }

    /**
     * Returns true if all prefixes are present.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
