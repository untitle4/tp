package seedu.duke.parser;

import seedu.duke.event.EventManager;

import seedu.duke.Duke.InvalidHelpCommandException;
import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;
import seedu.duke.exception.InvalidCommandException;
import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;

public class CommandParser {
    public static final String INPUT_ADD = "add";
    public static final String INPUT_DELETE = "delete";
    public static final String INPUT_LIST = "list";
    public static final String INPUT_BYE = "bye";
    public static final String INPUT_SCHEDULE_CLASS = "class";
    public static final String INPUT_SCHEDULE_TEST = "test";
    public static final String INPUT_SCHEDULE_CCA = "cca";
    public static final int MAIN_COMMAND_INDEX = 0;
    public static final int SUB_COMMAND_INDEX = 1;

    private final String[] separatedInputs;
    private final String userInput;
    private final EventManager eventManager;

    private CommandType commandType;

    public CommandParser(String userInput, EventManager eventManager) {
        this.userInput = userInput;
        this.eventManager = eventManager;
        separatedInputs = userInput.split(" ");
        commandType = null;
    }

    public CommandType parseCommand() {
        try {
            extractCommand();
            executeCommand();
        } catch (InvalidHelpCommandException e) {
            System.out.println("Oops! If you're trying to ask for help, simply enter 'help'!\n");
        } catch (InvalidCommandException e) {
            System.out.println("Oops! I did not recognize that command! Enter 'help' if needed!");
        }
        return commandType;
    }

    private void extractCommand() throws InvalidCommandException {
        if (separatedInputs[0].equals("help")) {
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
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DELETE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_CLASS)) {
            commandType = CommandType.DELETE_CLASS;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DELETE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_TEST)) {
            commandType = CommandType.DELETE_TEST;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_DELETE)
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_SCHEDULE_CCA)) {
            commandType = CommandType.DELETE_CCA;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_LIST)) {
            commandType = CommandType.LIST;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_BYE)) {
            commandType = CommandType.BYE;
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
            eventManager.getClassManager().addClass(userInput);
            break;
        case ADD_CCA:
            try {
                eventManager.getCcaManager().addCca(userInput);
            } catch (CcaEmptyStringException | CcaParamException e) {
                System.out.println("OOPS!!! The description of a cca cannot be empty.");
            }
            break;
        case ADD_TEST:
            try {
                eventManager.getTestManager().addTest(userInput);
            } catch (TestEmptyStringException | TestParamException e) {
                e.printStackTrace();
            }
            break;
        case DELETE_CLASS:
            eventManager.getClassManager().deleteClass(separatedInputs);
            break;
        case DELETE_CCA:
            eventManager.getCcaManager().deleteCca(separatedInputs);
            break;
        case DELETE_TEST:
            try {
                eventManager.getTestManager().deleteTest(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            break;
        case LIST:
            eventManager.listSchedule();
            break;
        case BYE:
            break;
        default:
            // do nothing
        }
    }

    private static void handleHelp(String[] userInputs) throws InvalidHelpCommandException {
        if (userInputs.length == 1) {
            System.out.println("Hello! Here is a list of commands you can try:\n\n"
                    + "\t1. Add class: add class /n [name of class] /s [start date-time of class] /e"
                    + " [end date-time of class]\n"
                    + "\t2. Delete class: delete class /n [class number]\n"
                    + "\t3. Add cca: add cca /n [name of cca] /s [start date-time of cca] /e [end date-time of cca]\n"
                    + "\t4. Delete cca: type delete cca /n [cca number]\n"
                    + "\t5. Add test: type add test /n [name of test] /s [start date-time of test] /e "
                    + "[end date-time of test]\n"
                    + "\t6. Delete test: type delete test /n [test number]\n"
                    + "\t7. Delete all: delete all\n");
            // "\n\tPlease enter the date-time in the following format: YYYY-MM-DD [time in 24hr format]\n" +
            // "\te.g. 2020-08-19 1300\n\n);
        } else {
            throw new InvalidHelpCommandException();
        }
    }
}
