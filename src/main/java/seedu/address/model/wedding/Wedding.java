package seedu.address.model.wedding;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.RsvpList;
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
    private final RsvpList rsvpList;
    private final UniquePersonList uniquePersonList;
    private final UniqueTableList tableList; // All tables for seating
    /**
     * Constructs a {@code Wedding}.
     *
     * @param name A table name.
     */
    public Wedding(String name) {
        this.name = name;
        this.rsvpList = new RsvpList();
        this.uniquePersonList = new UniquePersonList();
        this.tableList = new UniqueTableList();
    }

    public String getName() {
        return name;
    }

    public RsvpList getRsvpList() {
        return rsvpList;
    }

    public UniquePersonList getUniquePersonList() {
        return uniquePersonList;
    }

    public UniqueTableList getTableList() {
        return tableList;
    }

    //=========== Persons ================================================================================

    public void addPerson(Person person) {
        uniquePersonList.add(person);
    }

    public void deletePerson(Person person) {
        uniquePersonList.delete(person);
    }

    public void setPerson(Person target, Person editedPerson) {
        uniquePersonList.setPerson(target, editedPerson);
    }
    
    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        this.uniquePersonList.setPersons(replacement);
    }

    /**
     * Finds a person from the table with the matching name.
     * Throws a PersonNotFoundException if there isn't any
     * @param name Name of the person to find
     * @return A Person object with the corresponding name
     * @throws PersonNotFoundException
     */
    public Person findPersonByName(Name name) throws PersonNotFoundException {
        return uniquePersonList.findPersonByName(name);
    }

    public boolean hasPerson(Person person) {
        return uniquePersonList.contains(person);
    }

    //=========== Tables ================================================================================

    public void addTable(Table table) {
        this.tableList.addTable(table);
    }

    public void deleteTable(Table table) {
        tableList.deleteTable(table);
    }

    public void deleteTable(int tableId) {
        Table table = tableList.findTable(tableId);
        deleteTable(table);
    }

    public Table findTable(Table table) {
        return tableList.findTable(table);
    }

    public Table findTable(int tableId) {
        return tableList.findTable(tableId);
    }

    public boolean hasTable(Table table) {
        return tableList.contains(table);
    }

    public boolean hasTable(int tableId) {
        return tableList.hasTable(tableId);
    }

    public void setTable(Table target, Table editedPerson) {
        tableList.setTable(target, editedPerson);
    }

    public Table getTable(int tableId) {
        return tableList.findTable(tableId);
    }

    public void addPersonToTable(Person p, Table table) {
        if (tableList.findTable(table) == null) {
            throw new TableNotFoundException();
        }

        table.addPerson(p);
    }

    public void addPersonToTable(Person p, int tableId) {
        Table table = tableList.findTable(tableId);

        if (table == null) {
            throw new TableNotFoundException();
        }

        table.addPerson(p);
    }

    public void deletePersonFromTable(Person p, Table table) {
        if (tableList.findTable(table) == null) {
            throw new TableNotFoundException();
        }

        table.deletePerson(p);
    }

    public void deletePersonFromTable(Person p, int tableId) {
        Table table = tableList.findTable(tableId);

        if (table == null) {
            throw new TableNotFoundException();
        }

        table.deletePerson(p);
    }

    //=========== Utils ================================================================================

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Wedding)) {
            return false;
        }
        Wedding otherWedding = (Wedding) other;
        return Objects.equals(name, otherWedding.name)
            && Objects.equals(uniquePersonList, otherWedding.uniquePersonList)
            && Objects.equals(tableList, otherWedding.tableList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, uniquePersonList, tableList);
    }

    @Override
    public String toString() {
        return "Wedding{name='" + name + "', people= " + uniquePersonList + '}';
    }

    public boolean isSameWedding(Wedding otherWedding) {
        if (otherWedding == this) {
            return true;
        }

        return otherWedding != null
            && otherWedding.getName().equals(getName());
    }
}
