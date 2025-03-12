package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.wedding.Wedding;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    @Test
    public void setWeddings_validList_success() {
        List<Wedding> weddings = new ArrayList<>();
        weddings.add(new Wedding("Wedding 1"));
        weddings.add(new Wedding("Wedding 2"));

        addressBook.setWeddings(weddings);

        assertEquals(weddings, addressBook.getWeddings());
    }

    @Test
    public void setWeddings_emptyList_success() {
        List<Wedding> weddings = new ArrayList<>();

        addressBook.setWeddings(weddings);

        assertEquals(weddings, addressBook.getWeddings());
    }

    @Test
    public void hasWedding_nullWedding_throwsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> addressBook.hasWedding(null));
    }

    @Test
    public void hasWedding_weddingNotInAddressBook_returnsFalse() {
        Wedding wedding = new Wedding("Wedding 1");
        assertFalse(addressBook.hasWedding(wedding));
    }

    @Test
    public void hasWedding_weddingInAddressBook_returnsTrue() {
        Wedding wedding = new Wedding("Wedding 1");
        addressBook.addWedding(wedding);
        assertTrue(addressBook.hasWedding(wedding));
    }

    @Test
    public void findWeddingByName_weddingExists_returnsWedding() {
        Wedding wedding = new Wedding("Wedding 1");
        addressBook.addWedding(wedding);
        assertEquals(wedding, addressBook.findWeddingByName("Wedding 1"));
    }

    @Test
    public void findWeddingByName_weddingDoesNotExist_throwsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            addressBook.findWeddingByName("Nonexistent Wedding"));
    }


    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Wedding> getWeddingList() {
            return null;
        }
    }

}
