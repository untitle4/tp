package seedu.duke.controller;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidModelException;
import seedu.duke.model.DataManager;
import seedu.duke.model.Model;
import seedu.duke.model.ModelType;
import seedu.duke.model.contact.ContactManager;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.event.EventParameter;
import seedu.duke.model.event.classlesson.EventClassManager;
import seedu.duke.model.quiz.QuizManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ModelExtractorTest {
    @Test
    void retrieveModel_typeClass_eventClassManager() throws InvalidModelException {
        Model model = initializeModel();
        ModelExtractor modelExtractor = new ModelExtractor(model, ModelType.CLASS);
        DataManager actualModel = modelExtractor.retrieveModel();
        assertEquals(EventClassManager.class, actualModel.getClass());
    }

    @Test
    void retrieveModel_typeNull_null() throws InvalidModelException {
        Model model = initializeModel();
        ModelExtractor modelExtractor = new ModelExtractor(model, null);
        DataManager actualModel = modelExtractor.retrieveModel();
        assertNull(actualModel);
    }

    private Model initializeModel() {
        return new Model(new EventManager(new EventParameter()), new ContactManager(),
                new QuizManager(new ArrayList<>()));
    }
}