package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TABLE_ID;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.table.Table;

/**
 * Deletes a guest from the given table.
 */
public class DeletePersonFromTableCommand extends Command {

    public static final String COMMAND_WORD = "deletePersonFromTable";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the guest identified by name "
        + "from the table identified by the table ID.\n"
        + "Parameters: "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_TABLE_ID + "TABLE ID]\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_NAME + "John Doe"
        + PREFIX_TABLE_ID + "2";

    public static final String MESSAGE_REMOVED_GUEST_FROM_TABLE_SUCCESS = "Deleted Person: %s from Table: %d";

    private final Name guestName;
    private final int oldTableId;

    /**
     * @param guestName Name of the guest to delete from the table
     * @param oldTableId Int ID of the table to delete the guest from
     */
    public DeletePersonFromTableCommand(Name guestName, int oldTableId) {
        requireNonNull(guestName);
        requireNonNull(oldTableId);

        this.guestName = guestName;
        this.oldTableId = oldTableId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // 1. Check if wedding is set
        if (!model.hasCurrentWedding()) {
            throw new CommandException("No current wedding set. Please use 'setWedding' first.");
        }

        // 2. Try to find the person by name, catch if not found
        Person guestToRemove;
        try {
            guestToRemove = model.findPersonByName(guestName);
        } catch (seedu.address.model.person.exceptions.PersonNotFoundException e) {
            throw new CommandException(String.format("Person '%s' not found in the guest list.", guestName.fullName));
        }

        // 3. Check if the table exists
        if (!model.hasTable(oldTableId)) {
            throw new CommandException(String.format("Table with ID %d does not exist.", oldTableId));
        }

        Table table = model.getCurrentWedding().findTableById(oldTableId);
        if (table == null) {
            throw new CommandException(String.format("Table with ID %d could not be found.", oldTableId));
        }

        // 4. Check if the person is assigned to the table
        try {
            table.findPerson(guestToRemove); // throws if not assigned
        } catch (PersonNotFoundException e) {
            throw new CommandException(String.format("Person '%s' is not assigned to Table %d.",
                    guestName.fullName, oldTableId));
        }

        // 5. Safe to delete
        model.deletePersonFromTableById(guestToRemove, oldTableId);

        return new CommandResult(String.format(MESSAGE_REMOVED_GUEST_FROM_TABLE_SUCCESS,
                guestToRemove.getName().fullName, oldTableId));
    }



    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePersonFromTableCommand)) {
            return false;
        }

        DeletePersonFromTableCommand otherRemovePersonFromTableCommand = (DeletePersonFromTableCommand) other;
        return guestName.equals(otherRemovePersonFromTableCommand.guestName)
            && oldTableId == otherRemovePersonFromTableCommand.oldTableId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("RemovedPerson", guestName)
            .add("RemovedTable", oldTableId)
            .toString();
    }
}
