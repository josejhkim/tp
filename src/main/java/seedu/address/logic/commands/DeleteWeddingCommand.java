package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.wedding.Wedding;

/**
 * Deletes the current wedding in the model.
 */
public class DeleteWeddingCommand extends Command {

    public static final String COMMAND_WORD = "deleteWedding";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the current wedding.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Wedding deleted: %1$s";
    public static final String MESSAGE_NO_WEDDING = "No wedding exists to delete.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Get the currently stored Wedding
        Wedding wedding = model.getCurrentWedding();
        if (wedding == null) {
            throw new CommandException(MESSAGE_NO_WEDDING);
        }

        // Delete the wedding
        model.deleteWedding();

        return new CommandResult(String.format(MESSAGE_SUCCESS, wedding.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof DeleteWeddingCommand;
    }
}
