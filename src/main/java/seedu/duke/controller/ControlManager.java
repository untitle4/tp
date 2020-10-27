package seedu.duke.controller;

import seedu.duke.Duke;
import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;

import seedu.duke.controller.command.Command;
import seedu.duke.controller.command.CommandFactory;
import seedu.duke.controller.parser.ModelExtractor;
import seedu.duke.controller.parser.ModelParser;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.IncompleteListCommandException;
import seedu.duke.exception.InvalidCommandException;
import seedu.duke.exception.InvalidHelpCommandException;
import seedu.duke.exception.InvalidModelException;
import seedu.duke.exception.MissingModelException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.Model;
import seedu.duke.controller.parser.CommandParser;
import seedu.duke.controller.parser.CommandType;
import seedu.duke.controller.parser.ModelType;
import seedu.duke.model.ModelMain;
import seedu.duke.model.event.Event;
import seedu.duke.model.quiz.Quiz;
import seedu.duke.storage.EventStorageManager;
import seedu.duke.storage.QuizStorageManager;
import seedu.duke.ui.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author AndreWongZH
/**
 * Manages the parsing of commands and models and the execution of commands.
 */
public class ControlManager {
    private final String userInput;
    private final Model model;
    private final UserInterface userInterface;
    private final EventStorageManager eventStorageManager;
    private final QuizStorageManager quizStorageManager;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();

    public ControlManager(String userInput, Model model,
                          EventStorageManager eventStorageManager, QuizStorageManager quizStorageManager) {
        assert userInput != null : "ControlManager must not accept null userInput";
        this.userInput = userInput;
        this.model = model;
        userInterface = UserInterface.getInstance();
        this.eventStorageManager = eventStorageManager;
        this.quizStorageManager = quizStorageManager;
    }

    /**
     * Executes generated command class with a model.
     *
     * @return CommandType back to user interface to determine if program exits.
     */
    public CommandType runLogic() {
        CommandType commandType = null;
        ModelType modelType = null;
        ModelMain dataModel = null;

        try {
            logger.log(Level.INFO, "Running controller logic now");
            logger.log(Level.INFO, "Extracting command");
            commandType = new CommandParser(userInput).extractCommand();
            final Command actionableCommand = new CommandFactory(commandType, userInput).generateActionableCommand();

            if (commandType == CommandType.BYE) {
                logger.log(Level.INFO, "User terminates program");
                return commandType;
            }

            // Only extract model for certain commands(add, delete, list, find, quiz, done).
            if (doesRequireModel(commandType)) {
                logger.log(Level.INFO, "Extracting model");
                modelType = new ModelParser(userInput).extractModel();
                dataModel = new ModelExtractor(model, modelType).retrieveModel();
            }
            checkInvalidModels(commandType, modelType);

            logger.log(Level.INFO, "Executing command");
            actionableCommand.execute(dataModel);
        } catch (InvalidHelpCommandException e) {
            userInterface.showToUser(Messages.MESSAGE_EXTRA_HELP_PARAM);
        } catch (InvalidCommandException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_COMMAND);
        } catch (InvalidModelException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_MODEL);
        } catch (MissingParameterException e) {
            userInterface.showToUser(String.format(Messages.MESSAGE_MISSING_PARAMETERS, e.getMessage()));
        } catch (EmptyParameterException e) {
            userInterface.showToUser(Messages.MESSAGE_EMPTY_PARAMETERS);
        } catch (IncompleteListCommandException e) {
            userInterface.showToUser(Messages.MESSAGE_INCOMPLETE_LIST_PARAMETERS);
        } catch (MissingModelException e) {
            userInterface.showToUser(Messages.MESSAGE_MISSING_MODEL);
        } finally {
            refreshEvents();
            refreshQuizzes();
        }

        return commandType;
    }

    /**
     * Checks if user entered an invalid model after the command.
     * Commands such as add, delete and done cannot be paired with a model type event.
     *
     * @param commandType Command Type to be checked.
     * @param modelType Model Type to be compared against.
     * @throws InvalidModelException If add, delete or done is followed after event model.
     */
    private void checkInvalidModels(CommandType commandType, ModelType modelType) throws InvalidModelException {
        if ((commandType == CommandType.ADD || commandType == CommandType.DELETE || commandType == CommandType.DONE)
                && modelType == ModelType.EVENT) {
            throw new InvalidModelException();
        }
    }

    /**
     * Checks if a modelType is required to be extracted out.
     * Help and Bye command need not require any model.
     *
     * @param commandType The command type to be checked.
     * @return Boolean to inform a need for model extraction.
     */
    private boolean doesRequireModel(CommandType commandType) {
        boolean isAdd = commandType == CommandType.ADD;
        boolean isDelete = commandType == CommandType.DELETE;
        boolean isDone = commandType == CommandType.DONE;
        boolean isList = commandType == CommandType.LIST;
        boolean isFind = commandType == CommandType.FIND;
        boolean isQuiz = commandType == CommandType.QUIZ;

        return isAdd || isDelete || isDone || isList || isFind || isQuiz;
    }

    //@@author
    private void refreshEvents() {
        ArrayList<Event> events = new ArrayList<>();

        events.addAll(model.getEventManager().getCcaManager().getCcas());
        events.addAll(model.getEventManager().getTestManager().getTests());
        events.addAll(model.getEventManager().getClassManager().getClasses());
        events.addAll(model.getEventManager().getTuitionManager().getTuitions());

        try {
            eventStorageManager.saveData(events);
        } catch (IOException e) {
            userInterface.showToUser(Messages.MESSAGE_STORAGE_INITIALIZATION_ERROR);
        }
    }

    private void refreshQuizzes() {
        ArrayList<Quiz> quizzes = model.getQuizManager().getQuizList();

        try {
            quizStorageManager.saveData(quizzes, Duke.QUIZ_FILE_NAME);
        } catch (IOException e) {
            userInterface.showToUser(Messages.MESSAGE_STORAGE_INITIALIZATION_ERROR);
        }
    }
}
