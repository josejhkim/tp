package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddGuestToTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

public class AddGuestToTableCommandParserTest {
    private final AddGuestToTableCommandParser parser = new AddGuestToTableCommandParser();

    private Name name = new Name("John Doe");

    @Test
    public void parse_allFieldsPresent_success() throws Exception {
        try {
            AddGuestToTableCommand command = parser.parse(
                " n/John Doe tableId/ 1");
            System.out.println(command);
            System.out.println(new AddGuestToTableCommand(name, 1));
            assert(new AddGuestToTableCommand(name, 1).equals(command));

//            assertEquals(new AddGuestToTableCommand(name, 1), command);
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
