package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;


import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.category.Category;
import seedu.address.model.person.category.Rsvp;
import seedu.address.model.person.category.DietaryRestriction;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.RsvpList;

import seedu.address.model.person.Guest;

public class FilterGuestCommand extends Command {
    public static final String COMMAND_WORD = "filterGuest";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the guests belonging to respective "
            + "category and field.\n "
            + "Parameters: c/CATEGORY f/FIELD\n"
            + "Example: " + COMMAND_WORD + " c/RSVP" + " f/YES";

    public static final String MESSAGE_WEDDING_NOT_FOUND = "Wedding not found";
    public static final String MESSAGE_SUCCESS = "Guests that have been filtered out: %1$s";
    public static final String MESSAGE_MISSING_CATEGORY = "Please enter a category";
    public static final String MESSAGE_NO_SUCH_CATEGORY = "Please enter a valid category";

    private final Category category;

    public FilterGuestCommand(Category category) {
        // To change to classes?
        this.category = category;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        var wedding = model.getCurrentWedding();
        if (wedding == null) {
            throw new CommandException(MESSAGE_WEDDING_NOT_FOUND);
        }
        if (category == null) {
            throw new CommandException(MESSAGE_MISSING_CATEGORY);
        }

        RsvpList rsvpList = wedding.getRsvpList();
        List<Guest> guests = rsvpList.getAllGuests();
        StringBuilder guestList = new StringBuilder();
        for (Guest guest: guests) {
            if (category instanceof DietaryRestriction) {
                if (guest.getDietaryRestriction().equals(this.category)) {
                    guestList.append(guest.getDietaryRestriction());
                }
            } else if (category instanceof Rsvp) {
                if (guest.getRsvp().equals(this.category)) {
                    guestList.append(guest.getRsvp());
                }
            }
        }
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, wedding.getName(), guestList.toString().trim()));
    }

    @Override
    public String toString() {
        // TODO
            return "FilterGuestList";
        }
}
