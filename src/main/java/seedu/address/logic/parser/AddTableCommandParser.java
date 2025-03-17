package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TABLE_ID;

import seedu.address.logic.commands.AddTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddTableCommand object.
 */
public class AddTableCommandParser implements Parser<AddTableCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTableCommand
     * and returns an AddTableCommand object for execution.
     * @throws ParseException if the user input does not conform to expected format.
     */
    public AddTableCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TABLE_ID, PREFIX_CAPACITY);

        if (!argMultimap.getValue(PREFIX_TABLE_ID).isPresent()
                || !argMultimap.getValue(PREFIX_CAPACITY).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTableCommand.MESSAGE_USAGE));
        }

        try {
            int tableId = Integer.parseInt(argMultimap.getValue(PREFIX_TABLE_ID).get());
            int capacity = Integer.parseInt(argMultimap.getValue(PREFIX_CAPACITY).get());

            return new AddTableCommand(tableId, capacity);

        } catch (NumberFormatException e) {
            throw new ParseException("Table ID and Capacity must be integers.");
        }
    }
}
