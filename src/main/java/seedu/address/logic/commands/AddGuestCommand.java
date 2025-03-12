package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Guest;
import seedu.address.model.wedding.Wedding;

/**
 * Adds a guest to the current wedding.
 */
public class AddGuestCommand extends Command {

    public static final String COMMAND_WORD = "addGuest";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a guest to a wedding.\n"
        + "Parameters: WEDDING_NAME GUEST_NAME\n"
        + "Example: " + COMMAND_WORD + " John and Jane's Wedding John Doe";

    public static final String MESSAGE_SUCCESS = "Guest added to wedding: %1$s";
    public static final String MESSAGE_NO_CURRENT_WEDDING =
        "No current wedding set. Please set a current wedding first. Using the setWedding command.";

    private final Guest guest;

    public AddGuestCommand(Guest guest) {
        this.guest = guest;
    }
    /**
     * Executes the AddGuestCommand.
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} that describes the result of executing the command.
     * @throws CommandException if the current wedding is not set.
     */

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Wedding currentWedding = model.getCurrentWedding();
        if (currentWedding == null) {
            throw new CommandException("No Current Wedding");
        }
        currentWedding.addGuest(guest);
        return new CommandResult(String.format(MESSAGE_SUCCESS, guest));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddGuestCommand)) {
            return false;
        }
        AddGuestCommand otherCommand = (AddGuestCommand) other;
        return guest.toString().equals(otherCommand.guest.toString());
    }

    @Override
    public int hashCode() {
        return guest.hashCode();
    }

    @Override
    public String toString() {
        return "AddGuestCommand{guest=" + guest + "}";
    }
}
