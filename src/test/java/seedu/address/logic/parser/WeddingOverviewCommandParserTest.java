package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.WeddingOverviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Unit tests for {@link WeddingOverviewCommandParser}.
 */
public class WeddingOverviewCommandParserTest {

    private final WeddingOverviewCommandParser parser = new WeddingOverviewCommandParser();

    @Test
    public void parse_validArgs_returnsWeddingOverviewCommand() throws Exception {
        String weddingName = "John and Jane's Wedding";
        WeddingOverviewCommand expectedCommand = new WeddingOverviewCommand(); // ✅ No arguments

        assertEquals(expectedCommand, parser.parse(weddingName));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, WeddingOverviewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_whitespaceOnly_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("   "),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, WeddingOverviewCommand.MESSAGE_USAGE));
    }
}
