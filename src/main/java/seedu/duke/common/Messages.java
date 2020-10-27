package seedu.duke.common;

import seedu.duke.model.quiz.Quiz;
import seedu.duke.model.quiz.QuizManager;

public class Messages {
    //@@author durianpancakes
    // Intro related messages
    public static final String MESSAGE_LOGO = " ------   -                          -----\n"
            + "|   _  | | |                        | ____|\n"
            + "|  | | | | |                        | |___\n"
            + "|  |_| | | |  -----     -----    &  |____ |  ------   -----   -----   -----\n"
            + "|      | | | /  -  \\   |  _  |          | | | _____| /  -  \\ /  ___\\ /  -- \\\n"
            + "|  ----  | | | | | |   | | | |       ___| | | |      | | | | | /     |  ___|\n"
            + "| |      | | | |_|  \\  | | | |      |     | | |____  | |_| | | |     | |____\n"
            + "|_|      |_| \\____/\\_\\ |_| |_|      |_____| |______| \\_____/ |_|     \\_____/";
    //@@author
    public static final String MESSAGE_HELLO_FROM_DUKE = "Hello from\n"
            + Messages.MESSAGE_LOGO;
    public static final String MESSAGE_PROMPT_NAME = "What is your name?";
    public static final String MESSAGE_HELLO = "Hello ";
    public static final String MESSAGE_PROMPT_COMMAND = "What can we do for you? "
            + "(Enter 'help' for the list of available commands!)\n";
    public static final String MESSAGE_EMPTY_SCHEDULE_LIST = "Schedule is empty. Add some!";

    // General error messages
    //@@author durianpancakes
    public static final String MESSAGE_STORAGE_READ_ERROR = "There was an error loading your files.";
    public static final String MESSAGE_STORAGE_INITIALIZATION_ERROR = "STORAGE: There was an error";
    public static final String MESSAGE_STORAGE_CORRUPTED = "): Storage file corrupted. "
            + "Please delete your data directory and relaunch.";
    public static final String MESSAGE_INCOMPLETE_LIST_PARAMETERS = "Please tell me what you want to be listed! "
            + "You can either 'list event' or 'list quiz' or 'list contact'";
    //@@author AndreWongZH
    public static final String MESSAGE_INITIALIZATION_LOGGER_FAILED = "Failed to set up logger";
    public static final String MESSAGE_MISSING_MODEL = "☹ Oops! Model type is missing! "
            + "Enter 'help' if needed!";
    //@@author elizabethcwt
    public static final String MESSAGE_MISSING_PARAMETERS = "☹ OOPS!!! "
            + "Remember to include ALL %s inputs!";
    public static final String MESSAGE_EMPTY_PARAMETERS = "☹ OOPS!!! Ensure ALL parameters are filled up!";
    public static final String MESSAGE_LIST_INVALID_DATE = "☹ OOPS!!! Please enter today/week/valid date"
            + "and time in format yyyy-mm-dd!";
    public static final String MESSAGE_INVALID_DATE = "☹ OOPS!!! "
            + "Please enter valid date and time in format yyyy-mm-dd HHMM!";
    public static final String MESSAGE_INVALID_COMMAND = "☹ Oops! I did not recognize that command! "
            + "Enter 'help' if needed!";
    public static final String MESSAGE_INVALID_MODEL = "☹ Oops! I did not recognize that model type! "
            + "Enter 'help' if needed!";
    public static final String MESSAGE_LIST_EXTRA_PARAM = "☹ OOPS!!! Please do not enter extra inputs";

    //@@author durianpancakes
    // Messages from CalendarWeekRenderer
    public static final String MESSAGE_MONDAY_LABEL = "[MON]";
    public static final String MESSAGE_TUESDAY_LABEL = "[TUE]";
    public static final String MESSAGE_WEDNESDAY_LABEL = "[WED]";
    public static final String MESSAGE_THURSDAY_LABEL = "[THU]";
    public static final String MESSAGE_FRIDAY_LABEL = "[FRI]";
    public static final String MESSAGE_SATURDAY_LABEL = "[SAT]";
    public static final String MESSAGE_SUNDAY_LABEL = "[SUN]";

    //@@author untitle4
    // Messages from Contact related classes
    public static final String MESSAGE_CONTACT_ADD_SUCCESS = "Got it. I've added this contact: ";
    public static final String MESSAGE_CONTACT_DELETE_SUCCESS = "Noted. I've removed this contact: ";
    public static final String MESSAGE_CONTACT_DELETE_ERROR_NON_NUMBER = "☹ OOPS!!! "
            + "Please indicate in NUMERALS, which contact you'd like to delete!";
    public static final String MESSAGE_CONTACT_DELETE_ERROR_NO_NUMBER_GIVEN = "☹ OOPS!!! "
            + "Please indicate which contact you'd like to delete!";
    public static final String MESSAGE_INVALID_CONTACT_INDEX = "☹ OOPS!!! Please indicate a valid contact index!";
    public static final String MESSAGE_EMPTY_CONTACT_LIST = "Contact list is empty. Add some!!";

    //@@author elizabethcwt
    // Messages from Class related classes
    public static final String MESSAGE_CLASS_ADD_SUCCESS = "Got it. I've added this class: ";
    public static final String MESSAGE_CLASS_DELETE_SUCCESS = "Noted. I've removed this class: ";
    public static final String MESSAGE_CLASS_DELETE_ERROR_NON_NUMBER = "☹ OOPS!!! "
            + "Please indicate in NUMERALS, which class you'd like to delete!";
    public static final String MESSAGE_CLASS_DELETE_ERROR_NO_NUMBER_GIVEN = "☹ OOPS!!! "
            + "Please indicate which class you'd like to delete!";
    public static final String MESSAGE_CLASS_DONE_SUCCESS = "Nice! I've marked this class as done:";
    public static final String MESSAGE_CLASS_DONE_ERROR_NON_NUMBER = "☹ OOPS!!! "
            + "Please indicate in NUMERALS, which class you'd like to set as Done!";
    public static final String MESSAGE_CLASS_DONE_ERROR_NO_NUMBER_GIVEN = "☹ OOPS!!! "
            + "Please indicate which class you'd like to set as Done!";
    public static final String MESSAGE_INVALID_CLASS_INDEX = "☹ OOPS!!! Please indicate a valid class index!";

    //@@author untitle4
    // Messages from Cca related classes
    public static final String MESSAGE_CCA_ADD_SUCCESS = "Got it. I've added this cca: ";
    public static final String MESSAGE_CCA_DELETE_SUCCESS = "Noted. I've removed this cca: ";
    public static final String MESSAGE_CCA_DELETE_ERROR_NON_NUMBER = "☹ OOPS!!! "
            + "Please indicate in NUMERALS, which cca you'd like to delete!";
    public static final String MESSAGE_CCA_DELETE_ERROR_NO_NUMBER_GIVEN = "☹ OOPS!!! "
            + "Please indicate which cca you'd like to delete!";
    public static final String MESSAGE_CCA_DONE_SUCCESS = "Nice! I've marked this cca as done:";
    public static final String MESSAGE_CCA_DONE_ERROR_NON_NUMBER = "☹ OOPS!!! "
            + "Please indicate in NUMERALS, which cca you'd like to set as Done!";
    public static final String MESSAGE_CCA_DONE_ERROR_NO_NUMBER_GIVEN = "☹ OOPS!!! "
            + "Please indicate which cca you'd like to set as Done!";
    public static final String MESSAGE_INVALID_CCA_INDEX = "☹ OOPS!!! Please indicate a valid cca index!";

    //@@author Aliciaho
    // Messages from Test related classes
    public static final String MESSAGE_TEST_ADD_SUCCESS = "Got it. I've added this test: ";
    public static final String MESSAGE_TEST_DELETE_SUCCESS = "Noted. I've removed this test: ";
    public static final String MESSAGE_TEST_DELETE_ERROR_NON_NUMBER = "☹ OOPS!!! "
            + "Please indicate in NUMERALS, which test you'd like to delete!";
    public static final String MESSAGE_TEST_DELETE_ERROR_NO_NUMBER_GIVEN = "☹ OOPS!!! "
            + "Please indicate which test you'd like to delete!";
    public static final String MESSAGE_TEST_DONE_SUCCESS = "Nice! I've marked this test as done:";
    public static final String MESSAGE_TEST_DONE_ERROR_NON_NUMBER = "☹ OOPS!!! "
            + "Please indicate in NUMERALS, which test you'd like to set as Done!";
    public static final String MESSAGE_TEST_DONE_ERROR_NO_NUMBER_GIVEN = "☹ OOPS!!! "
            + "Please indicate which test you'd like to set as Done!";
    public static final String MESSAGE_INVALID_TEST_INDEX = "☹ OOPS!!! Please indicate a valid test index!";

    //@@author durianpancakes
    // Messages from Tuition related classes
    public static final String MESSAGE_MISSING_TUITION_SUFFIX = "☹ OOPS!!! "
            + "Remember to include ALL '/n', '/s', '/e', '/l' suffixes!";
    public static final String MESSAGE_MISSING_TUITION_INPUT = "☹ OOPS!!! "
            + "Remember to include ALL '/n', '/s', '/e', '/l' inputs!";
    public static final String MESSAGE_TUITION_ADD_SUCCESS = "Got it. I've added this tuition: ";
    public static final String MESSAGE_TUITION_DELETE_SUCCESS = "Noted. I've removed this tuition: ";
    public static final String MESSAGE_TUITION_DELETE_ERROR_NON_NUMBER = "☹ OOPS!!! "
            + "Please indicate in NUMERALS, which tuition you'd like to delete!";
    public static final String MESSAGE_TUITION_DELETE_ERROR_NO_NUMBER_GIVEN = "☹ OOPS!!! "
            + "Please indicate which tuition you'd like to delete!";
    public static final String MESSAGE_TUITION_DONE_SUCCESS = "Nice! I've marked this tuition as done:";
    public static final String MESSAGE_TUITION_DONE_ERROR_NON_NUMBER = "☹ OOPS!!! "
            + "Please indicate in NUMERALS, which tuition you'd like to set as Done!";
    public static final String MESSAGE_TUITION_DONE_ERROR_NO_NUMBER_GIVEN = "☹ OOPS!!! "
            + "Please indicate which tuition you'd like to set as Done!";
    public static final String MESSAGE_INVALID_TUITION_INDEX = "☹ OOPS!!! Please indicate a valid tuition index!";

    //@@author elizabethcwt
    // Messages from Quiz related classes
    public static final String MESSAGE_QUIZ_DELETE_ERROR_NON_NUMBER = "☹ OOPS!!! Please indicate in NUMERALS, "
            + "which quiz you'd like to delete!\n";
    public static final String MESSAGE_INVALID_HELP_COMMAND = "☹ OOPS!!! Are you trying to take a quiz or add/delete a"
            + " quiz question? Enter 'help' to check the correct format!\n";

    public static String invalid_number_of_quiz_questions_message(int size) {
        return "☹ OOPS!!! Please enter a valid number of quiz questions to attempt! (1~" + size + ")\n";
    }

    public static final String MESSAGE_MISSING_QUIZ_PARAM = "Please indicate the command you would like to apply on"
            + " quiz!";
    public static final String MESSAGE_QUIZ_NON_NUMBER = "Please enter a valid number or "
            + "enter 'quiz record' to see your incorrect questions in your previous quiz!";


    //@@author elizabethcwt
    public static final String MESSAGE_HELP = "Hello! Here is a list of commands you can try:\n\n"
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
            + "\t19. Add quiz question: 'add quiz /q [question] /o1 [option 1] /o2 [option 2] /o3 [option 3]"
            + " /o4 [option 4] /a [option answer] /exp (explanation)'\n"
            + "\t20. Delete quiz question: 'delete quiz [question number]'\n"
            + "\t21. List quiz questions: 'list quiz'\n"
            + "\t22. Find quiz questions: 'find quiz [keyword(s)]'\n"
            + "\t23. Search former incorrect quiz question records: 'quiz record'\n\n"
            + "\t24. Exit program: 'bye'\n\n"
            + "\n\tNOTE:\n\t1. Please enter the date-time in the following format: YYYY-MM-DD "
            + "[time in 24hr format]\n\te.g. 2020-08-19 1300\n\n"
            + "\t2. For command 20 (Add quiz question), the 'explanation' field is OPTIONAL\n\n";
    public static final String MESSAGE_BYE = "BYE BYE! SEE YOU NEXT TIME! :3";
    public static final String MESSAGE_EXTRA_HELP_PARAM = "OOPS! Were you trying to ask for help? Just enter 'help'!";
    public static final String MESSAGE_NO_EVENTS_FOUND = "Sorry but your searches yield no results!";
    public static final String MESSAGE_NO_QUIZZES_FOUND = "Sorry but there is not such a quiz in your list!";
}
