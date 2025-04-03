package seedu.address.model.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class NoCurrentWeddingExceptionTest {

    @Test
    public void constructor_defaultMessage_correctMessageSet() {
        NoCurrentWeddingException exception = new NoCurrentWeddingException();
        assertEquals("There is no current wedding.", exception.getMessage());
    }

    @Test
    public void throws_andCatchesCorrectly() {
        Exception exception = assertThrows(NoCurrentWeddingException.class, () -> {
            // Simulate method that throws it
            throw new NoCurrentWeddingException();
        });

        assertEquals("There is no current wedding.", exception.getMessage());
    }
}
