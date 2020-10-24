package seedu.duke.controller.parser;

import seedu.duke.exception.InvalidModelException;
import seedu.duke.model.Model;
import seedu.duke.model.ModelMain;

//@@author AndreWongZH
/**
 * Represents a extractor that returns the corresponding Model Manager
 * based on the modelType.
 */
public class ModelExtractor {
    private final Model model;
    private final ModelType modelType;

    public ModelExtractor(Model model, ModelType modelType) {
        this.model = model;
        this.modelType = modelType;
    }

    /**
     * Returns the Model Manager based on the modelType.
     * Returns null if modelType is EVENT or null.
     *
     * @return Model Manager to be read or modified.
     * @throws InvalidModelException If modelType does not match any of the Model Managers.
     */
    public ModelMain retrieveModel() throws InvalidModelException {
        if (modelType == null) {
            return null;
        }

        switch (modelType) {
        case CLASS:
            return model.getEventManager().getClassManager();
        case CCA:
            return model.getEventManager().getCcaManager();
        case TEST:
            return model.getEventManager().getTestManager();
        case TUITION:
            return model.getEventManager().getTuitionManager();
        case QUIZ:
            return model.getQuizManager();
        case CONTACT:
            return model.getContactManager();
        case EVENT:
            return model.getEventManager();
        default:
            throw new InvalidModelException();
        }
    }
}
