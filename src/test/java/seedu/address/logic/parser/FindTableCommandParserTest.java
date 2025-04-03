package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FindTableCommandParserTest {

    private FindTableCommandParser parser;

    @BeforeEach
    public void setUp() {
        parser = new FindTableCommandParser();
    }

    @Test
    public void parse_validTidLowerCase_success() throws Exception {
        FindTableCommand command = parser.parse("tid/3");
        assertEquals(new FindTableCommand(3), command);
    }

    @Test
    public void parse_validTidUpperCase_success() throws Exception {
        FindTableCommand command = parser.parse("TID/5");
        assertEquals(new FindTableCommand(5), command);
    }

    @Test
    public void parse_validTidMixedCase_success() throws Exception {
        FindTableCommand command = parser.parse("tId/7");
        assertEquals(new FindTableCommand(7), command);
    }

    @Test
    public void parse_validTidWithWhitespace_success() throws Exception {
        FindTableCommand command = parser.parse("  tid/8  ");
        assertEquals(new FindTableCommand(8), command);
    }

    @Test
    public void parse_missingTidPrefix_throwsParseException() {
        ParseException exception = assertThrows(ParseException.class, () -> parser.parse("3"));
        assertEquals("Invalid format. You must use the prefix 'tid/'. "
                + "Example: findTable tid/3", exception.getMessage());
    }

    @Test
    public void parse_wrongPrefix_throwsParseException() {
        ParseException exception = assertThrows(ParseException.class, () -> parser.parse("tableid/3"));
        assertEquals("Invalid format. You must use the prefix 'tid/'."
                + " Example: findTable tid/3", exception.getMessage());
    }

    @Test
    public void parse_nonNumericTid_throwsParseException() {
        ParseException exception = assertThrows(ParseException.class, () -> parser.parse("tid/abc"));
        assertEquals("Invalid table ID. It must be a numeric value.", exception.getMessage());
    }

    @Test
    public void parse_negativeTid_throwsParseException() {
        ParseException exception = assertThrows(ParseException.class, () -> parser.parse("tid/-1"));
        assertEquals("Table ID must be a positive number.", exception.getMessage());
    }

    @Test
    public void parse_zeroTid_throwsParseException() {
        ParseException exception = assertThrows(ParseException.class, () -> parser.parse("tid/0"));
        assertEquals("Table ID must be a positive number.", exception.getMessage());
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        ParseException exception = assertThrows(ParseException.class, () -> parser.parse("   "));
        assertEquals(String.format("Invalid command format! \n%1$s",
                FindTableCommand.MESSAGE_USAGE), exception.getMessage());
    }
}
