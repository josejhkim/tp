package seedu.address.storage;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.category.DietaryRestriction;
import seedu.address.model.person.category.Rsvp;

/**
 * Jackson-friendly version of {@link Guest}.
 */
class JsonAdaptedGuest {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Guest's %s field is missing!";

    private final int guestId;
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String dietaryRestriction;
    private final String rsvp;

    /**
     * Constructs a {@code JsonAdaptedGuest} with the given guest details.
     */
    @JsonCreator
    public JsonAdaptedGuest(@JsonProperty("guestId") int guestId,
                            @JsonProperty("name") String name,
                            @JsonProperty("phone") String phone,
                            @JsonProperty("email") String email,
                            @JsonProperty("address") String address,
                            @JsonProperty("dietaryRestriction") String dietaryRestriction,
                            @JsonProperty("rsvp") String rsvp) {
        this.guestId = guestId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dietaryRestriction = dietaryRestriction;
        this.rsvp = rsvp;
    }

    /**
     * Converts a given {@code Guest} into this class for Jackson use.
     */
    public JsonAdaptedGuest(Guest source) {
        this.guestId = source.getGuestId();
        this.name = source.getName().fullName;
        this.phone = source.getPhone().value;
        this.email = source.getEmail().value;
        this.address = source.getAddress().value;
        this.dietaryRestriction = source.getDietaryRestriction().toString();
        this.rsvp = source.getRsvp().toString();
    }

    /**
     * Converts this Jackson-friendly adapted guest object into the model's {@code Guest} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted guest.
     */
    public Guest toModelType() throws IllegalValueException {
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

        return new Guest(guestId, modelName, modelPhone, modelEmail, modelAddress,
                modelDietaryRestriction, modelRsvp);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JsonAdaptedGuest)) {
            return false;
        }
        JsonAdaptedGuest otherGuest = (JsonAdaptedGuest) other;
        return guestId == otherGuest.guestId
                && Objects.equals(name, otherGuest.name)
                && Objects.equals(phone, otherGuest.phone)
                && Objects.equals(email, otherGuest.email)
                && Objects.equals(address, otherGuest.address)
                && Objects.equals(dietaryRestriction, otherGuest.dietaryRestriction)
                && Objects.equals(rsvp, otherGuest.rsvp);
    }


    @Override
    public int hashCode() {
        return Objects.hash(guestId, name, phone, email, address,
                dietaryRestriction, rsvp);
    }
}
