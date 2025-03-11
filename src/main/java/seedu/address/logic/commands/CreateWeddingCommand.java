package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.wedding.Wedding;

public class CreateWeddingCommand extends Command {

    public static final String COMMAND_WORD = "createWedding";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new wedding.\n"
        + "Parameters: NAME\n"
        + "Example: " + COMMAND_WORD + " John and Jane's Wedding";

    public static final String MESSAGE_SUCCESS = "New wedding created: %1$s";

    private final String weddingName;

    public CreateWeddingCommand(String weddingName) {
        this.weddingName = weddingName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Wedding wedding = new Wedding(weddingName);
        model.addWedding(wedding);
        return new CommandResult(String.format(MESSAGE_SUCCESS, wedding));
    }
}