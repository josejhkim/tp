package seedu.address.model.table;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

import java.util.List;

class TableTest {
    private Table table;
    private Person person1;
    private Person person2;

    @BeforeEach
    void setUp() {
        table = new Table(1, 5);
        person1 = new Person(new Name("Alice"));
        person2 = new Person(new Name("Bob"));
    }

    @Test
    void constructor_validInputs_success() {
        assertEquals(1, table.getTableId());
        assertEquals(5, table.getCapacity());
        assertTrue(table.getGuests().isEmpty());
    }

    @Test
    void constructor_invalidInputs_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Table(0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Table(1, -1));
    }

    @Test
    void addPerson_personAddedSuccessfully() {
        table.addPerson(person1);
        assertEquals(1, table.getGuests().size());
        assertTrue(table.getGuests().contains(person1));
    }

    @Test
    void deletePerson_personRemovedSuccessfully() {
        table.addPerson(person1);
        table.addPerson(person2);
        table.deletePerson(person1);
        assertEquals(1, table.getGuests().size());
        assertFalse(table.getGuests().contains(person1));
    }

    @Test
    void getGuestNames_returnsCorrectNames() {
        table.addPerson(person1);
        table.addPerson(person2);
        List<Name> guestNames = table.getGuestNames();
        assertTrue(guestNames.contains(person1.getName()));
        assertTrue(guestNames.contains(person2.getName()));
    }

    @Test
    void isSameTable_sameId_returnsTrue() {
        Table anotherTable = new Table(1, 10);
        assertTrue(table.isSameTable(anotherTable));
    }

    @Test
    void isSameTable_differentId_returnsFalse() {
        Table anotherTable = new Table(2, 10);
        assertFalse(table.isSameTable(anotherTable));
    }

    @Test
    void equals_sameProperties_returnsTrue() {
        UniquePersonList uniquePersonList = new UniquePersonList();
        Table anotherTable = new Table(1, 5, uniquePersonList);
        assertEquals(table, anotherTable);
    }

    @Test
    void equals_differentProperties_returnsFalse() {
        Table anotherTable = new Table(2, 5);
        assertNotEquals(table, anotherTable);
    }

    @Test
    void hashCode_consistentWithEquals() {
        Table anotherTable = new Table(1, 5);
        assertEquals(table.hashCode(), anotherTable.hashCode());
    }
}