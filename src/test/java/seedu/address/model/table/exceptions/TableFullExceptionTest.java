package seedu.address.model.table.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TableFullExceptionTest {

    @Test
    public void constructor_setsCorrectMessage() {
        TableFullException exception = new TableFullException();
        assertEquals("The table you're trying to add a person to is already full!", exception.getMessage());
    }

    @Test
    public void throws_andCatchesCorrectly() {
        Exception exception = assertThrows(TableFullException.class, () -> {
            throw new TableFullException();
        });

        assertEquals("The table you're trying to add a person to is already full!", exception.getMessage());
    }
}
