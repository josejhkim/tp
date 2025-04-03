package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Unit tests for FindTableCommandParser.
 */
public class FindTableCommandParserTest {
    private final FindTableCommandParser parser = new FindTableCommandParser();

    // Valid test case: with prefix
    @Test
    public void parse_validWithPrefix_success() throws Exception {
        FindTableCommand command = parser.parse("tableId/3");
        assertEquals(new FindTableCommand(3), command);
    }

    // Valid test case: without prefix
    @Test
    public void parse_validWithoutPrefix_success() throws Exception {
        FindTableCommand command = parser.parse("3");
        assertEquals(new FindTableCommand(3), command);
    }

    // Valid test case: with whitespace
    @Test
    public void parse_validWithWhitespace_success() throws Exception {
        FindTableCommand command = parser.parse("   tableId/7   ");
        assertEquals(new FindTableCommand(7), command);
    }

    // Invalid input: tableId/ but no number
    @Test
    public void parse_missingNumber_failure() {
        assertThrows(ParseException.class, () -> parser.parse("tableId/"));
    }

    // Invalid input: non-numeric ID
    @Test
    public void parse_nonNumeric_failure() {
        assertThrows(ParseException.class, () -> parser.parse("tableId/abc"));
    }

    // Invalid input: empty input
    @Test
    public void parse_empty_failure() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    // Invalid input: negative ID
    @Test
    public void parse_negativeNumber_failure() {
        assertThrows(ParseException.class, () -> parser.parse("-1"));
    }

    // Invalid input: ID is zero
    @Test
    public void parse_zero_failure() {
        assertThrows(ParseException.class, () -> parser.parse("0"));
    }

    // Invalid input: extra unexpected arguments
    @Test
    public void parse_extraGarbage_failure() {
        assertThrows(ParseException.class, () -> parser.parse("tableId/1 extra stuff here"));
    }
}
