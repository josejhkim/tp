package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.DIETARY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.RSVP_DESC_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterPersonsCommand;
import seedu.address.model.person.DietaryRestriction;
import seedu.address.model.person.DietaryRestriction.TypicalRestriction;
import seedu.address.model.person.DietaryRestrictionFilter;
import seedu.address.model.person.Rsvp;
import seedu.address.model.person.Rsvp.Status;
import seedu.address.model.person.RsvpFilter;

public class FilterPersonsCommandParserTest {
    private final FilterPersonsCommandParser parser = new FilterPersonsCommandParser();
    private final Rsvp rsvpYes = new Rsvp(Status.YES);
    private final RsvpFilter rsvpYesFilter = new RsvpFilter(rsvpYes);
    private final DietaryRestriction dietaryRestrictionNone = new DietaryRestriction(TypicalRestriction.NONE);
    private final DietaryRestrictionFilter dietaryRestrictionFilter =
            new DietaryRestrictionFilter(dietaryRestrictionNone);

    @Test
    public void parse_noFilter() {
        FilterPersonsCommand expectedCommand = new FilterPersonsCommand(null, null);
        assertParseSuccess(parser, "", expectedCommand);
    }

    @Test
    public void parse_validRsvpFilter_returnsFilterCommand() {
        // Rsvp filter - r/Yes
        FilterPersonsCommand expectedCommand = new FilterPersonsCommand(null, rsvpYesFilter);
        assertParseSuccess(parser, RSVP_DESC_AMY, expectedCommand);
    }

    @Test
    public void parse_invalidDietaryRestrictionFilter_throwsParseException() {
        // Dietary restriction filter - d/None
        FilterPersonsCommand expectedCommand = new FilterPersonsCommand(dietaryRestrictionFilter, null);
        assertParseSuccess(parser, DIETARY_DESC_AMY, expectedCommand);
    }

    @Test
    public void parse_bothFilter_returnsFilterCommand() {
        // Both filters
        FilterPersonsCommand expectedCommand = new FilterPersonsCommand(dietaryRestrictionFilter, rsvpYesFilter);
        assertParseSuccess(parser, DIETARY_DESC_AMY + RSVP_DESC_AMY, expectedCommand);
    }
}
