package seedu.duke.controller.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidCommandException;
import seedu.duke.exception.MissingModelException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author AndreWongZH
class CommandParserTest {
    @Test
    void extractCommand_deleteString_deleteCommandType() throws InvalidCommandException, MissingModelException {
        CommandType actualType = new CommandParser("delete 22").extractCommand();
        assertEquals(CommandType.DELETE, actualType);
    }

    @Test
    void extractCommand_singleDoneString_expectException() {
        CommandParser commandParser = new CommandParser("done");
        assertThrows(MissingModelException.class, commandParser::extractCommand);
    }

    @Test
    void extractCommand_randomString_expectException() {
        CommandParser commandParser = new CommandParser("random string");
        assertThrows(InvalidCommandException.class, commandParser::extractCommand);
    }
}