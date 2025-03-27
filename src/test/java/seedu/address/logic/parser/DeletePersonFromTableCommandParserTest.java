package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeletePersonFromTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

public class DeletePersonFromTableCommandParserTest {
    private final DeletePersonFromTableCommandParser parser = new DeletePersonFromTableCommandParser();

    private Name name = new Name("John Doe");

    @Test
    public void parse_allFieldsPresent_success() throws Exception {
        try {
            DeletePersonFromTableCommand command = parser.parse(
                " n/John Doe tableId/ 1");
            System.out.println(command);
            System.out.println(new DeletePersonFromTableCommand(name, 1));

            assertEquals(new DeletePersonFromTableCommand(name, 1), command);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_missingFields_failure() {
        assertThrows(ParseException.class, () -> parser.parse(
            " n/John Doe"));
    }

    @Test
    public void parse_invalidFormat_failure() {
        assertThrows(ParseException.class, () -> parser.parse(
            " n/ John Doe tableId/ 1 abcde"));
    }
}
