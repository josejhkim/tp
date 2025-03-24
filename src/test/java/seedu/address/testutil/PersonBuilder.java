package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.DietaryRestriction;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rsvp;
import seedu.address.model.table.Table;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final int DEFAULT_TABLEID = 1;
    public static final int DEFAULT_CAPACITY = 1;
    public static final DietaryRestriction DEFAULT_DIETARY_RESTRICTIONS =
        new DietaryRestriction(DietaryRestriction.TypicalRestriction.NONE);

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private DietaryRestriction dietaryRestriction;
    private Rsvp rsvp;
    private Optional<Table> table;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        table = Optional.empty();
        rsvp = new Rsvp(Rsvp.Status.YES);
        dietaryRestriction = DEFAULT_DIETARY_RESTRICTIONS;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        table = personToCopy.getTable();
        dietaryRestriction = personToCopy.getDietaryRestriction();
        rsvp = personToCopy.getRsvp();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }
    /**
     * Sets the {@code Table} of the {@code Person} that we are building.
     */
    public PersonBuilder withTable(int tableId, int capacity) {
        this.table = Optional.of(new Table(tableId, capacity));
        return this;
    }

    /**
     * Sets the {@code DietaryRestriction} of the {@code Person} that we are building.
     */
    public PersonBuilder withDietaryRestriction(DietaryRestriction.TypicalRestriction dietaryRestriction) {
        this.dietaryRestriction = new DietaryRestriction(dietaryRestriction);
        return this;
    }
    /**
     * Sets the {@code Rsvp} of the {@code Person} that we are building.
     */
    public PersonBuilder withRsvp(Rsvp.Status status) {
        this.rsvp = new Rsvp(status);
        return this;
    }
    /**
     * Builds the person object.
     */
    public Person build() {
        return new Person(name, phone, email, address, tags, dietaryRestriction, rsvp,
            table);
    }
}
