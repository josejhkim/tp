package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TABLE_ID;

import seedu.address.logic.commands.DeleteTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTableCommand object.
 */
public class DeleteTableCommandParser implements Parser<DeleteTableCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTableCommand
     * and returns a DeleteTableCommand object for execution.
     * @throws ParseException if the user input does not conform to expected format.
     */
    public DeleteTableCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TABLE_ID);

        if (!argMultimap.getValue(PREFIX_TABLE_ID).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTableCommand.MESSAGE_USAGE));
        }

        try {
            int tableId = Integer.parseInt(argMultimap.getValue(PREFIX_TABLE_ID).get());
            return new DeleteTableCommand(tableId);

        } catch (NumberFormatException e) {
            throw new ParseException("Table ID must be an integer.");
        }
    }
}
