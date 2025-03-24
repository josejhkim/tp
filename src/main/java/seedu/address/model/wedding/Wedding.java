package seedu.address.model.wedding;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.table.Table;
import seedu.address.model.table.UniqueTableList;
import seedu.address.model.table.exceptions.TableNotFoundException;

/**
 * Represents a Wedding in the address book.
 */
public class Wedding {
    private final String name;
    private final UniquePersonList uniquePersonList;
    private final UniqueTableList tableList; // All tables for seating
    /**
     * Constructs a new {@code Wedding} with the given name.
     * Initializes empty person and table lists.
     *
     * @param name Name of the wedding.
     */
    public Wedding(String name) {
        this.name = name;
        this.uniquePersonList = new UniquePersonList();
        this.tableList = new UniqueTableList();
    }

    /**
     * Constructs a copy of an existing {@code Wedding}.
     *
     * @param wedding The Wedding object to copy from.
     */
    public Wedding(Wedding wedding) {
        this.name = wedding.name;
        this.uniquePersonList = new UniquePersonList(wedding.uniquePersonList);
        this.tableList = new UniqueTableList(wedding.tableList);
    }

    /**
     * Returns the name of the wedding.
     *
     * @return Wedding name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of unique persons for this wedding.
     *
     * @return UniquePersonList object.
     */
    public UniquePersonList getUniquePersonList() {
        return uniquePersonList;
    }

    /**
     * Returns the list of tables for this wedding.
     *
     * @return UniqueTableList object.
     */
    public UniqueTableList getTableList() {
        return tableList;
    }

    //=========== Persons ================================================================================

    /**
     * Adds a person to the wedding guest list.
     *
     * @param person Person to add.
     */
    public void addPerson(Person person) {
        uniquePersonList.add(person);
    }

    /**
     * Deletes a person from the wedding guest list.
     *
     * @param person Person to delete.
     */
    public void deletePerson(Person person) {
        uniquePersonList.delete(person);
    }

    /**
     * Replaces a person in the guest list with an edited version.
     *
     * @param target The person to replace.
     * @param editedPerson The new person data.
     */
    public void setPerson(Person target, Person editedPerson) {
        uniquePersonList.setPerson(target, editedPerson);
    }

    /**
     * Replaces the entire person list with a new one.
     *
     * @param replacement The new UniquePersonList.
     */
    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        this.uniquePersonList.setPersons(replacement);
    }

    /**
     * Finds and returns a person by their name.
     *
     * @param name Name of the person to find.
     * @return The Person with the given name.
     * @throws PersonNotFoundException If no matching person is found.
     */
    public Person findPersonByName(Name name) throws PersonNotFoundException {
        return uniquePersonList.findPersonByName(name);
    }

    /**
     * Checks if the person exists in the wedding guest list.
     *
     * @param person The person to check.
     * @return True if the person exists, false otherwise.
     */
    public boolean hasPerson(Person person) {
        return uniquePersonList.contains(person);
    }

    //=========== Tables ================================================================================

    /**
     * Adds a new table to the wedding.
     *
     * @param table Table to add.
     */
    public void addTable(Table table) {
        this.tableList.addTable(table);
    }

    /**
     * Deletes a table from the wedding.
     *
     * @param table Table to delete.
     */
    public void deleteTable(Table table) {
        tableList.deleteTable(table);
    }

    /**
     * Deletes a table by its ID.
     *
     * @param tableId ID of the table to delete.
     */
    public void deleteTable(int tableId) {
        Table table = tableList.findTable(tableId);
        deleteTable(table);
    }

    /**
     * Finds a table matching the given table object.
     *
     * @param table The table to find.
     * @return Matching Table object.
     */
    public Table findTable(Table table) {
        return tableList.findTable(table);
    }

    /**
     * Finds a table by its ID.
     *
     * @param tableId The ID of the table.
     * @return Matching Table object.
     */
    public Table findTable(int tableId) {
        return tableList.findTable(tableId);
    }

    /**
     * Checks if the table exists in the wedding.
     *
     * @param table The table to check.
     * @return True if the table exists, false otherwise.
     */
    public boolean hasTable(Table table) {
        return tableList.contains(table);
    }

    /**
     * Checks if a table with the given ID exists.
     *
     * @param tableId The ID to check.
     * @return True if the table exists, false otherwise.
     */
    public boolean hasTable(int tableId) {
        return tableList.hasTable(tableId);
    }

    /**
     * Replaces an existing table with a new one.
     *
     * @param target The table to replace.
     * @param editedPerson The new table data.
     */
    public void setTable(Table target, Table editedPerson) {
        tableList.setTable(target, editedPerson);
    }

    /**
     * Gets a table by its ID.
     *
     * @param tableId The ID of the table.
     * @return Table object with the given ID.
     */
    public Table getTable(int tableId) {
        return tableList.findTable(tableId);
    }

    /**
     * Adds a person to a specific table.
     *
     * @param p The person to add.
     * @param table The table to add the person to.
     * @throws TableNotFoundException If the table doesn't exist.
     */
    public void addPersonToTable(Person p, Table table) {
        if (tableList.findTable(table) == null) {
            throw new TableNotFoundException();
        }

        table.addPerson(p);
    }

    /**
     * Adds a person to a table using table ID.
     *
     * @param p The person to add.
     * @param tableId The ID of the table.
     * @throws TableNotFoundException If the table doesn't exist.
     */
    public void addPersonToTable(Person p, int tableId) {
        Table table = tableList.findTable(tableId);

        if (table == null) {
            throw new TableNotFoundException();
        }

        table.addPerson(p);
    }

    /**
     * Removes a person from a table.
     *
     * @param p The person to remove.
     * @param table The table to remove the person from.
     * @throws TableNotFoundException If the table doesn't exist.
     */
    public void deletePersonFromTable(Person p, Table table) {
        if (tableList.findTable(table) == null) {
            throw new TableNotFoundException();
        }

        table.deletePerson(p);
    }

    /**
     * Removes a person from a table by ID.
     *
     * @param p The person to remove.
     * @param tableId The ID of the table.
     * @throws TableNotFoundException If the table doesn't exist.
     */
    public void deletePersonFromTable(Person p, int tableId) {
        Table table = tableList.findTable(tableId);

        if (table == null) {
            throw new TableNotFoundException();
        }

        table.deletePerson(p);
    }

    //=========== Utils ================================================================================

    /**
     * Checks if two weddings are equal.
     *
     * @param other The other object to compare with.
     * @return True if equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Wedding)) {
            return false;
        }
        Wedding otherWedding = (Wedding) other;

        return name.equals(otherWedding.name)
            && uniquePersonList.equals(otherWedding.uniquePersonList)
            && tableList.equals(otherWedding.tableList);
    }

    /**
     * Returns the hash code for the wedding.
     *
     * @return Hash code integer.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, uniquePersonList, tableList);
    }

    /**
     * Returns a string representation of the wedding.
     *
     * @return String describing the wedding.
     */
    @Override
    public String toString() {
        return "Wedding{name='" + name + "', people= " + uniquePersonList + '}';
    }

    /**
     * Checks if another wedding is the same based on name.
     *
     * @param otherWedding The wedding to compare with.
     * @return True if same, false otherwise.
     */
    public boolean isSameWedding(Wedding otherWedding) {
        if (otherWedding == this) {
            return true;
        }

        return otherWedding != null
            && otherWedding.getName().equals(getName());
    }
}
