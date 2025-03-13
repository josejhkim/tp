package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.table.Table;
import seedu.address.model.table.TableList;
import seedu.address.storage.JsonTableStorage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AddTableCommand extends Command {
    public static final String COMMAND_WORD = "add_table";
    public static final String MESSAGE_SUCCESS = "New table added: %s";

    private final Table table;
    private final Path filePath = Paths.get("data", "tables.json");

    public AddTableCommand(Table table) {
        this.table = table;
    }

    @Override
    public CommandResult execute(Model model) {
        TableList tableList = new TableList();
        tableList.addTable(table);
        try {
            JsonTableStorage tableStorage = new JsonTableStorage(filePath);
            tableStorage.saveTableList(tableList);
        } catch (Exception e) {
            return new CommandResult("Error saving table: " + e.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, table));
    }
}
