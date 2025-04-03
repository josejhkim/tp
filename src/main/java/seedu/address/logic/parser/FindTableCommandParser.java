package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindTableCommand object.
 */
public class FindTableCommandParser implements Parser<FindTableCommand> {

    private static final String PREFIX_TID = "tid/";

    @Override
    public FindTableCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTableCommand.MESSAGE_USAGE));
        }

        // Force strict format: must start with "tid/" (case insensitive)
        if (!trimmedArgs.toLowerCase().startsWith(PREFIX_TID)) {
            throw new ParseException("Invalid format. You must use the prefix 'tid/'. Example: findTable tid/3");
        }

        String tableIdStr = trimmedArgs.substring(PREFIX_TID.length()).trim();

        try {
            int tableId = Integer.parseInt(tableIdStr);

            if (tableId <= 0) {
                throw new ParseException("Table ID must be a positive number.");
            }

            return new FindTableCommand(tableId);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid table ID. It must be a numeric value.");
        }
    }
}
