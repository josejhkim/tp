package seedu.address.logic.commands;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Guest;
import seedu.address.model.person.RsvpList;

/**
 * Command to an overview of the current wedding.
 */
public class WeddingOverviewCommand extends Command {

    public static final String COMMAND_WORD = "weddingOverview";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Overview of specified wedding.\n"
            + "Parameters: Wedding\n" + "Example: " + COMMAND_WORD + " John and Jane's Wedding";

    // // TODO: Add number of tables
    // public static final String MESSAGE_SUCCESS = """
    //         Overview of %1$s's Wedding:
    //         Number of guests attending: %2$s
    //         Guest list:
    //         %3$s
    //         """;

    public static final String MESSAGE_SUCCESS = """
        Overview of %1$s's Wedding:
        Number of tables: %2$d
        Number of guests attending: %3$d
        Guest list:
        %4$s
        """;

    public static final String MESSAGE_WEDDING_NOT_FOUND = "Wedding not found";

    private final String weddingName;

    public WeddingOverviewCommand(String weddingName) {
        this.weddingName = weddingName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        var wedding = model.findWeddingByName(weddingName);
        if (wedding == null) {
            throw new CommandException(MESSAGE_WEDDING_NOT_FOUND);
        }
        RsvpList rsvpList = wedding.getRsvpList();
        Integer rsvpListLen = rsvpList.getAllGuests().size();

        List<Guest> guests = rsvpList.getAllGuests();
        StringBuilder guestList = new StringBuilder();
        for (Guest guest : guests) {
            guestList.append(guest.toString()).append("\n");
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS,
                wedding.getName(),
                wedding.getTableList().getTables().size(),
                rsvpListLen, guestList.toString().trim()));

        //return new CommandResult(
                //String.format(MESSAGE_SUCCESS, wedding.getName(), rsvpListLen, guestList.toString().trim()));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof WeddingOverviewCommand;
    }
}
