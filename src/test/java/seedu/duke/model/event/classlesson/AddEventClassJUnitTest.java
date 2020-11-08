package seedu.duke.model.event.classlesson;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.SwappedParameterException;
import seedu.duke.storage.TestUtils;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.event.Event;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddEventClassJUnitTest {
    TestUtils testUtils = new TestUtils();

    @Test
    void addClassToList() throws EmptyParameterException, MissingParameterException, SwappedParameterException {
        ArrayList<Event> classes = new ArrayList<>();
        EventClassManager eventClassManager = new EventClassManager(classes,
                testUtils.getEmptyEventManager());
        eventClassManager.add("add class /n Math /s 2020-08-19 1300 /e 2020-08-19 1400");
        int actualOutputs = eventClassManager.getClassListSize();
        int expectedOutputs = 1;
        assertEquals(actualOutputs, expectedOutputs);
    }
}

