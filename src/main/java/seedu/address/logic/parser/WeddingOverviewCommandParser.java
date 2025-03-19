package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.WeddingOverviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new WeddingOverviewCommand object
 */
public class WeddingOverviewCommandParser implements Parser<WeddingOverviewCommand> {

    @Override
    public WeddingOverviewCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, WeddingOverviewCommand.MESSAGE_USAGE));
        }
        return new WeddingOverviewCommand();
    }
}
