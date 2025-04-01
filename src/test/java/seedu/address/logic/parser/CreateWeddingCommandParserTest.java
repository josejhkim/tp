package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateWeddingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class CreateWeddingCommandParserTest {

    private final CreateWeddingCommandParser parser = new CreateWeddingCommandParser();

    @Test
    public void parse_validArgs_returnsCreateWeddingCommand() throws Exception {
        String validArgs = " " + PREFIX_NAME + "John and Jane's Wedding";
        CreateWeddingCommand command = parser.parse(validArgs);
        assertEquals(new CreateWeddingCommand("John and Jane's Wedding"), command);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        String emptyArgs = "";
        assertThrows(ParseException.class, () -> parser.parse(emptyArgs));
    }

    @Test
    public void parse_missingNamePrefix_throwsParseException() {
        String missingPrefixArgs = "John and Jane's Wedding";
        assertThrows(ParseException.class, () -> parser.parse(missingPrefixArgs));
    }
}
