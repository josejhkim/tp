package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.wedding.Wedding;
/**
 * Sets the current wedding in the model.
 */
public class SetWeddingCommand extends Command {

    public static final String COMMAND_WORD = "setWedding";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the current wedding.\n"
        + "Parameters: WEDDING_NAME\n"
        + "Example: " + COMMAND_WORD + " John and Jane's Wedding";

    public static final String MESSAGE_SUCCESS = "Current wedding set to: %1$s";
    public static final String MESSAGE_WEDDING_NOT_FOUND = "Wedding not found";

    private final String weddingName;

    public SetWeddingCommand(String weddingName) {
        this.weddingName = weddingName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Wedding wedding = model.findWeddingByName(weddingName);
        if (wedding == null) {
            throw new CommandException(MESSAGE_WEDDING_NOT_FOUND);
        }
        model.setCurrentWedding(wedding);
        return new CommandResult(String.format(MESSAGE_SUCCESS, weddingName));
    }
    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof SetWeddingCommand
            && weddingName.equals(((SetWeddingCommand) other).weddingName));
    }
}
