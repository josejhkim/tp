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

public class AddGuestToTableCommand extends Command {

    public static final String COMMAND_WORD = "addGuestToTable";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Adds the guest identified by name "
        + "to the table identified by the table ID.\n"
        + "If the guest was previously seated at another table, "
        + "they will forcefully be removed from that table "
        + "to be relocated to the given table.\n"
        + "Parameters: "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_TABLE_ID + "TABLE ID]\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_NAME + "John Doe"
        + PREFIX_TABLE_ID + "2";

    public static final String MESSAGE_ADD_GUEST_TO_TABLE_SUCCESS = "Added Guest: %s to Table: %d";
    public static final String MESSAGE_DUPLICATE_PERSON = "This guest already exists in the address book.";

    private final Name guestName;
    private final int newTableId;

    public AddGuestToTableCommand(Name guestName, int newTableId) {
        requireNonNull(guestName);
        requireNonNull(newTableId);

        this.guestName = guestName;
        this.newTableId = newTableId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Wedding currentWedding = model.getCurrentWedding();
        if (currentWedding == null) {
            throw new CommandException("No current wedding");
        }

        Guest guestToAdd = currentWedding.findGuestByName(guestName);
        Guest addedGuest = createAddedGuest(currentWedding, guestToAdd, newTableId);

        if (!guestToAdd.equals(addedGuest) && currentWedding.hasGuest(addedGuest)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        currentWedding.setGuest(guestToAdd, addedGuest);
        currentWedding.getTableList().removeGuestFromTable(newTableId, guestToAdd);
        currentWedding.getTableList().assignGuestToTable(newTableId, addedGuest);
        return new CommandResult(String.format(MESSAGE_ADD_GUEST_TO_TABLE_SUCCESS, addedGuest.getName().fullName, newTableId));
    }


    private static Guest createAddedGuest(Wedding wedding, Guest guestToAdd, int newTableId) throws CommandException {
        Table tableToAdd = wedding.getTableList().findTableById(newTableId);

        Guest addedGuest = new Guest(guestToAdd, tableToAdd);

        return addedGuest;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddGuestToTableCommand)) {
            return false;
        }

        AddGuestToTableCommand otherAddGuestToTableCommand = (AddGuestToTableCommand) other;
        return guestName.equals(otherAddGuestToTableCommand.guestName)
            && newTableId == otherAddGuestToTableCommand.newTableId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("AddedGuest", guestName)
            .add("AddedTable", newTableId)
            .toString();
    }
}
