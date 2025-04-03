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
     *
     * @param args the user input after the command word
     * @return FindTableCommand with parsed tableId
     * @throws ParseException if the input is invalid
     */
    @Override
    public FindTableCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim().toLowerCase();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTableCommand.MESSAGE_USAGE));
        }

        String tableIdStr;

        if (trimmedArgs.startsWith("tableid/")) {
            tableIdStr = trimmedArgs.substring("tableid/".length()).trim();
        } else {
            tableIdStr = trimmedArgs;
        }

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
