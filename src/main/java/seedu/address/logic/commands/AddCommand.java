package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.wedding.Wedding;

/**
 * Adds a guest to the current wedding.
 */
public class AddCommand extends Command {
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    public static final String COMMAND_WORD = "addGuest";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a guest to the current wedding.\n"
        + "Parameters: "
        + "n/NAME p/PHONE e/EMAIL a/ADDRESS d/DIETARY_RESTRICTION r/RSVP\n"
        + "Example: " + COMMAND_WORD + " "
        + "n/John Doe p/12345678 e/johndoe@example.com a/123 Street d/None r/YES";

    public static final String MESSAGE_SUCCESS = "Person added to wedding: %1$s";
    public static final String MESSAGE_NO_CURRENT_WEDDING =
        "No current wedding set. Please set a current wedding first using the setWedding command.";

    private final Person guest;
    /**
     * Creates an AddGuestCommand to add the specified {@code Person} to the rsvp list in the current wedding.
     */
    public AddCommand(Person guest) {
        requireNonNull(guest);
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
            throw new CommandException(MESSAGE_NO_CURRENT_WEDDING);
        }

        if (currentWedding.hasGuest(guest)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        currentWedding.addGuest(guest);
        return new CommandResult(String.format(MESSAGE_SUCCESS, guest));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddCommand)) {
            return false;
        }
        AddCommand otherCommand = (AddCommand) other;
        return guest.toString().equals(otherCommand.guest.toString());
    }

    @Override
    public int hashCode() {
        return guest.hashCode();
    }

    @Override
    public String toString() {
        return "seedu.address.logic.commands.AddCommand{toAdd=" + guest + "}";
    }
}
