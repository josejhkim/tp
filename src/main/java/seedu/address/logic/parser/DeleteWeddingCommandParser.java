package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.CreateWeddingCommand;
import seedu.address.logic.commands.DeleteWeddingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteWeddingCommand object
 */
public class DeleteWeddingCommandParser implements Parser<DeleteWeddingCommand> {

    @Override
    public DeleteWeddingCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateWeddingCommand.MESSAGE_USAGE));
        }
        return new DeleteWeddingCommand(trimmedArgs);
    }
}
