package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * Component that displays the wedding name.
 */
public class WeddingName extends UiPart<Region> {

    private static final String FXML = "WeddingName.fxml";

    @FXML
    private Label weddingNameLabel;

    // Observable property to hold wedding name
    private final StringProperty weddingNameProperty = new SimpleStringProperty("Not Set");

    /**
     * Creates a {@code WeddingName} with the default wedding name.
     */
    public WeddingName() {
        super(FXML);
        // Bind the label text to the property with formatting
        weddingNameLabel.textProperty().bind(weddingNameProperty.map(name -> "Wedding Name: " + name));
    }

    /**
     * Creates a {@code WeddingName} with a specific wedding name.
     */
    public WeddingName(String weddingName) {
        this();
        setWeddingName(weddingName);
    }

    /**
     * Updates the wedding name display.
     * @param weddingName the wedding name to display
     */
    public void setWeddingName(String weddingName) {
        if (weddingName != null && !weddingName.isEmpty()) {
            weddingNameProperty.set(weddingName);
        } else {
            weddingNameProperty.set("Not Set");
        }
    }

    /**
     * Binds the wedding name to an observable value.
     * @param observable the observable string value to bind to
     */
    public void bindWeddingName(ObservableValue<String> observable) {
        weddingNameProperty.unbind(); // Unbind any existing binding
        weddingNameProperty.bind(observable);
    }

    /**
     * Gets the current wedding name property.
     * @return the StringProperty representing the wedding name
     */
    public StringProperty weddingNameProperty() {
        return weddingNameProperty;
    }

    /**
     * Gets the current wedding name value.
     * @return the current wedding name
     */
    public String getWeddingName() {
        return weddingNameProperty.get();
    }
}
