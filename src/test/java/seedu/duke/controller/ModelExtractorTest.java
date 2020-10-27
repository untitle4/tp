package seedu.duke.controller;

import org.junit.jupiter.api.Test;
import seedu.duke.controller.parser.ModelExtractor;
import seedu.duke.exception.InvalidModelException;
import seedu.duke.model.Model;
import seedu.duke.controller.parser.ModelType;
import seedu.duke.model.ModelMain;
import seedu.duke.model.contact.ContactManager;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.event.EventParameter;
import seedu.duke.model.event.classlesson.EventClassManager;
import seedu.duke.model.quiz.QuizManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

//@@author AndreWongZH
class ModelExtractorTest {
    @Test
    void retrieveModel_typeClass_eventClassManager() throws InvalidModelException {
        Model model = initializeModel();
        ModelExtractor modelExtractor = new ModelExtractor(model, ModelType.CLASS);
        ModelMain actualModel = modelExtractor.retrieveModel();
        assertEquals(EventClassManager.class, actualModel.getClass());
    }

    @Test
    void retrieveModel_typeNull_null() throws InvalidModelException {
        Model model = initializeModel();
        ModelExtractor modelExtractor = new ModelExtractor(model, null);
        ModelMain actualModel = modelExtractor.retrieveModel();
        assertNull(actualModel);
    }

    private Model initializeModel() {
        return new Model(new EventManager(new EventParameter()), new ContactManager(),
                new QuizManager(new ArrayList<>()));
    }
}