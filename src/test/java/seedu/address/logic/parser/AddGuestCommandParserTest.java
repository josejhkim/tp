package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.category.DietaryRestriction;
import seedu.address.model.person.category.Rsvp;

import java.util.HashSet;

public class AddGuestCommandParserTest {

    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws Exception {
        try {
            Person guest = new Person(new Name("John Doe"),
                new Phone("12345678"),
                new Email("johndoe@example.com"),
                new Address("123 Street"),
                new HashSet<>(),
                new DietaryRestriction(DietaryRestriction.TypicalRestriction.NONE),
                new Rsvp(Rsvp.Status.YES),
                null);
            AddCommand command = parser.parse(
                " n/John Doe p/12345678 e/johndoe@example.com a/123 Street d/None r/YES");
            System.out.println(command);
            System.out.println(new AddCommand(guest));

            assertEquals(new AddCommand(guest), command);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_missingFields_failure() {
        assertThrows(ParseException.class, () -> parser.parse(
            " n/John Doe p/12345678 e/johndoe@example.com a/123 Street d/None"));
    }

    @Test
    public void parse_invalidFormat_failure() {
        assertThrows(ParseException.class, () -> parser.parse(
            " n/John Doe p/12345678 e/johndoe@example.com a/123 Street d/None r/YES extra"));
    }
}
