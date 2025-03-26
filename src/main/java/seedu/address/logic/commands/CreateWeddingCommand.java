package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.wedding.Wedding;

/**
 * Represents a command to create a new wedding in the model.
 * If a wedding already exists, the user must delete it first before creating a new one.
 */
public class CreateWeddingCommand extends Command {

    public static final String COMMAND_WORD = "createWedding";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new wedding.\n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD + " John and Jane's Wedding";

    public static final String MESSAGE_SUCCESS = "New wedding created: %1$s";
    public static final String MESSAGE_EXISTING_WEDDING = "A wedding already exists. "
            + "Please delete the current wedding before creating a new one.";
    public static final String MESSAGE_INVALID_NAME = "Wedding name cannot be empty or just spaces.";

    private final String weddingName;

    /**
     * Constructs a CreateWeddingCommand with the specified wedding name.
     *
     * @param weddingName The name of the wedding to be created.
     *                    It must be non-null and contain at least one non-whitespace character.
     * @throws IllegalArgumentException If the wedding name is null or contains only spaces.
     */
    public CreateWeddingCommand(String weddingName) {
        requireNonNull(weddingName, "Wedding name cannot be null");
        if (weddingName.trim().isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_INVALID_NAME);
        }
        this.weddingName = weddingName;
    }

    /**
     * Executes the CreateWeddingCommand, creating a new wedding if none exists.
     * If a wedding already exists, an error is thrown, requiring the user to delete it first.
     *
     * @param model The model that manages the application's data.
     * @return A CommandResult indicating the outcome of the command execution.
     * @throws CommandException If a wedding already exists and has not been deleted.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getCurrentWedding() != null) {
            throw new CommandException(MESSAGE_EXISTING_WEDDING);
        }

        // No existing wedding, proceed to create a new one
        Wedding newWedding = new Wedding(weddingName);
        model.addWedding(newWedding);
        model.setCurrentWedding(newWedding);

        return new CommandResult(String.format(MESSAGE_SUCCESS, weddingName));
    }

    /**
     * Checks whether this command is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the object is an instance of CreateWeddingCommand with the same wedding name.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof CreateWeddingCommand
                && weddingName.equals(((CreateWeddingCommand) other).weddingName));
    }

    /**
     * Generates a hash code for this command based on the wedding name.
     *
     * @return The hash code of the wedding name.
     */
    @Override
    public int hashCode() {
        return weddingName.hashCode();
    }
}
