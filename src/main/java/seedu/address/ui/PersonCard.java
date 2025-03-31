package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.DietaryRestriction;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As a consequence, UI
     * elements' variable names cannot be set to such keywords or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label dietaryRestriction;
    @FXML
    private Label rsvp;
    @FXML
    private Label table;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        dietaryRestriction.setText("Dietary restriction: " + person.getDietaryRestriction().toString());

        // Update to style the dietary restriction label with the same colors as TableCard
        DietaryRestriction dietRestriction = person.getDietaryRestriction();
        if (dietRestriction != null
            &&
                dietRestriction.getTypicalRestriction() != DietaryRestriction.TypicalRestriction.NONE) {
            String restriction = dietRestriction.toString();
            dietaryRestriction.getStyleClass().add("dietary-restriction");

            // Add specific style class based on the type of restriction
            switch (restriction) {
            case "VEGETARIAN":
                dietaryRestriction.getStyleClass().add("vegetarian-restriction");
                break;
            case "VEGAN":
                dietaryRestriction.getStyleClass().add("vegan-restriction");
                break;
            case "HALAL":
                dietaryRestriction.getStyleClass().add("halal-restriction");
                break;
            case "SHELLFISH":
                dietaryRestriction.getStyleClass().add("shellfish-restriction");
                break;
            case "PEANUTS":
                dietaryRestriction.getStyleClass().add("peanuts-restriction");
                break;
            case "EGGS":
                dietaryRestriction.getStyleClass().add("eggs-restriction");
                break;
            case "FISH":
                dietaryRestriction.getStyleClass().add("fish-restriction");
                break;
            case "SOY":
                dietaryRestriction.getStyleClass().add("soy-restriction");
                break;
            case "SESAME":
                dietaryRestriction.getStyleClass().add("sesame-restriction");
                break;
            default:
                dietaryRestriction.getStyleClass().add("other-restriction");
                break;
            }
        }

        rsvp.setText("RSVP Status: " + person.getRsvp().getStatus().toString());

        // Style the RSVP status label based on the status
        String rsvpStatus = person.getRsvp().getStatus().toString();
        rsvp.getStyleClass().add("rsvp-status");

        switch (rsvpStatus) {
        case "YES":
            rsvp.getStyleClass().add("rsvp-yes");
            break;
        case "NO":
            rsvp.getStyleClass().add("rsvp-no");
            break;
        case "NO_RESPONSE":
            rsvp.getStyleClass().add("rsvp-no-response");
            break;
        default:
            break;
        }

        if (person.isSeated()) {
            table.setText("TableId: " + person.getTableIdString());
        } else {
            table.setText("No table assigned");
        }
    }
}
