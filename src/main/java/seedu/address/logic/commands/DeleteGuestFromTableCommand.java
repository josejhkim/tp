package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TABLE_ID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GUESTS;
import java.util.List;
import java.util.Optional;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Name;
import seedu.address.model.table.Table;
import seedu.address.model.wedding.Wedding;

public class DeleteGuestFromTableCommand extends Command {

    public static final String COMMAND_WORD = "deleteGuestFromTable";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the guest identified by name "
        + "from the table identified by the table ID.\n"
        + "Parameters: "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_TABLE_ID + "TABLE ID]\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_NAME + "John Doe"
        + PREFIX_TABLE_ID + "2";

    public static final String MESSAGE_REMOVED_GUEST_FROM_TABLE_SUCCESS = "Deleted Guest: %s from Table: %d";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This guest already exists in the address book.";

    private final Name guestName;
    private final int oldTableId;

    public DeleteGuestFromTableCommand(Name guestName, int oldTableId) {
        requireNonNull(guestName);
        requireNonNull(oldTableId);

        this.guestName = guestName;
        this.oldTableId = oldTableId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Wedding currentWedding = model.getCurrentWedding();
        if (currentWedding == null) {
            throw new CommandException("No current wedding");
        }

        Guest guestToRemove = currentWedding.findGuestByName(guestName);

        Guest removedGuest = createRemovedGuest(model.getCurrentWedding(), guestToRemove, oldTableId);

        if (!guestToRemove.equals(removedGuest) && currentWedding.hasGuest(removedGuest)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        currentWedding.setGuest(guestToRemove, removedGuest);
        currentWedding.getTableList().removeGuestFromTable(oldTableId, guestToRemove);
        return new CommandResult(String.format(MESSAGE_REMOVED_GUEST_FROM_TABLE_SUCCESS, removedGuest.getName().fullName,
            oldTableId));
    }

    private static Guest createRemovedGuest(Wedding wedding, Guest guestToRemove, int oldTableId) throws CommandException {
        return new Guest(guestToRemove, null);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteGuestFromTableCommand)) {
            return false;
        }

        DeleteGuestFromTableCommand otherRemoveGuestFromTableCommand = (DeleteGuestFromTableCommand) other;
        return guestName.equals(otherRemoveGuestFromTableCommand.guestName)
            && oldTableId == otherRemoveGuestFromTableCommand.oldTableId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("RemovedGuest", guestName)
            .add("RemovedTable", oldTableId)
            .toString();
    }
}
