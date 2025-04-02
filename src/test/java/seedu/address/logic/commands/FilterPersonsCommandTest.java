package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.person.DietaryRestriction.TypicalRestriction.EGGS;
import static seedu.address.model.person.DietaryRestriction.TypicalRestriction.NONE;
import static seedu.address.model.person.DietaryRestriction.TypicalRestriction.VEGAN;
import static seedu.address.model.person.Rsvp.Status.NO;
import static seedu.address.model.person.Rsvp.Status.NO_RESPONSE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.ToStringBuilder;
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
import seedu.address.testutil.TypicalPersons;

/**
 * Contains unit tests for {@code FilterPersonsCommand}.
 * The test structure is modeled after the DietaryRestrictionFilterTest.
 */
public class FilterPersonsCommandTest {

    // Doesn't contain rsvp = NO_RESPONSE and dietary_restriction = EGGS
    private final AddressBook typicalAddressBook = TypicalPersons.getTypicalAddressBook();
    private final Model model = new ModelManager(typicalAddressBook, new UserPrefs());
    private final Model expectedModel = new ModelManager(typicalAddressBook, new UserPrefs());

    // Predicates
    private final DietaryRestrictionFilter veganPredicate = new DietaryRestrictionFilter(new DietaryRestriction(VEGAN));
    private final DietaryRestrictionFilter nonePredicate = new DietaryRestrictionFilter(new DietaryRestriction(NONE));
    private final DietaryRestrictionFilter eggPredicate = new DietaryRestrictionFilter(new DietaryRestriction(EGGS));
    private final RsvpFilter noPredicate = new RsvpFilter(new Rsvp(NO));
    private final RsvpFilter noResponsePredicate = new RsvpFilter(new Rsvp(NO_RESPONSE));

    @Test
    public void equals() {
        DietaryRestrictionFilter dietaryFilter = new DietaryRestrictionFilter(new DietaryRestriction(VEGAN));
        RsvpFilter rsvpFilter = new RsvpFilter(new Rsvp(NO));

        FilterPersonsCommand command1 = new FilterPersonsCommand(dietaryFilter, null);
        FilterPersonsCommand command2 = new FilterPersonsCommand(dietaryFilter, null);
        FilterPersonsCommand command3 = new FilterPersonsCommand(null, rsvpFilter);

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
        FilterPersonsCommand command = new FilterPersonsCommand(null, noResponsePredicate);
        expectedModel.updateFilteredPersonList(noResponsePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(), model.getFilteredPersonList());
    }

    @Test
    public void execute_filterOnRsvp_multiplePersonsFound() throws CommandException {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FilterPersonsCommand command = new FilterPersonsCommand(null, noPredicate);
        expectedModel.updateFilteredPersonList(noPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(BENSON, ELLE), model.getFilteredPersonList());
    }

    @Test
    public void execute_filterOnDietaryRestriction_noPersonFound() throws CommandException {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FilterPersonsCommand command = new FilterPersonsCommand(eggPredicate, null);
        expectedModel.updateFilteredPersonList(eggPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(), model.getFilteredPersonList());
    }

    @Test
    public void execute_filterOnDietaryRestriction_multiplePersonsFound() throws CommandException {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FilterPersonsCommand command = new FilterPersonsCommand(veganPredicate, null);
        expectedModel.updateFilteredPersonList(veganPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_filterOn2Conditions_noPersonFound() throws CommandException {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FilterPersonsCommand command = new FilterPersonsCommand(veganPredicate, noPredicate);
        expectedModel.updateFilteredPersonList(veganPredicate.and(noPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(), model.getFilteredPersonList());
    }

    @Test
    public void execute_filterOnDietaryRestrictionAndRsvp_multiplePersonsFound() throws CommandException {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FilterPersonsCommand command = new FilterPersonsCommand(nonePredicate, noPredicate);
        expectedModel.updateFilteredPersonList(nonePredicate.and(noPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(ELLE), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        FilterPersonsCommand command = new FilterPersonsCommand(nonePredicate, noPredicate);
        String expected = new ToStringBuilder(command)
                .add("combined predicate", command.combinedPredicate)
                .toString();
        assertEquals(expected, command.toString());
    }
}
