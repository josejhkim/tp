package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.table.Table;
import seedu.address.model.table.TableList;

public class DeleteTableCommandTest {

    // private Model model;
    // private TableList tableList;
    // private Table table1;
    //
    // @BeforeEach
    // public void setUp() {
    //     tableList = new TableList();
    //     table1 = new Table(1, 10);
    //     tableList.addTable(table1);
    //     model = new ModelStubWithTable(tableList);
    // }
    //
    // @Test
    // public void execute_tableExists_success() throws CommandException {
    //     DeleteTableCommand command = new DeleteTableCommand(1);
    //     CommandResult result = command.execute(model);
    //     assertEquals("Table deleted: Table ID: 1, Capacity: 10", result.getFeedbackToUser());
    // }
    //
    // @Test
    // public void execute_tableDoesNotExist_throwsCommandException() {
    //     DeleteTableCommand command = new DeleteTableCommand(99);
    //     assertThrows(CommandException.class, () -> command.execute(model));
    // }
}
