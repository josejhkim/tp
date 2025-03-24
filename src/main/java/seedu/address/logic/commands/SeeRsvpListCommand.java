package seedu.address.logic.commands;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Command to see the RSVP list of the current wedding.
 */
public class SeeRsvpListCommand extends Command {

    public static final String COMMAND_WORD = "seeRSVPList";
    public static final String MESSAGE_SUCCESS = "RSVP List:\n%1$s";

    @Override
    public CommandResult execute(Model model) {
        List<Person> guests = model.getFilteredPersonList();
        StringBuilder result = new StringBuilder();
        for (Person guest : guests) {
            result.append(guest.toString()).append("\n");
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, result.toString().trim()));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof SeeRsvpListCommand;
    }
}
