package seedu.duke.controller.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidModelException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author AndreWongZH
class ModelParserTest {
    @Test
    void extractModel_singleString_expectNull() throws InvalidModelException {
        ModelType actualType = new ModelParser("single").extractModel();
        assertNull(actualType);
    }

    @Test
    void extractModel_getTuitionString_expectException() throws InvalidModelException {
        ModelType actualType = new ModelParser("add tuition").extractModel();
        assertEquals(ModelType.TUITION, actualType);
    }

    @Test
    void extractModel_invalidString_expectException() {
        ModelParser modelParser = new ModelParser("random string");
        assertThrows(InvalidModelException.class, modelParser::extractModel);
    }
}