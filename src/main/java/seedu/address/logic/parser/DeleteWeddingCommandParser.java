package seedu.address.logic.parser;

import seedu.address.logic.commands.DeleteWeddingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class DeleteWeddingCommandParser {
    /*Parses the given {@code String} of arguments in the context of the DeleteWeddingCommand
     * and returns a DeleteWeddingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteWeddingCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteWeddingCommand.MESSAGE_USAGE));
        }
        return new DeleteWeddingCommand(trimmedArgs);
    }
}
