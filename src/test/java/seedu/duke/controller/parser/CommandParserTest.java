package seedu.duke.controller.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidCommandException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandParserTest {
    @Test
    void extractCommand_deleteString_deleteCommandType() throws InvalidCommandException {
        CommandType actualType = new CommandParser("delete 22").extractCommand();
        assertEquals(CommandType.DELETE, actualType);
    }

    @Test
    void extractCommand_singleDoneString_expectException() {
        CommandParser commandParser = new CommandParser("done");
        assertThrows(InvalidCommandException.class, commandParser::extractCommand);
    }

    @Test
    void extractCommand_randomString_expectException() {
        CommandParser commandParser = new CommandParser("random string");
        assertThrows(InvalidCommandException.class, commandParser::extractCommand);
    }
}