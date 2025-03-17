package seedu.address.logic.parser;

import seedu.address.logic.commands.GetAllTablesCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new GetAllTablesCommand object.
 */
public class GetAllTablesCommandParser implements Parser<GetAllTablesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetAllTablesCommand
     * and returns a GetAllTablesCommand object for execution.
     * @throws ParseException if the user input is invalid.
     */
    @Override
    public GetAllTablesCommand parse(String args) throws ParseException {
        // Convert to lowercase to allow case-insensitive commands
        String trimmedArgs = args.trim().toLowerCase();

        if (!trimmedArgs.isEmpty()) {
            throw new ParseException("getTables command does not take any arguments.");
        }

        return new GetAllTablesCommand();
    }
}
