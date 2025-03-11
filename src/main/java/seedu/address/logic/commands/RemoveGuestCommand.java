package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Phone;
import seedu.address.model.wedding.Wedding;
/**
 * Removes a guest from the current wedding.
 */
public class RemoveGuestCommand extends Command {

    public static final String COMMAND_WORD = "removeGuest";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a guest from the current wedding.\n"
        + "Parameters: p/GUEST_PHONE_NUMBER or gid/GUEST_ID\n"
        + "Example: " + COMMAND_WORD + " p/91234567 or " + COMMAND_WORD + " gid/123";

    public static final String MESSAGE_SUCCESS = "Guest removed from wedding: %1$s";
    public static final String MESSAGE_NO_CURRENT_WEDDING = "No current wedding set."
        + " Please set a current wedding first.";
    public static final String MESSAGE_GUEST_NOT_FOUND = "No guest found with the provided details."
        + " Please check and try again.";

    private final Phone phone;
    private final int guestId;
    /**
     * Creates a RemoveGuestCommand to remove the guest with the specified phone number.
     */
    public RemoveGuestCommand(Phone phone, int guestId) {
        this.phone = phone;
        this.guestId = guestId;
    }

    /**
     * Executes the RemoveGuestCommand.
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} that describes the result of executing the command.
     * @throws CommandException if the current wedding is not set or the guest is not found.
     */

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Wedding currentWedding = model.getCurrentWedding();
        if (currentWedding == null) {
            throw new CommandException(MESSAGE_NO_CURRENT_WEDDING);
        }
        Guest guestToRemove = null;
        if (phone != null) {
            guestToRemove = model.findGuestByPhone(currentWedding, phone);
        } else if (guestId != 0) {
            guestToRemove = model.findGuestByGuestId(currentWedding, guestId);
        }

        if (guestToRemove == null) {
            throw new CommandException(MESSAGE_GUEST_NOT_FOUND);
        }
        currentWedding.removeGuest(guestToRemove);
        return new CommandResult(String.format(MESSAGE_SUCCESS, guestToRemove));
    }
}
