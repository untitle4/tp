package seedu.duke.controller.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.IncompleteListCommandException;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author AndreWongZH
class ListCommandTest {
    @Test
    void execute_nullModel_expectException() {
        ListCommand listCommand = new ListCommand("");
        assertThrows(IncompleteListCommandException.class, () -> listCommand.execute(null));
    }
}