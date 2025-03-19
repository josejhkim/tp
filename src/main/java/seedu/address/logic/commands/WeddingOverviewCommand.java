package seedu.address.logic.commands;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Guest;
import seedu.address.model.person.RsvpList;
import seedu.address.model.wedding.Wedding;


/**
 * Command to provide an overview of the current wedding.
 */
public class WeddingOverviewCommand extends Command {

    public static final String COMMAND_WORD = "weddingOverview";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows an overview of the current wedding.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = """
        Overview of %1$s's Wedding:
        Number of tables: %2$d
        Number of guests attending: %3$d
        Guest list:
        %4$s
        """;

    public static final String MESSAGE_NO_WEDDING = "No wedding is currently set.";

    public WeddingOverviewCommand() {
        // No parameters needed
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Wedding wedding = model.getCurrentWedding();
        if (wedding == null) {
            throw new CommandException(MESSAGE_NO_WEDDING);
        }

        RsvpList rsvpList = wedding.getRsvpList();
        int rsvpListSize = rsvpList.getAllGuests().size();

        List<Guest> guests = rsvpList.getAllGuests();
        StringBuilder guestList = new StringBuilder();
        for (Guest guest : guests) {
            guestList.append(guest.toString()).append("\n");
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS,
                wedding.getName(),
                wedding.getTableList().asUnmodifiableObservableList().size(),
                rsvpListSize, guestList.toString().trim()));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof WeddingOverviewCommand;
    }
}
