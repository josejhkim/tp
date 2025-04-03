package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddTableCommandParserTest {

    private final AddTableCommandParser parser = new AddTableCommandParser();


    // @Test
    // public void parse_validArgs_returnsAddTableCommand() throws Exception {
    //     AddTableCommand command = parser.parse("addTable tid/1 c/5");
    //     assertEquals(new AddTableCommand(1, 5), command);
    // }

    @Test
    public void parse_validArgsWithWhitespace_returnsAddTableCommand() throws Exception {
        AddTableCommand command = parser.parse("   tid/10    c/100   ");
        assertEquals(new AddTableCommand(10, 100), command);
    }

    @Test
    public void parse_missingTableId_throwsParseException() {
        ParseException exception = assertThrows(ParseException.class, () ->
                parser.parse("c/5"));
        assertEquals(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTableCommand.MESSAGE_USAGE),
                exception.getMessage());
    }
    //
    // @Test
    // public void parse_missingCapacity_throwsParseException() {
    //     ParseException exception = assertThrows(ParseException.class, () ->
    //             parser.parse("tid/1"));
    //     assertEquals(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTableCommand.MESSAGE_USAGE),
    //             exception.getMessage());
    // }
    //
    // @Test
    // public void parse_missingBothArgs_throwsParseException() {
    //     ParseException exception = assertThrows(ParseException.class, () ->
    //             parser.parse(""));
    //     assertEquals(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTableCommand.MESSAGE_USAGE),
    //             exception.getMessage());
    // }
    //
    // @Test
    // public void parse_extraPreamble_throwsParseException() {
    //     ParseException exception = assertThrows(ParseException.class, () ->
    //             parser.parse("add tid/2 c/4"));
    //     assertEquals(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTableCommand.MESSAGE_USAGE),
    //             exception.getMessage());
    // }
    //
    //
    // @Test
    // public void parse_negativeTableId_throwsParseException() {
    //     ParseException exception = assertThrows(ParseException.class, () ->
    //             parser.parse("tid/-1 c/6"));
    //     assertEquals("Table ID must be a positive integer.", exception.getMessage());
    // }
    //
    // @Test
    // public void parse_negativeCapacity_throwsParseException() {
    //     ParseException exception = assertThrows(ParseException.class, () ->
    //             parser.parse("tid/1 c/-3"));
    //     assertEquals("Capacity must be a positive integer.", exception.getMessage());
    // }
    //
    // @Test
    // public void parse_zeroCapacity_throwsParseException() {
    //     ParseException exception = assertThrows(ParseException.class, () ->
    //             parser.parse("tid/3 c/0"));
    //     assertEquals("Capacity must be a positive integer.", exception.getMessage());
    // }
    //
    // @Test
    // public void parse_nonIntegerTableId_throwsParseException() {
    //     ParseException exception = assertThrows(ParseException.class, () ->
    //             parser.parse("tid/abc c/4"));
    //     assertEquals("Invalid table ID. Must be an integer.", exception.getMessage());
    // }
    //
    // @Test
    // public void parse_nonIntegerCapacity_throwsParseException() {
    //     ParseException exception = assertThrows(ParseException.class, () ->
    //             parser.parse("tid/2 c/xyz"));
    //     assertEquals("Invalid capacity. Must be an integer.", exception.getMessage());
    // }
    //
    // @Test
    // public void parse_tableIdMaxValue_success() throws Exception {
    //     AddTableCommand command = parser.parse("tid/" + Integer.MAX_VALUE + " c/1");
    //     assertEquals(new AddTableCommand(Integer.MAX_VALUE, 1), command);
    // }
    //
    // @Test
    // public void parse_capacityMaxValue_success() throws Exception {
    //     AddTableCommand command = parser.parse("tid/5 c/" + Integer.MAX_VALUE);
    //     assertEquals(new AddTableCommand(5, Integer.MAX_VALUE), command);
    // }
}
