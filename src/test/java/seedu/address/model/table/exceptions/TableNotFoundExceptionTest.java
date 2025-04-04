package seedu.address.model.table.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TableNotFoundExceptionTest {

    @Test
    public void constructor_defaultMessage_correctMessage() {
        TableNotFoundException exception = new TableNotFoundException();
        assertEquals("Requested table does not exist in the list.", exception.getMessage());
    }

    @Test
    public void throws_tableNotFoundException_successfullyThrows() {
        assertThrows(TableNotFoundException.class, () -> {
            throw new TableNotFoundException();
        });
    }
}
