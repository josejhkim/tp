package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GetRsvpListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SeeRsvpListCommandParserTest {

    private final SeeRsvpListCommandParser parser = new SeeRsvpListCommandParser();

    @Test
    public void parse_emptyArgs_returnsSeeRsvpListCommand() throws Exception {
        String emptyArgs = "";
        GetRsvpListCommand command = parser.parse(emptyArgs);
        assertEquals(new GetRsvpListCommand(), command);
    }

    @Test
    public void parse_nonEmptyArgs_throwsParseException() {
        String nonEmptyArgs = "some arguments";
        assertThrows(ParseException.class, () -> parser.parse(nonEmptyArgs));
    }
}
