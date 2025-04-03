package seedu.address.model.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PersonAlreadySeatedExceptionTest {

    @Test
    public void constructor_setsCorrectMessage() {
        PersonAlreadySeatedException exception = new PersonAlreadySeatedException();
        assertEquals("The given person is already seated at a table!", exception.getMessage());
    }

    @Test
    public void throws_andCatchesCorrectly() {
        Exception exception = assertThrows(PersonAlreadySeatedException.class, () -> {
            // Simulate condition that throws it
            throw new PersonAlreadySeatedException();
        });

        assertEquals("The given person is already seated at a table!", exception.getMessage());
    }
}
