package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DIETARY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.RSVP_DESC_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterGuestCommand;
import seedu.address.model.person.DietaryRestriction;
import seedu.address.model.person.DietaryRestriction.TypicalRestriction;
import seedu.address.model.person.DietaryRestrictionFilter;
import seedu.address.model.person.Rsvp;
import seedu.address.model.person.Rsvp.Status;
import seedu.address.model.person.RsvpFilter;

public class FilterGuestParserTest {
    private final FilterGuestParser parser = new FilterGuestParser();
    private final Rsvp rsvpYes = new Rsvp(Status.YES);
    private final RsvpFilter rsvpYesFilter = new RsvpFilter(rsvpYes);
    private final DietaryRestriction dietaryRestrictionNone = new DietaryRestriction(TypicalRestriction.NONE);
    private final DietaryRestrictionFilter dietaryRestrictionFilter =
            new DietaryRestrictionFilter(dietaryRestrictionNone);

    @Test
    public void parse_noFilter_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterGuestCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validRsvpFilter_returnsFilterCommand() {
        // Rsvp filter - r/Yes
        FilterGuestCommand expectedCommand = new FilterGuestCommand(null, rsvpYesFilter);
        assertParseSuccess(parser, RSVP_DESC_AMY, expectedCommand);
    }

    @Test
    public void parse_invalidDietaryRestrictionFilter_throwsParseException() {
        // Dietary restriction filter - d/None
        FilterGuestCommand expectedCommand = new FilterGuestCommand(dietaryRestrictionFilter, null);
        assertParseSuccess(parser, DIETARY_DESC_AMY, expectedCommand);
    }

    @Test
    public void parse_bothFilter_returnsFilterCommand() {
        // Both filters
        FilterGuestCommand expectedCommand = new FilterGuestCommand(dietaryRestrictionFilter, rsvpYesFilter);
        assertParseSuccess(parser, DIETARY_DESC_AMY + RSVP_DESC_AMY, expectedCommand);
    }
}
