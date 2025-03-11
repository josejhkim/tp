package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.person.Guest;
import seedu.address.model.person.RSVPList;

import java.util.List;

public class SeeRSVPListCommand extends Command {

    public static final String COMMAND_WORD = "seeRSVPList";
    public static final String MESSAGE_SUCCESS = "RSVP List:\n%1$s";

    @Override
    public CommandResult execute(Model model) {
        RSVPList rsvpList = model.getCurrentWedding().getRsvpList();
        List<Guest> guests = rsvpList.getAllGuests();
        StringBuilder result = new StringBuilder();
        for (Guest guest : guests) {
            result.append(guest.toString()).append("\n");
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, result.toString().trim()));
    }
}