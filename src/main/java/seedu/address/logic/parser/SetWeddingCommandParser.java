package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SetWeddingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SetWeddingCommandParser implements Parser<SetWeddingCommand> {

    @Override
    public SetWeddingCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetWeddingCommand.MESSAGE_USAGE));
        }
        return new SetWeddingCommand(trimmedArgs);
    }
}