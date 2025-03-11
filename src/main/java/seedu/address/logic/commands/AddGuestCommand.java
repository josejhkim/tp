package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Guest;
import seedu.address.model.wedding.Wedding;

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

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Wedding currentWedding = model.getCurrentWedding();
        if (currentWedding == null) {
            throw new CommandException("Wedding ");
        }
        currentWedding.addGuest(guest);
        return new CommandResult(String.format(MESSAGE_SUCCESS, guest));
    }
}