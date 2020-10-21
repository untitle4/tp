package seedu.duke.controller;

import seedu.duke.exception.InvalidModelException;
import seedu.duke.model.DataManager;
import seedu.duke.model.Model;
import seedu.duke.model.ModelType;

public class ModelExtractor {
    private final Model model;
    private final ModelType modelType;

    public ModelExtractor(Model model, ModelType modelType) {
        this.model = model;
        this.modelType = modelType;
    }

    public DataManager retrieveModel() throws InvalidModelException {
        if (modelType == null || modelType == ModelType.EVENT) {
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
        //case EVENT:
            //return model.getEventManager();
        default:
            throw new InvalidModelException();
        }
    }
}
