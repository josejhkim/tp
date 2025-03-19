package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindTableCommand object.
 */
public class FindTableCommandParser implements Parser<FindTableCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of FindTableCommand.
     * @throws ParseException if the input is invalid.
     */
    @Override
    public FindTableCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim().toLowerCase();

        // Validate input (must have a number)
        if (trimmedArgs.isEmpty() || !trimmedArgs.startsWith("tableid/")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTableCommand.MESSAGE_USAGE));
        }

        try {
            int tableId = Integer.parseInt(trimmedArgs.split("/")[1]); // Extract table ID
            return new FindTableCommand(tableId);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid table ID. It must be a number.");
        }
    }
}
