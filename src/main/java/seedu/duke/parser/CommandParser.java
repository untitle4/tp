package seedu.duke.parser;

import seedu.duke.LogManager;
import seedu.duke.Messages;
import seedu.duke.UserInterface;
import seedu.duke.contact.ContactManager;
import seedu.duke.event.EventManager;
import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;
import seedu.duke.exception.ContactEmptyStringException;
import seedu.duke.exception.ContactParamException;
import seedu.duke.exception.EmptyTuitionInputException;
import seedu.duke.exception.InvalidClassInputException;
import seedu.duke.exception.InvalidCommandException;
import seedu.duke.exception.InvalidHelpCommandException;
import seedu.duke.exception.InvalidTuitionInputException;
import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;
import seedu.duke.quiz.QuizManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandParser {
    public static final String INPUT_ADD = "add";
    public static final String INPUT_DELETE = "delete";
    public static final String INPUT_DONE = "done";
    public static final String INPUT_LIST = "list";
    public static final String INPUT_BYE = "bye";
    public static final String INPUT_SCHEDULE_CLASS = "class";
    public static final String INPUT_SCHEDULE_TEST = "test";
    public static final String INPUT_SCHEDULE_CCA = "cca";
    public static final String INPUT_SCHEDULE_TUITION = "tuition";
    public static final String INPUT_HELP = "help";
    public static final String INPUT_QUIZ = "quiz";
    public static final String INPUT_CONTACT = "contact";
    public static final int MAIN_COMMAND_INDEX = 0;
    public static final int SUB_COMMAND_INDEX = 1;

    private final String[] separatedInputs;
    private final String userInput;
    private final EventManager eventManager;
    private final QuizManager quizManager;
    private final ContactManager contactManager;

    private CommandType commandType;

    private UserInterface userInterface;

    private static final Logger logger = LogManager.getLogger();

    public CommandParser(String userInput, EventManager eventManager,
                         QuizManager quizManager, ContactManager contactManager) {
        this.userInput = userInput;
        this.eventManager = eventManager;
        this.quizManager = quizManager;
        this.contactManager = contactManager;
        separatedInputs = userInput.split(" ");
        commandType = null;
        userInterface = UserInterface.getInstance();
    }

    public CommandType parseCommand() {
        try {
            extractCommand();
            executeCommand();
        } catch (InvalidHelpCommandException e) {
            System.out.println("☹ Oops! If you're trying to ask for help, simply enter 'help'!\n");
        } catch (InvalidCommandException e) {
            System.out.println("☹ Oops! I did not recognize that command! Enter 'help' if needed!");
        }
        return commandType;
    }

    private void extractCommand() throws InvalidCommandException {
        if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_HELP)) {
            commandType = CommandType.HELP;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_ADD)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_CLASS)) {
            commandType = CommandType.ADD_CLASS;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_ADD)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_TEST)) {
            commandType = CommandType.ADD_TEST;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_ADD)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_CCA)) {
            commandType = CommandType.ADD_CCA;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_ADD)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_CONTACT)) {
            commandType = CommandType.ADD_CONTACT;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_ADD)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_TUITION)) {
            commandType = CommandType.ADD_TUITION;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DELETE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_CLASS)) {
            commandType = CommandType.DELETE_CLASS;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DELETE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_TEST)) {
            commandType = CommandType.DELETE_TEST;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DELETE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_CCA)) {
            commandType = CommandType.DELETE_CCA;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DELETE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_QUIZ)) {
            commandType = CommandType.DELETE_QUIZ;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DELETE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_CONTACT)) {
            commandType = CommandType.DELETE_CONTACT;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_LIST)
                && separatedInputs.length > 1
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_QUIZ)) {
            commandType = CommandType.LIST_QUIZ;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_LIST)
                && separatedInputs.length > 1
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_CONTACT)) {
            commandType = CommandType.LIST_CONTACT;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_LIST)) {
            commandType = CommandType.LIST;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_BYE)) {
            commandType = CommandType.BYE;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DONE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_TEST)) {
            commandType = CommandType.DONE_TEST;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DONE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_CLASS)) {
            commandType = CommandType.DONE_CLASS;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DONE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_CCA)) {
            commandType = CommandType.DONE_CCA;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_ADD)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_QUIZ)) {
            commandType = CommandType.ADD_QUIZ;
        } else {
            throw new InvalidCommandException();
        }
    }

    private void executeCommand() throws InvalidHelpCommandException {
        switch (commandType) {
        case HELP:
            handleHelp(separatedInputs);
            break;
        case ADD_CLASS:
            try {
                eventManager.getClassManager().addClass(userInput);
            } catch (InvalidClassInputException e) {
                userInterface.showToUser(Messages.MESSAGE_MISSING_PARAMETERS);
            }
            break;
        case ADD_CCA:
            try {
                eventManager.getCcaManager().addCca(userInput);
            } catch (CcaEmptyStringException | CcaParamException e) {
                userInterface.showToUser(Messages.MESSAGE_MISSING_PARAMETERS);
            }
            break;
        case ADD_TEST:
            try {
                eventManager.getTestManager().addTest(userInput);
            } catch (TestEmptyStringException | TestParamException e) {
                userInterface.showToUser(Messages.MESSAGE_MISSING_PARAMETERS);
            }
            break;
        case ADD_CONTACT:
            try {
                contactManager.addContact(userInput);
            } catch (ContactEmptyStringException | ContactParamException e) {
                userInterface.showToUser(Messages.MESSAGE_MISSING_PARAMETERS);
            }
            break;
        case ADD_TUITION:
            try {
                eventManager.getTuitionManager().addTuition(userInput);
            } catch (InvalidTuitionInputException e) {
                userInterface.showToUser(Messages.MESSAGE_MISSING_TUITION_SUFFIX);
            } catch (EmptyTuitionInputException e) {
                userInterface.showToUser(Messages.MESSAGE_MISSING_TUITION_INPUT);
            }
            break;
        case DELETE_CLASS:
            try {
                eventManager.getClassManager().deleteClass(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                userInterface.showToUser(Messages.MESSAGE_INVALID_CLASS_INDEX);
            }
            break;
        case DELETE_CCA:
            try {
                eventManager.getCcaManager().deleteCca(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                userInterface.showToUser(Messages.MESSAGE_INVALID_CCA_INDEX);
            }
            break;
        case DELETE_TEST:
            try {
                eventManager.getTestManager().deleteTest(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                userInterface.showToUser(Messages.MESSAGE_INVALID_TEST_INDEX);
            }
            break;
        case DELETE_QUIZ:
            try {
                quizManager.deleteQuiz(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                userInterface.showToUser(Messages.MESSAGE_INVALID_QUIZ_INDEX);
            }
            break;
        case DELETE_TUITION:
            try {
                eventManager.getTuitionManager().deleteTuition(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                userInterface.showToUser(Messages.MESSAGE_INVALID_TUITION_INDEX);
            }
            break;
        case DELETE_CONTACT:
            try {
                contactManager.deleteContact(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                userInterface.showToUser(Messages.MESSAGE_INVALID_CONTACT_INDEX);
            }
            break;
        case DONE_CLASS:
            try {
                eventManager.getClassManager().setClassDone(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                userInterface.showToUser(Messages.MESSAGE_INVALID_CLASS_INDEX);
            }
            break;
        case DONE_TEST:
            try {
                eventManager.getTestManager().setTestDone(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                userInterface.showToUser(Messages.MESSAGE_INVALID_TEST_INDEX);
            }
            break;
        case DONE_CCA:
            try {
                eventManager.getCcaManager().setCcaDone(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                userInterface.showToUser(Messages.MESSAGE_INVALID_CCA_INDEX);
            }
            break;
        case DONE_TUITION:
            try {
                eventManager.getTuitionManager().setTuitionDone(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                userInterface.showToUser(Messages.MESSAGE_INVALID_TUITION_INDEX);
            }
            break;
        case ADD_QUIZ:
            quizManager.addQuiz(userInput);
            break;
        case LIST:
            eventManager.listSchedule();
            break;
        case LIST_QUIZ:
            quizManager.listQuiz();
            break;
        case LIST_CONTACT:
            contactManager.listContacts();
            break;
        case BYE:
            break;
        default:
            // do nothing
        }
    }

    /**
     * <h2>handleHelp()</h2>
     * Prints out all available features users can use.
     * @param userInputs to check if user input for 'help' is in a valid format.
     * @exception InvalidHelpCommandException to inform the user if their help input is invalid.
     */
    private void handleHelp(String[] userInputs) throws InvalidHelpCommandException {
        if (userInputs.length == 1) {
            logger.log(Level.INFO, "printing out all features users can use");
            userInterface.showToUser(Messages.MESSAGE_HELP);
        } else {
            logger.log(Level.WARNING, "invalid help command");
            throw new InvalidHelpCommandException();
        }
    }
}
