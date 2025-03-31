package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
    private FlowPane persons;

    /**
     * Creates a {@code TableCode} with the given {@code Table} and index to display.
     */
    public TableCard(Table table, int displayedIndex) {
        super(FXML);
        this.table = table;
        
        tableId.setText("Table: " + String.valueOf(table.getTableId()));
        capacity.setText("Capacity: " + table.getCapacity());
        occupancy.setText("Occupancy: " + table.getSize() + "/" + table.getCapacity());
        
        // Display persons assigned to this table
        table.getAllPersonsNames().forEach(personName -> {
            persons.getChildren().add(new Label(personName.fullName));
        });
    }
}
