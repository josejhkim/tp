package seedu.address.logic.parser;

import seedu.address.logic.commands.SeeRsvpListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new SeeRsvpListCommand object
 */
public class SeeRsvpListCommandParser implements Parser<SeeRsvpListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SeeRsvpListCommand
     * and returns a SeeRsvpListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SeeRsvpListCommand parse(String args) throws ParseException {
        if (!args.trim().isEmpty()) {
            throw new ParseException(String.format("Unexpected arguments: %s", args));
        }
        return new SeeRsvpListCommand();
    }
}
