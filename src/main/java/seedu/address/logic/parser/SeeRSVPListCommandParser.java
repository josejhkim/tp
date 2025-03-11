package seedu.address.logic.parser;

import seedu.address.logic.commands.SeeRSVPListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SeeRSVPListCommandParser implements Parser<SeeRSVPListCommand> {

    @Override
    public SeeRSVPListCommand parse(String args) throws ParseException {
        return new SeeRSVPListCommand();
    }
}