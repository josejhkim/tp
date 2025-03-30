package seedu.address.model.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.exceptions.PersonAlreadySeatedException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.table.exceptions.TableFullException;
import seedu.address.testutil.PersonBuilder;

public class TableTest {

    private Table table;
    private Person person1;
    private Person person2;

    @BeforeEach
    public void setUp() {
        table = new Table(1, 2); // tableId = 1, capacity = 2
        person1 = new PersonBuilder(ALICE).build();
        person2 = new PersonBuilder(BOB).build();
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Table(0, 5));
    }

    @Test
    public void constructor_invalidCapacity_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Table(1, -1));
    }

    @Test
    public void addPerson_validPerson_success() {
        table.addPerson(person1);
        assertEquals(1, table.getSize());
        assertTrue(table.getAllPersons().contains(person1));
    }

    // currently not needed as adding a person that's already seated at that table doesn't do anything
    //    @Test
    //    public void addPerson_duplicatePerson_throwsPersonAlreadySeatedException() {
    //        table.addPerson(person1);
    //        assertThrows(PersonAlreadySeatedException.class, () -> table.addPerson(person1));
    //    }

    @Test
    public void addPerson_tableFull_throwsTableFullException() {
        table.addPerson(person1);
        table.addPerson(person2);
        Person thirdPerson = new PersonBuilder().withName("Charlie").build();
        assertThrows(TableFullException.class, () -> table.addPerson(thirdPerson));
    }

    @Test
    public void deletePerson_personExists_success() {
        table.addPerson(person1);
        table.deletePerson(person1);
        assertEquals(0, table.getSize());
        assertFalse(table.getAllPersons().contains(person1));
    }

    @Test
    public void deletePerson_personNotFound_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> table.deletePerson(person1));
    }

    @Test
    public void getAllPersonsNames_returnsCorrectNames() {
        table.addPerson(person1);
        table.addPerson(person2);
        List<?> names = table.getAllPersonsNames();
        assertTrue(names.contains(person1.getName()));
        assertTrue(names.contains(person2.getName()));
        assertEquals(2, names.size());
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        table.addPerson(person1);
        Table same = new Table(1, 2);
        same.addPerson(person1);
        assertTrue(table.equals(same));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        table.addPerson(person1);
        Table different = new Table(2, 2); // different ID
        different.addPerson(person1);
        assertFalse(table.equals(different));
    }

    @Test
    public void toString_containsDetails() {
        table.addPerson(person1);
        String result = table.toString();
        assertTrue(result.contains("Table{ID=1"));
        assertTrue(result.contains(person1.getName().toString()));
    }
}
