package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.person.DietaryRestriction.TypicalRestriction.NONE;
import static seedu.address.model.person.DietaryRestriction.TypicalRestriction.VEGAN;
import static seedu.address.model.person.Rsvp.Status.NO;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.HOON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.DietaryRestriction;
import seedu.address.model.person.DietaryRestrictionFilter;
import seedu.address.model.person.Rsvp;
import seedu.address.model.person.RsvpFilter;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.TypicalPersons;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Contains unit tests for {@code FilterGuestCommand}.
 * The test structure is modeled after the DietaryRestrictionFilterTest.
 */
public class FilterGuestCommandTest {

    private final AddressBook typicalAddressBook = TypicalPersons.getTypicalAddressBook();
    private final Model model = new ModelManager(typicalAddressBook, new UserPrefs());
    private final Model expectedModel = new ModelManager(typicalAddressBook, new UserPrefs());

    // addressBookSinglePerson contains Amy with RSVP Yes, Dietary Restriction None
    private final AddressBook addressBookSinglePerson = new AddressBookBuilder().withPerson(AMY).build();
    private final Model modelSinglePerson = new ModelManager(addressBookSinglePerson, new UserPrefs());
    private final Model expectedModelSinglePerson = new ModelManager(addressBookSinglePerson, new UserPrefs());

    // Predicates
    private final DietaryRestrictionFilter veganPredicate = new DietaryRestrictionFilter(new DietaryRestriction(VEGAN));
    private final DietaryRestrictionFilter nonePredicate = new DietaryRestrictionFilter(new DietaryRestriction(NONE));
    private final RsvpFilter noRsvpPredicate = new RsvpFilter(new Rsvp(NO));

    @Test
    public void equals() {
        DietaryRestrictionFilter dietaryFilter = new DietaryRestrictionFilter(new DietaryRestriction(VEGAN));
        RsvpFilter rsvpFilter = new RsvpFilter(new Rsvp(NO));

        FilterGuestCommand command1 = new FilterGuestCommand(dietaryFilter, null);
        FilterGuestCommand command2 = new FilterGuestCommand(dietaryFilter, null);
        FilterGuestCommand command3 = new FilterGuestCommand(null, rsvpFilter);

        // same object -> returns true
        assertEquals(command1, command1);

        // same values (constructed with the same predicate instance) -> returns true
        assertEquals(command1, command2);

        // different types -> returns false
        assertNotEquals(1, command1);

        // null -> returns false
        assertNotEquals(null, command1);

        // different predicate -> returns false
        assertNotEquals(command1, command3);
    }

    @Test
    public void execute_filterOnRsvp_noPersonFound() throws CommandException {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FilterGuestCommand command = new FilterGuestCommand(null, noRsvpPredicate);
        expectedModelSinglePerson.updateFilteredPersonList(noRsvpPredicate);
        assertCommandSuccess(command, modelSinglePerson, expectedMessage, expectedModelSinglePerson);
        assertEquals(List.of(), modelSinglePerson.getFilteredPersonList());
    }

    @Test
    public void execute_filterOnRsvp_multiplePersonsFound() throws CommandException {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        FilterGuestCommand command = new FilterGuestCommand(null, noRsvpPredicate);
        expectedModel.updateFilteredPersonList(noRsvpPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(BENSON, ELLE, HOON, BOB), model.getFilteredPersonList());
    }

    @Test
    public void execute_filterOnDietaryRestriction_noPersonFound() throws CommandException {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FilterGuestCommand command = new FilterGuestCommand(veganPredicate, null);
        expectedModelSinglePerson.updateFilteredPersonList(veganPredicate);
        assertCommandSuccess(command, modelSinglePerson, expectedMessage, expectedModelSinglePerson);
        assertEquals(List.of(), modelSinglePerson.getFilteredPersonList());
    }

    @Test
    public void execute_filterOnDietaryRestriction_multiplePersonsFound() throws CommandException {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FilterGuestCommand command = new FilterGuestCommand(veganPredicate, null);
        expectedModel.updateFilteredPersonList(veganPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_filterOn2Conditions_noPersonFound() throws CommandException {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FilterGuestCommand command = new FilterGuestCommand(veganPredicate, noRsvpPredicate);
        expectedModelSinglePerson.updateFilteredPersonList(veganPredicate.and(noRsvpPredicate));
        assertCommandSuccess(command, modelSinglePerson, expectedMessage, expectedModelSinglePerson);
        assertEquals(List.of(), modelSinglePerson.getFilteredPersonList());
    }

    @Test
    public void execute_filterOnDietaryRestrictionAndRsvp_multiplePersonsFound() throws CommandException {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FilterGuestCommand command = new FilterGuestCommand(nonePredicate, noRsvpPredicate);
        expectedModel.updateFilteredPersonList(nonePredicate.and(noRsvpPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(BOB, ELLE, HOON), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        FilterGuestCommand command = new FilterGuestCommand(nonePredicate, noRsvpPredicate);
        String expected = new ToStringBuilder(command)
                .add("combined predicate", command.combinedPredicate)
                .toString();
        assertEquals(expected, command.toString());
    }
}
