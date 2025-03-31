package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddTableCommandParserTest {
    private final AddTableCommandParser parser = new AddTableCommandParser();

    @Test
    public void parse_prefixedFormat_success() throws Exception {
        AddTableCommand command = parser.parse("tableId/2 capacity/6");
        assertEquals(new AddTableCommand(2, 6), command);
    }

    @Test
    public void parse_rawFormat_success() throws Exception {
        AddTableCommand command = parser.parse("2 6");
        assertEquals(new AddTableCommand(2, 6), command);
    }

    @Test
    public void parse_missingTableId_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("capacity/6"));
    }

    @Test
    public void parse_missingCapacity_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("tableId/3"));
    }

    @Test
    public void parse_nonIntegerValues_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("tableId/a capacity/b"));
        assertThrows(ParseException.class, () -> parser.parse("a b"));
    }

    @Test
    public void parse_negativeValues_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("-1 -5"));
    }

    @Test
    public void parse_tooManyArguments_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("1 2 3"));
    }

    @Test
    public void parse_tooFewArguments_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("1"));
        assertThrows(ParseException.class, () -> parser.parse(""));
    }
}
