package seedu.address.logic.parser;

import seedu.address.logic.commands.GetRsvpListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new GetRsvpListCommand object
 */
public class SeeRsvpListCommandParser implements Parser<GetRsvpListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the GetRsvpListCommand
     * and returns a GetRsvpListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public GetRsvpListCommand parse(String args) throws ParseException {
        if (!args.trim().isEmpty()) {
            throw new ParseException(String.format("Unexpected arguments: %s", args));
        }
        return new GetRsvpListCommand();
    }
}
