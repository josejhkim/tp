package seedu.address.model.table.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DuplicateTableExceptionTest {

    @Test
    public void constructor_setsCorrectMessage() {
        DuplicateTableException exception = new DuplicateTableException();
        assertEquals("Operation would result in duplicate tables.", exception.getMessage());
    }

    @Test
    public void throws_andCatchesCorrectly() {
        Exception exception = assertThrows(DuplicateTableException.class, () -> {
            // Simulate the condition that would trigger this
            throw new DuplicateTableException();
        });

        assertEquals("Operation would result in duplicate tables.", exception.getMessage());
    }
}
