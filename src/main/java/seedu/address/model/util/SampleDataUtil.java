package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.DietaryRestriction;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rsvp;
import seedu.address.model.table.Table;
import seedu.address.model.tag.Tag;
import seedu.address.model.wedding.Wedding;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("friends"),
                    new DietaryRestriction(DietaryRestriction.TypicalRestriction.VEGETARIAN),
                    new Rsvp(Rsvp.Status.NO_RESPONSE), -1),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getTagSet("colleagues", "friends"),
                    new DietaryRestriction(DietaryRestriction.TypicalRestriction.VEGETARIAN),
                    new Rsvp(Rsvp.Status.YES), -1),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getTagSet("neighbours"),
                    new DietaryRestriction(DietaryRestriction.TypicalRestriction.HALAL), new Rsvp(Rsvp.Status.NO),
                -1),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("family"),
                    new DietaryRestriction(DietaryRestriction.TypicalRestriction.PEANUTS),
                    new Rsvp(Rsvp.Status.YES), -1),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), getTagSet("classmates"),
                    new DietaryRestriction(DietaryRestriction.TypicalRestriction.VEGAN),
                    new Rsvp(Rsvp.Status.NO_RESPONSE), -1),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), getTagSet("colleagues"),
                    new DietaryRestriction(DietaryRestriction.TypicalRestriction.SHELLFISH),
                    new Rsvp(Rsvp.Status.NO), -1) };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        Wedding sampleWedding = new Wedding("John and Jane");
        sampleWedding.addTable(new Table(1, 20));
        sampleWedding.addTable(new Table(2, 20));

        for (Person samplePerson : getSamplePersons()) {
            sampleWedding.addPerson(samplePerson);
            sampleWedding.addPersonToTableById(samplePerson, 1);
        }

        sampleAb.addWedding(sampleWedding);
        sampleAb.setCurrentWeddingByName("John and Jane");
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }

}
