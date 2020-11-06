package seedu.duke.controller.command;

import org.junit.jupiter.api.Test;
import seedu.duke.controller.parser.CommandType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

//@@author AndreWongZH
class CommandFactoryTest {
    @Test
    void generateActionableCommand_addType_addCommandClass() {
        Command actualCommand = new CommandFactory(CommandType.ADD, "").generateActionableCommand();
        assertEquals(AddCommand.class, actualCommand.getClass());
    }

    @Test
    void generateActionableCommand_nullType_addCommandClass() {
        Command actualCommand = new CommandFactory(CommandType.BYE, "").generateActionableCommand();
        assertNull(actualCommand);
    }
}