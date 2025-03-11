package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.wedding.Wedding;
/**
 * Deletes a wedding in the model.
 */

public class DeleteWeddingCommand extends Command {

    public static final String COMMAND_WORD = "deleteWedding";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a wedding.\n"
        + "Parameters: NAME\n"
        + "Example: " + COMMAND_WORD + " John and Jane's Wedding";

    public static final String MESSAGE_SUCCESS = "Wedding deleted: %1$s";

    private final String weddingName;

    public DeleteWeddingCommand(String weddingName) {
        this.weddingName = weddingName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Wedding wedding = model.findWeddingByName(weddingName);
        if (wedding == null) {
            throw new CommandException("Wedding not found");
        }
        model.deleteWedding(wedding);
        return new CommandResult(String.format(MESSAGE_SUCCESS, wedding));
    }
}
