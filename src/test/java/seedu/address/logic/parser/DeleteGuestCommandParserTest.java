package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteGuestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Phone;


// NOTE: When tokenizing the input, ensure there's a leading whitespace so that the prefixes are properly detected.
// For example, " p/12345678" is parsed correctly, whereas "p/12345678" may be treated as a non-empty preamble.
// This is important to avoid unintended ParseExceptions due to unexpected preamble content.

// Also, be aware that if a prefix is not provided (e.g., only p/ is provided and not gid/),
// the corresponding field in the RemoveGuestCommand will be set to null.
// Do not assume that a missing value defaults to 0 or any other value.
// In our tests, we learned that the expected object should match these null values precisely,
// as the equals() method compares the fields directly.

public class DeleteGuestCommandParserTest {

    private final DeleteGuestCommandParser parser = new DeleteGuestCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws Exception {
        DeleteGuestCommand command = parser.parse(
            " p/12345678");

        assertEquals(
            new DeleteGuestCommand(new Phone("12345678"), null), command);
    }

    @Test
    public void parse_missingFields_failure() {
        assertThrows(ParseException.class, () -> parser.parse(
            " n/John Doe p/12345678 e/johndoe@example.com a/123 Street d/None"));
    }

    @Test
    public void parse_validPhoneArgs_returnsRemoveGuestCommand() throws Exception {
        String validArgs = " p/12345678";
        DeleteGuestCommand command = parser.parse(validArgs);
        assertEquals(new DeleteGuestCommand(new Phone("12345678"), null), command);
    }

    @Test
    public void parse_missingAllFields_throwsParseException() {
        String invalidArgs = "";
        assertThrows(ParseException.class, () -> parser.parse(invalidArgs));
    }

}
