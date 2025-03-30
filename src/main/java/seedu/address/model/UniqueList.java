package seedu.address.model;

/**
 * Interface for things related to unique lists that are displayed in the GUI.
 * Used as an interface to deal with ObservableLists.
 * @param <T>
 */
public interface UniqueList<T> {
    /**
     * Clear the internal storage of all items.
     * Useful for JavaFX GUI when choosing to display a different wedding info.
     */
    public void clear();

    /**
     * Clears the current data
     * and loads the data from another UniqueList of this type.
     * @param other
     */
    public void loadData(UniqueList<T> other);

    /**
     * Gets all the current data in an iterable format
     * @return An Iterable object containing all the current data
     */
    public Iterable<T> getListItems();
}
