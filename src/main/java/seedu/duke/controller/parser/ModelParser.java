package seedu.duke.controller.parser;

import seedu.duke.common.LogManager;
import seedu.duke.exception.InvalidModelException;
import seedu.duke.model.ModelType;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelParser {
    public static final String INPUT_SCHEDULE_CLASS = "class";
    public static final String INPUT_SCHEDULE_TEST = "test";
    public static final String INPUT_SCHEDULE_CCA = "cca";
    public static final String INPUT_SCHEDULE_TUITION = "tuition";
    public static final String INPUT_QUIZ = "quiz";
    public static final String INPUT_CONTACT = "contact";
    public static final String INPUT_EVENT = "event";
    public static final int MAIN_COMMAND_INDEX = 0;
    public static final int SUB_COMMAND_INDEX = 1;

    private final String[] separatedInputs;

    private ModelType modelType;
    private static final Logger logger = LogManager.getLoggerInstance().getLogger();

    public ModelParser(String userInput) {
        separatedInputs = userInput.split(" ");
        modelType = null;
    }

    public ModelType extractModel() throws InvalidModelException {
        logger.log(Level.INFO, "Extracting model now...");

        if (separatedInputs.length <= 1) {
            return null;
        }

        switch (separatedInputs[SUB_COMMAND_INDEX]) {
        case INPUT_SCHEDULE_CLASS:
            modelType = ModelType.CLASS;
            break;
        case INPUT_SCHEDULE_CCA:
            modelType = ModelType.CCA;
            break;
        case INPUT_SCHEDULE_TEST:
            modelType = ModelType.TEST;
            break;
        case INPUT_SCHEDULE_TUITION:
            modelType = ModelType.TUITION;
            break;
        case INPUT_CONTACT:
            modelType = ModelType.CONTACT;
            break;
        case INPUT_QUIZ:
            modelType = ModelType.QUIZ;
            break;
        case INPUT_EVENT:
            modelType = ModelType.EVENT;
            break;
        default:
            throw new InvalidModelException();
        }

        return modelType;
    }
}
