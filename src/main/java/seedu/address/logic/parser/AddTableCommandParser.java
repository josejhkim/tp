package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TABLE_ID;

import seedu.address.logic.commands.AddTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input and creates an AddTableCommand object.
 */
public class AddTableCommandParser implements Parser<AddTableCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTableCommand
     * and returns an AddTableCommand object for execution.
     *
     * @param args The input string containing the arguments.
     * @return An AddTableCommand object with parsed arguments.
     * @throws ParseException if the input is invalid.
     */
    public AddTableCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TABLE_ID, PREFIX_CAPACITY);

        if (argMultimap.getValue(PREFIX_TABLE_ID).isPresent()
                && argMultimap.getValue(PREFIX_CAPACITY).isPresent()) {

            int tableId = parseInteger(argMultimap, PREFIX_TABLE_ID, "Table ID");
            int capacity = parseInteger(argMultimap, PREFIX_CAPACITY, "Capacity");
            return new AddTableCommand(tableId, capacity);
        }

        // fallback: try positional input like: addTable 3 5
        String[] tokens = args.trim().split("\\s+");
        if (tokens.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTableCommand.MESSAGE_USAGE));
        }

        try {
            int tableId = Integer.parseInt(tokens[0]);
            int capacity = Integer.parseInt(tokens[1]);
            return new AddTableCommand(tableId, capacity);
        } catch (NumberFormatException e) {
            throw new ParseException("Table ID and capacity must be valid integers.");
        }
    }


    /**
     * Parses an integer value from the argument multimap.
     *
     * @param argMultimap The argument multimap containing user input.
     * @param prefix The prefix associated with the value.
     * @param fieldName The name of the field (for error messages).
     * @return The parsed integer value.
     * @throws ParseException if the value is missing or not a valid integer.
     */
    // private int parseInteger(ArgumentMultimap argMultimap, Prefix prefix, String fieldName) throws ParseException {
    //     if (!argMultimap.getValue(prefix).isPresent()) {
    //         throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTableCommand.MESSAGE_USAGE));
    //     }
    //     try {
    //         return Integer.parseInt(argMultimap.getValue(prefix).get());
    //     } catch (NumberFormatException e) {
    //         throw new ParseException(fieldName + " must be a valid integer.");
    //     }
    // }

    private int parseInteger(ArgumentMultimap argMultimap, Prefix prefix, String fieldName) throws ParseException {
        if (!argMultimap.getValue(prefix).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTableCommand.MESSAGE_USAGE));
        }
        try {
            return Integer.parseInt(argMultimap.getValue(prefix).get());
        } catch (NumberFormatException e) {
            throw new ParseException(fieldName + " must be a valid integer.");
        }
    }
}
