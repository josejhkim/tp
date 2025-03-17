package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.wedding.Wedding;
/**
 * Creates a new wedding in the model.
 */
public class CreateWeddingCommand extends Command {

    public static final String COMMAND_WORD = "createWedding";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new wedding.\n"
        + "Parameters: NAME\n"
        + "Example: " + COMMAND_WORD + " John and Jane's Wedding";

    //public static final String MESSAGE_SUCCESS = "New wedding created: %1$s";
    public static final String MESSAGE_SUCCESS =
            "New wedding created: %1$s\nNumber of tables: %2$d\nNumber of guests: %3$d";

    private final String weddingName;

    public CreateWeddingCommand(String weddingName) {
        this.weddingName = weddingName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Wedding wedding = new Wedding(weddingName);
        model.addWedding(wedding);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                wedding.getName(), wedding.getTableList().getTables().size(),
                wedding.getRsvpList().getAllGuests().size()));

        //return new CommandResult(String.format(MESSAGE_SUCCESS, wedding));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CreateWeddingCommand)) {
            return false;
        }
        CreateWeddingCommand otherCommand = (CreateWeddingCommand) other;
        return weddingName.equals(otherCommand.weddingName);
    }
}
