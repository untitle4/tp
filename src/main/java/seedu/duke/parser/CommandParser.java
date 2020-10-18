package seedu.duke.parser;

import seedu.duke.common.LogManager;
import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;
import seedu.duke.exception.ContactEmptyStringException;
import seedu.duke.exception.ContactParamException;
import seedu.duke.exception.EmptyTuitionInputException;
import seedu.duke.exception.InvalidClassInputException;
import seedu.duke.exception.InvalidCommandException;
import seedu.duke.exception.InvalidHelpCommandException;
import seedu.duke.exception.InvalidTuitionInputException;
import seedu.duke.exception.QuizParamException;
import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;
import seedu.duke.model.contact.ContactManager;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.quiz.QuizManager;

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
    public static final String INPUT_DATE = "date";
    public static final int MAIN_COMMAND_INDEX = 0;
    public static final int SUB_COMMAND_INDEX = 1;

    private final String[] separatedInputs;
    private final String userInput;
    private final EventManager eventManager;
    private final QuizManager quizManager;
    private final ContactManager contactManager;

    private CommandType commandType;

    private static final Logger logger = LogManager.getLoggerInstance().getLogger();

    public CommandParser(String userInput, EventManager eventManager,
                         QuizManager quizManager, ContactManager contactManager) {
        this.userInput = userInput;
        this.eventManager = eventManager;
        this.quizManager = quizManager;
        this.contactManager = contactManager;
        separatedInputs = userInput.split(" ");
        commandType = null;
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
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_LIST)
                && separatedInputs.length > 1
                && separatedInputs[SUB_COMMAND_INDEX].equals(INPUT_DATE)) {
            commandType = CommandType.LIST_DATE;
        } else if (separatedInputs[MAIN_COMMAND_INDEX].equals(INPUT_LIST)
                && separatedInputs.length == 1) {
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
                eventManager.getClassManager().add(userInput);
            } catch (InvalidClassInputException e) {
                System.out.println("☹ OOPS! Remember to include ALL '/n', '/s' and '/e' inputs!");
            }
            break;
        case ADD_CCA:
            try {
                eventManager.getCcaManager().add(userInput);
            } catch (CcaEmptyStringException | CcaParamException e) {
                System.out.println("☹ OOPS!!! Remember to include ALL '/n', '/s', '/e' inputs!");
            }
            break;
        case ADD_TEST:
            try {
                eventManager.getTestManager().add(userInput);
            } catch (TestEmptyStringException | TestParamException e) {
                System.out.println("☹ OOPS!!! Remember to include ALL '/n', '/s', '/e' inputs!");
            }
            break;
        case ADD_CONTACT:
            try {
                contactManager.add(userInput);
            } catch (ContactEmptyStringException | ContactParamException e) {
                System.out.println("☹ OOPS!!! Remember to include ALL '/s', '/n', '/p', '/e' inputs!");
            }
            break;
        case ADD_TUITION:
            try {
                eventManager.getTuitionManager().add(userInput);
            } catch (InvalidTuitionInputException e) {
                System.out.println("☹ OOPS!!! Remember to include ALL '/n', '/s', '/e', '/l' suffixes!");
            } catch (EmptyTuitionInputException e) {
                System.out.println("☹ OOPS!!! Remember to include ALL '/n', '/s', '/e', '/l' inputs!");
            }
            break;
        case DELETE_CLASS:
            try {
                eventManager.getClassManager().delete(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! Please indicate a valid class index!");
            }
            break;
        case DELETE_CCA:
            try {
                eventManager.getCcaManager().delete(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! Please indicate a valid cca index!");
            }
            break;
        case DELETE_TEST:
            try {
                eventManager.getTestManager().delete(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! Please indicate a valid test index!");
            }
            break;
        case DELETE_QUIZ:
            try {
                quizManager.delete(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! Please indicate a valid quiz index!");
            }
            break;
        case DELETE_CONTACT:
            try {
                contactManager.delete(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! Please indicate a valid contact index!");
            }
            break;
        case DONE_CLASS:
            try {
                eventManager.getClassManager().setDone(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! Please indicate a valid class index!");
            }
            break;
        case DONE_TEST:
            try {
                eventManager.getTestManager().setDone(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! Please indicate a valid test index!");
            }
            break;
        case DONE_CCA:
            try {
                eventManager.getCcaManager().setDone(separatedInputs);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! Please indicate a valid test index!");
            }
            break;
        case ADD_QUIZ:
            try {
                quizManager.add(userInput);
            } catch (QuizParamException e) {
                e.printStackTrace();
            }
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
        case LIST_DATE:
            eventManager.listDate(separatedInputs);
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
    private static void handleHelp(String[] userInputs) throws InvalidHelpCommandException {
        if (userInputs.length == 1) {
            logger.log(Level.INFO, "printing out all features users can use");
            System.out.println("Hello! Here is a list of commands you can try:\n\n"
                    + "\t1. Add class: 'add class /n [name of class] /s [start date-time of class] /e"
                    + " [end date-time of class]'\n"
                    + "\t2. Delete class: 'delete class [class number]'\n\n"
                    + "\t3. Add cca: 'add cca /n [name of cca] /s [start date-time of cca] /e [end date-time of cca]'\n"
                    + "\t4. Delete cca: 'delete cca [cca number]'\n\n"
                    + "\t5. Add test: 'add test /n [name of test] /s [start date-time of test] /e "
                    + "[end date-time of test]'\n"
                    + "\t6. Delete test: 'delete test [test number]'\n\n"
                    + "\t7. Add tuition: 'add tuition /n [name of tuition] /s [start date-time of tuition] /e "
                    + "start date-time of tuition] /l [location of tuition]'\n"
                    + "\t8. Delete tuition: 'delete tuition [tuition number]'\n\n"
                    + "\t9. List events (class, test, cca, tuition): 'list'\n\n"
                    + "\t10. Set class as done: 'done class [class number]'\n"
                    + "\t11. Set test as done: 'done test [test number]'\n"
                    + "\t12. Set cca as done: 'done cca [cca number]'\n"
                    + "\t13. Set tuition as done: 'done tuition [tuition number]'\n\n"
                    + "\t14. Find relevant event(s): 'find [keyword(s)]'\n\n"
                    + "\t15. Add contact: 'add contact /sub [subject] /n [name of contact person] /hp [phone number]"
                    + " /e [email address]'\n"
                    + "\t16. Delete contact: 'delete contact [contact number]'\n"
                    + "\t17. List contact: 'list contact'\n\n"
                    + "\t18. Take Mathematics quiz: 'quiz [no. of questions (10, 20 or 30)]'\n"
                    + "\t19. List quiz questions: 'list quiz'\n"
                    + "\t20. Add quiz question: 'add quiz /q [question] /o1 [option 1] /o2 [option 2] /o3 [option 3]"
                    + " /o4 [option 4] /a [option answer] /exp [explanation]'\n\n"
                    + "\t21. Exit program: 'bye'\n\n"
                    + "\n\tNOTE:\n\t1. Please enter the date-time in the following format: YYYY-MM-DD "
                    + "[time in 24hr format]\n\te.g. 2020-08-19 1300\n\n"
                    + "\t2. For command 20 (Add quiz question), the 'explanation' field is OPTIONAL\n\n");
        } else {
            logger.log(Level.WARNING, "invalid help command");
            throw new InvalidHelpCommandException();
        }
    }
}
