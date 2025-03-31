package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.table.Table;

/**
 * Panel containing the list of tables.
 */
public class TableListPanel extends UiPart<Region> {
    private static final String FXML = "TableListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TableListPanel.class);

    @FXML
    private ListView<Table> tableListView;

    /**
     * Creates a {@code TableListPanel} with the given {@code ObservableList}.
     */
    public TableListPanel(ObservableList<Table> tableList) {
        super(FXML);
        tableListView.setItems(tableList);
        tableListView.setCellFactory(listView -> new TableListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Table} using a {@code TableCard}.
     */
    class TableListViewCell extends ListCell<Table> {
        @Override
        protected void updateItem(Table table, boolean empty) {
            super.updateItem(table, empty);

            if (empty || table == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TableCard(table, getIndex() + 1).getRoot());
            }
        }
    }

}
