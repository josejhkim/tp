package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.wedding.Wedding;
/**
 * Removes a guest from the current wedding.
 */
public class DeleteGuestCommand extends Command {

    public static final String COMMAND_WORD = "deleteGuest";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a guest from the current wedding.\n"
        + "Parameters: p/GUEST_PHONE_NUMBER or gid/GUEST_ID\n"
        + "Example: " + COMMAND_WORD + " n/ John Doe or " + COMMAND_WORD + " gid/123";
 

    public static final String MESSAGE_SUCCESS = "Guest removed from wedding: %1$s";
    public static final String MESSAGE_NO_CURRENT_WEDDING = "No current wedding set."
        + " Please set a current wedding first.";
    public static final String MESSAGE_GUEST_NOT_FOUND = "No guest found with the provided details."
        + " Please check and try again.";

    private final Phone phone;

    private final Name guestName;
    /**
     * Creates a RemoveGuestCommand to remove the guest with the specified phone number.
     */
    public DeleteGuestCommand(Phone phone, Name guestName) {
        this.phone = phone;
        this.guestName = guestName;
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
            guestToRemove = currentWedding.findGuestByPhone(phone);
        } else if (guestName != null) {
            guestToRemove = currentWedding.findGuestByName(guestName);
        }

        if (guestToRemove == null) {
            throw new CommandException(MESSAGE_GUEST_NOT_FOUND);
        }

        currentWedding.removeGuest(guestToRemove);
        return new CommandResult(String.format(MESSAGE_SUCCESS, guestToRemove));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DeleteGuestCommand)) {
            return false;
        }
        DeleteGuestCommand otherCommand = (DeleteGuestCommand) other;
        return (phone == null ? otherCommand.phone == null : phone.equals(otherCommand.phone))
            && (guestName == null ? otherCommand.guestName == null : guestName.equals(otherCommand.guestName));

    }
}
