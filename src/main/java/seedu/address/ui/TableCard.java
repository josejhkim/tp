package seedu.address.ui;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.DietaryRestriction;
import seedu.address.model.person.Person;
import seedu.address.model.person.Rsvp;
import seedu.address.model.table.Table;

/**
 * An UI component that displays information of a {@code Table}.
 */
public class TableCard extends UiPart<Region> {

    private static final String FXML = "TableListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As a consequence, UI
     * elements' variable names cannot be set to such keywords or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Table table;

    @FXML
    private Label tableId;

    @FXML
    private Label capacity;

    @FXML
    private Label occupancy;

    @FXML
    private VBox persons;

    @FXML
    private FlowPane dietaryRestrictions;

    @FXML
    private FlowPane rsvpCounts;

    /**
     * Creates a {@code TableCode} with the given {@code Table} and index to display.
     */
    public TableCard(Table table, int displayedIndex) {
        super(FXML);
        this.table = table;

        tableId.setText("TableId: " + String.valueOf(table.getTableId()));
        capacity.setText("Capacity: " + table.getCapacity());
        occupancy.setText("Occupancy: " + table.getSize() + "/" + table.getCapacity());
        
        // Display persons assigned to this table, one per row
        table.getAllPersonsNames().forEach(personName -> {
            Label personLabel = new Label(personName.fullName);
            personLabel.getStyleClass().add("guest-name");
            persons.getChildren().add(personLabel);
        });

        // Count and display dietary restrictions for this table
        displayDietaryRestrictionCounts();

        // Count and display RSVP statuses for this table
        displayRsvpCounts();
    }

    /**
     * Counts and displays the number of each dietary restriction among people at the table.
     */
    private void displayDietaryRestrictionCounts() {
        Map<String, Integer> restrictionCounts = new HashMap<>();

        // Count dietary restrictions using the DietaryRestriction field
        for (Person person : table.getAllPersons()) {
            DietaryRestriction dietRestriction = person.getDietaryRestriction();
            if (dietRestriction != null
            &&
                dietRestriction.getTypicalRestriction() != DietaryRestriction.TypicalRestriction.NONE) {
                String restriction = dietRestriction.toString();
                restrictionCounts.put(restriction, restrictionCounts.getOrDefault(restriction, 0) + 1);
            }
        }
        
        // Display the counts
        restrictionCounts.forEach((restriction, count) -> {
            Label label = new Label(restriction + ": " + count);
            label.getStyleClass().add("dietary-restriction");
            dietaryRestrictions.getChildren().add(label);
        });
    }

    /**
     * Counts and displays the number of each RSVP status among people at the table.
     */
    private void displayRsvpCounts() {
        Map<String, Integer> rsvpStatusCounts = new HashMap<>();

        // Count RSVP statuses
        for (Person person : table.getAllPersons()) {
            Rsvp rsvp = person.getRsvp();
            if (rsvp != null) {
                String status = rsvp.toString();
                rsvpStatusCounts.put(status, rsvpStatusCounts.getOrDefault(status, 0) + 1);
            }
        }

        // Display the counts
        rsvpStatusCounts.forEach((status, count) -> {
            Label label = new Label(status + ": " + count);
            label.getStyleClass().add("rsvp-status");
            rsvpCounts.getChildren().add(label);
        });
    }
}
