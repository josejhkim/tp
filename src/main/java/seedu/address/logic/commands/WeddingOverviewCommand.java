package seedu.address.logic.commands;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.person.Guest;
import seedu.address.model.person.RsvpList;

/**
 * Command to an overview of the current wedding.
 */
public class WeddingOverviewCommand extends Command {

    public static final String COMMAND_WORD = "weddingOverview";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Overview of specified wedding.\n"
        + "Parameters: Wedding\n"
        + "Example: " + COMMAND_WORD + " John and Jane's Wedding";

    //TODO: Add number of tables
    public static final String MESSAGE_SUCCESS = """
        Overview of %1$s's Wedding:
        Number of guests attending: %2$s
        Guest list:
        %3$s
        """;

    private final String weddingName;

    public WeddingOverviewCommand(String weddingName) {
        this.weddingName = weddingName;
    }

    @Override
    public CommandResult execute(Model model) {
        RsvpList rsvpList = model.findWeddingByName(weddingName).getRsvpList();
        Integer rsvpListLen = rsvpList.getAllGuests().size();

        List<Guest> guests = rsvpList.getAllGuests();
        StringBuilder guestList = new StringBuilder();
        for (Guest guest : guests) {
            guestList.append(guest.toString()).append("\n");
        }
        return new CommandResult(String.format(
            MESSAGE_SUCCESS,
            model.getCurrentWedding().getName(),
            rsvpListLen,
            guestList.toString().trim()));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof WeddingOverviewCommand;
    }
}
