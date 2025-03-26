package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEDDING_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteWeddingCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteWeddingCommand object
 */
public class DeleteWeddingCommandParser implements Parser<DeleteWeddingCommand> {

    @Override
    public DeleteWeddingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_WEDDING_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_WEDDING_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteWeddingCommand.MESSAGE_USAGE));
        }
        // Getting the name of the wedding
        String weddingName = ParserUtil
                .parseWeddingName(argMultimap.getValue(PREFIX_WEDDING_NAME).get());

        return new DeleteWeddingCommand(weddingName);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap,
            Prefix... prefixes) {
        return Stream.of(prefixes)
                .allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
