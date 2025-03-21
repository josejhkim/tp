package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.category.DietaryRestriction;
import seedu.address.model.person.category.Rsvp;
import seedu.address.model.table.Table;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;

    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String dietaryRestriction;
    private final String rsvp;
    private final String table;
    /**
     * Constructs a {@code JsonAdaptedGuest} with the given guest details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email,
                             @JsonProperty("address") String address,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags,
                             @JsonProperty("dietaryRestriction") String dietaryRestriction,
                             @JsonProperty("rsvp") String rsvp,
                             @JsonProperty("table") String table) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.dietaryRestriction = dietaryRestriction;
        this.rsvp = rsvp;
        this.table = table;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        this.name = source.getName().fullName;
        this.phone = source.getPhone().value;
        this.email = source.getEmail().value;
        this.address = source.getAddress().value;
        this.dietaryRestriction = source.getDietaryRestriction().toString();
        this.tags.addAll(source.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList()));
        this.rsvp = source.getRsvp().toString();
        this.table = source.getTableIdString();
    }

    /**
     * Converts this Jackson-friendly adapted guest object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted guest.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (dietaryRestriction == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                DietaryRestriction.class.getSimpleName()));
        }
        final DietaryRestriction modelDietaryRestriction;
        try {
            modelDietaryRestriction = ParserUtil.parseDietaryRestriction(dietaryRestriction);
        } catch (ParseException e) {
            throw new IllegalValueException(e.getMessage());
        }

        if (rsvp == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Rsvp.class.getSimpleName()));
        }

        final Rsvp.Status statusEnum;
        try {
            statusEnum = Rsvp.Status.valueOf(rsvp.toUpperCase()); // Converts String to Enum
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException("Invalid RSVP status: " + rsvp);
        }
        final Rsvp modelRsvp = new Rsvp(statusEnum);

        Table modelTable = null;
        if (!table.equals("Unassigned")) {
            modelTable = new Table(Integer.parseInt(table), Table.MAX_CAPACITY);
        }

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags,
            modelDietaryRestriction, modelRsvp, modelTable);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JsonAdaptedPerson)) {
            return false;
        }
        JsonAdaptedPerson otherGuest = (JsonAdaptedPerson) other;
        return Objects.equals(name, otherGuest.name)
            && Objects.equals(phone, otherGuest.phone)
            && Objects.equals(email, otherGuest.email)
            && Objects.equals(address, otherGuest.address)
            && Objects.equals(dietaryRestriction, otherGuest.dietaryRestriction)
            && Objects.equals(rsvp, otherGuest.rsvp)
            && Objects.equals(table, otherGuest.table);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, address,
            dietaryRestriction, rsvp, table);
    }
}
