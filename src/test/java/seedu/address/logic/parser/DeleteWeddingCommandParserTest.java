package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteWeddingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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
