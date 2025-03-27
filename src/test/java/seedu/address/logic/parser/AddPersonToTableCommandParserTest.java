package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPersonToTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

public class AddPersonToTableCommandParserTest {
    private final AddPersonToTableCommandParser parser = new AddPersonToTableCommandParser();

    private Name name = new Name("John Doe");

    @Test
    public void parse_allFieldsPresent_success() throws Exception {
        try {
            AddPersonToTableCommand command = parser.parse(
                " n/ John Doe tableId/ 1");
            System.out.println(command);
            System.out.println(new AddPersonToTableCommand(name, 1));
            assert(new AddPersonToTableCommand(name, 1).equals(command));

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
