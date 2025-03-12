package seedu.address.logic.parser;

import seedu.address.logic.commands.DeleteWeddingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteWeddingCommandParserTest {

    private final DeleteWeddingCommandParser parser = new DeleteWeddingCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteWeddingCommand() throws Exception {
        String validArgs = "Wedding ID";
        DeleteWeddingCommand command = parser.parse(validArgs);
        assertEquals(new DeleteWeddingCommand(validArgs), command);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        String emptyArgs = "";
        assertThrows(ParseException.class, () -> parser.parse(emptyArgs));
    }
}