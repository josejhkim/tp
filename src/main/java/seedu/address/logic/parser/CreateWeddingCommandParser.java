package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.CreateWeddingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new CreateWeddingCommand object
 */
public class CreateWeddingCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the CreateWeddingCommand
     * and returns a CreateWeddingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateWeddingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        if (!argMultimap.getValue(PREFIX_NAME).isPresent() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateWeddingCommand.MESSAGE_USAGE));
        }

        String weddingName = argMultimap.getValue(PREFIX_NAME).get().trim();
        return new CreateWeddingCommand(weddingName);
    }
}
