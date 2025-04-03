package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteWeddingCommand;

/**
 * Unit tests for {@link DeleteWeddingCommandParser}.
 */
public class DeleteWeddingCommandParserTest {

    private final DeleteWeddingCommandParser parser = new DeleteWeddingCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteWeddingCommand() throws Exception {
        DeleteWeddingCommand command = parser.parse("deleteWedding n/ Default Wedding");

        // ✅ Remove the argument since DeleteWeddingCommand takes no arguments
        assertEquals(new DeleteWeddingCommand("Default Wedding"), command);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteWeddingCommand.MESSAGE_USAGE));
    }
}
