package seedu.duke.common;

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
    public static final String MESSAGE_PROMPT_HOURS = "Key in the number of hours you would like "
            + "to be productive each day (not more than 12)?";
    public static final String MESSAGE_SHOW_HOURS = "This is your recommended hours per day: ";
    public static final String MESSAGE_SHOW_NEW_HOURS = "This is your new recommended hours per day: ";
    public static final String MESSAGE_HELLO = "Hello ";
    public static final String MESSAGE_PROMPT_COMMAND = "What can we do for you? "
            + "(Enter 'help' for the list of available commands!)\n";
    public static final String MESSAGE_HOURS_ERROR_NON_NUMBER = "Please indicate in NUMERALS, "
            + "how many hours you would like to set!";

    // General error messages
    //@@author durianpancakes
    public static final String MESSAGE_STORAGE_READ_ERROR = "There was an error loading your files.";
    public static final String MESSAGE_STORAGE_INITIALIZATION_ERROR = "STORAGE: There was an error";
    public static final String MESSAGE_STORAGE_CORRUPTED = "): Storage file corrupted.";
    public static final String MESSAGE_FACTORY_RESET_PROMPT = "Would you like to reset Plan&Score? [y/n]"
            + "(NOTE: This will result in the loss of all data!)";
    public static final String MESSAGE_MANUAL_TROUBLESHOOT_PROMPT = "Otherwise, consider going through the "
            + "troubleshooting steps provided in our User Guide to fix the problem manually.";
    public static final String MESSAGE_FACTORY_RESET_INVALID_INPUT_PROMPT = "Please enter [y/n] only!";
    public static final String MESSAGE_FACTORY_RESET_SUCCESSFUL = "Reset successful! Please relaunch Plan&Score.";
    public static final String MESSAGE_FACTORY_RESET_FAILED_OR_CANCELLED = "Reset failed/cancelled. "
            + "Please follow troubleshooting steps provided in our User Guide to reset Plan&Score.";
    public static final String MESSAGE_INCOMPLETE_LIST_PARAMETERS = "Please tell me what you want to be listed! "
            + "You can either 'list event' or 'list quiz' or 'list contact'";
    public static final String MESSAGE_RECOMMENDED_TIME_EXCEEDED = "Recommended time exceeded!";
    public static final String MESSAGE_PROMPT_CHECK_START_END_INPUTS = "Please check the start and end inputs again!";
    public static final String MESSAGE_ERROR_START_AFTER_END = "The start time given is later "
            + "than the end time given!\n"
            + "Please check your inputs again!";
    public static final String MESSAGE_ERROR_EQUALS_END = "The start time given is the same as "
            + "the end time given!\n"
            + "Please check your inputs again!";
    //@@author AndreWongZH
    public static final String MESSAGE_EMPTY_SCHEDULE_LIST = "Schedule is %s. Add some!";
    public static final String MESSAGE_INITIALIZATION_LOGGER_FAILED = "Failed to set up logger";
    public static final String MESSAGE_MISSING_MODEL = ":( Oops! Category type is missing! "
            + "Enter 'help' if needed!";
    public static final String MESSAGE_INCOMPLETE_FIND_PARAMETERS = "Please tell me what you want to be listed! "
            + "You can either 'find event' or 'find quiz' or 'find contact'";
    public static final String MESSAGE_SWAPPED_PARAMETERS = ":( Please do not swap the parameters";
    //@@author elizabethcwt
    public static final String MESSAGE_MISSING_PARAMETERS = ":( OOPS!!! "
            + "Remember to include ALL %s inputs!";
    public static final String MESSAGE_EMPTY_PARAMETERS = ":( OOPS!!! Ensure ALL parameters are filled up!";
    public static final String MESSAGE_INVALID_COMMAND = ":( Oops! I did not recognize that command! "
            + "Enter 'help' if needed!";
    public static final String MESSAGE_INVALID_MODEL = ":( Oops! I did not recognize that category type"
            + " or category not compatible with command! "
            + "Enter 'help' if needed!";
    public static final String MESSAGE_INVALID_EXTRA_PARAM = ":( OOPS!!! Please do not enter extra inputs";
    //@@author Aliciaho
    //Messages for date time
    public static final String MESSAGE_LIST_INVALID_DATE = ":( OOPS!!! Please enter today/week/valid date"
            + "and time in format yyyy-mm-dd!";
    public static final String MESSAGE_INVALID_DATE = ":( OOPS!!! "
            + "Please enter valid date and time in format yyyy-mm-dd HHMM!";

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
    public static final String MESSAGE_CONTACT_DELETE_ERROR_NON_NUMBER = ":( OOPS!!! "
            + "Please indicate in NUMERALS, which contact you'd like to delete!";
    public static final String MESSAGE_CONTACT_DELETE_ERROR_NO_NUMBER_GIVEN = ":( OOPS!!! "
            + "Please indicate which contact you'd like to delete!";
    public static final String MESSAGE_INVALID_CONTACT_INDEX = ":( OOPS!!! Please indicate a valid contact index!";
    public static final String MESSAGE_EMPTY_CONTACT_LIST = "Contact list is empty. Add some!!";
    public static final String MESSAGE_CONTACT_INDEX_OUT_OF_BOUNDS = "There is not such a contact in your list!";

    //@@author elizabethcwt
    // Messages from Class related classes
    public static final String MESSAGE_CLASS_ADD_SUCCESS = "Got it. I've added this class: ";
    public static final String MESSAGE_CLASS_DELETE_SUCCESS = "Noted. I've removed this class: ";
    public static final String MESSAGE_CLASS_DELETE_ERROR_NON_NUMBER = ":( OOPS!!! "
            + "Please indicate in NUMERALS, which class you'd like to delete!";
    public static final String MESSAGE_CLASS_DELETE_ERROR_NO_NUMBER_GIVEN = ":( OOPS!!! "
            + "Please indicate which class you'd like to delete!";
    public static final String MESSAGE_CLASS_DONE_SUCCESS = "Nice! I've marked this class as done:";
    public static final String MESSAGE_CLASS_DONE_ERROR_NON_NUMBER = ":( OOPS!!! "
            + "Please indicate in NUMERALS, which class you'd like to set as Done!";
    public static final String MESSAGE_CLASS_DONE_ERROR_NO_NUMBER_GIVEN = ":( OOPS!!! "
            + "Please indicate which class you'd like to set as Done!";
    public static final String MESSAGE_INVALID_CLASS_INDEX = ":( OOPS!!! Please indicate a valid class index!";

    //@@author untitle4
    // Messages from Cca related classes
    public static final String MESSAGE_CCA_ADD_SUCCESS = "Got it. I've added this cca: ";
    public static final String MESSAGE_CCA_DELETE_SUCCESS = "Noted. I've removed this cca: ";
    public static final String MESSAGE_CCA_DELETE_ERROR_NON_NUMBER = ":( OOPS!!! "
            + "Please indicate in NUMERALS, which cca you'd like to delete!";
    public static final String MESSAGE_CCA_DELETE_ERROR_NO_NUMBER_GIVEN = ":( OOPS!!! "
            + "Please indicate which cca you'd like to delete!";
    public static final String MESSAGE_CCA_DONE_SUCCESS = "Nice! I've marked this cca as done:";
    public static final String MESSAGE_CCA_DONE_ERROR_NON_NUMBER = ":( OOPS!!! "
            + "Please indicate in NUMERALS, which cca you'd like to set as Done!";
    public static final String MESSAGE_CCA_DONE_ERROR_NO_NUMBER_GIVEN = ":( OOPS!!! "
            + "Please indicate which cca you'd like to set as Done!";
    public static final String MESSAGE_INVALID_CCA_INDEX = ":( OOPS!!! Please indicate a valid cca index!";

    //@@author Aliciaho
    // Messages from Test related classes
    public static final String MESSAGE_TEST_ADD_SUCCESS = "Got it. I've added this test: ";
    public static final String MESSAGE_TEST_DELETE_SUCCESS = "Noted. I've removed this test: ";
    public static final String MESSAGE_TEST_DELETE_ERROR_NON_NUMBER = ":( OOPS!!! "
            + "Please indicate in NUMERALS, which test you'd like to delete!";
    public static final String MESSAGE_TEST_DELETE_ERROR_NO_NUMBER_GIVEN = ":( OOPS!!! "
            + "Please indicate which test you'd like to delete!";
    public static final String MESSAGE_TEST_DONE_SUCCESS = "Nice! I've marked this test as done:";
    public static final String MESSAGE_TEST_DONE_ERROR_NON_NUMBER = ":( OOPS!!! "
            + "Please indicate in NUMERALS, which test you'd like to set as Done!";
    public static final String MESSAGE_TEST_DONE_ERROR_NO_NUMBER_GIVEN = ":( OOPS!!! "
            + "Please indicate which test you'd like to set as Done!";
    public static final String MESSAGE_INVALID_TEST_INDEX = ":( OOPS!!! Please indicate a valid test index!";
    public static final String MESSAGE_TIME_LEFT_HEADER = "Time left for this day: ";

    //@@author durianpancakes
    // Messages from Tuition related classes
    public static final String MESSAGE_MISSING_TUITION_SUFFIX = ":( OOPS!!! "
            + "Remember to include ALL '/n', '/s', '/e', '/l' suffixes!";
    public static final String MESSAGE_MISSING_TUITION_INPUT = ":( OOPS!!! "
            + "Remember to include ALL '/n', '/s', '/e', '/l' inputs!";
    public static final String MESSAGE_TUITION_ADD_SUCCESS = "Got it. I've added this tuition: ";
    public static final String MESSAGE_TUITION_DELETE_SUCCESS = "Noted. I've removed this tuition: ";
    public static final String MESSAGE_TUITION_DELETE_ERROR_NON_NUMBER = ":( OOPS!!! "
            + "Please indicate in NUMERALS, which tuition you'd like to delete!";
    public static final String MESSAGE_TUITION_DELETE_ERROR_NO_NUMBER_GIVEN = ":( OOPS!!! "
            + "Please indicate which tuition you'd like to delete!";
    public static final String MESSAGE_TUITION_DONE_SUCCESS = "Nice! I've marked this tuition as done:";
    public static final String MESSAGE_TUITION_DONE_ERROR_NON_NUMBER = ":( OOPS!!! "
            + "Please indicate in NUMERALS, which tuition you'd like to set as Done!";
    public static final String MESSAGE_TUITION_DONE_ERROR_NO_NUMBER_GIVEN = ":( OOPS!!! "
            + "Please indicate which tuition you'd like to set as Done!";
    public static final String MESSAGE_INVALID_TUITION_INDEX = ":( OOPS!!! Please indicate a valid tuition index!";

    //@@author elizabethcwt
    // Messages from Quiz related classes
    public static final String MESSAGE_QUIZ_DELETE_ERROR_NON_NUMBER = ":( OOPS!!! Please indicate in NUMERALS, "
            + "which quiz you'd like to delete!\n";
    public static final String MESSAGE_INVALID_HELP_COMMAND = ":( OOPS!!! Are you trying to take a quiz or add/delete a"
            + " quiz question? Enter 'help' to check the correct format!";
    public static final String MESSAGE_EMPTY_QUIZ_LIST = "Quiz list is empty. Add some!";
    public static final String MESSAGE_QUIZ_INDEX_OUT_OF_BOUND = "There is no such question in your quiz list!";
    public static final String MESSAGE_QUIZ_QUESTION_NOT_FOUND = "No question provided";
    public static final String MESSAGE_QUIZ_ANSWER_NOT_FOUND = "No answer provided";
    public static final String MESSAGE_QUIZ_OPTIONS_NOT_FOUND = "Options not provided";
    public static final String MESSAGE_QUIZ_ADD_SUCCESSFUL = "Quiz question added!";
    public static final String MESSAGE_QUIZ_INVALID_ANS_PROVIDED = ":( OOPS! Incorrect answer format! "
            + "Your answer can only be either 1, 2, 3 or 4!";
    public static final String MESSAGE_QUIZ_FULL_MARKS = "Congratulations! You got full marks in your last attempt!";
    public static final String MESSAGE_QUIZ_WRONG_QUESTIONS_HEADER = "Here are the incorrect questions "
            + "in your last quiz attempt:";
    public static final String MESSAGE_QUIZ_LIST_HEADER = "Here are the questions in your quiz list:";
    public static final String MESSAGE_QUIZ_MISSING_ANSWER = ":( OOPS! Please enter your answer for the "
            + "question above!";
    public static final String MESSAGE_NO_QUIZ_ATTEMPTS = "You have not taken a quiz yet!";

    public static String invalid_number_of_quiz_questions_message(int size) {
        return ":( OOPS!!! Please enter a valid number of quiz questions to attempt! (1~" + size + ")";
    }

    public static final String MESSAGE_MISSING_QUIZ_PARAM = "Please indicate the command you would like to apply on"
            + " quiz!";

    public static final String MESSAGE_QUIZ_NON_NUMBER = "Please enter a valid number or "
            + "enter 'quiz record' to see your incorrect questions in your previous quiz!";

    public static String print_quiz_score(int correctCounter, int noOfQues) {
        return "You scored " + correctCounter + " out of " + noOfQues + "!"
                + "Scroll up to review your quiz.";
    }

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
            + "\t10. Find relevant event(s): 'find [keyword(s)]'\n\n"
            + "\t11. Add contact: 'add contact /s [subject] /n [name of contact person] /p [phone number]"
            + " /e [email address]'\n"
            + "\t12. Delete contact: 'delete contact [contact number]'\n"
            + "\t13. List contact: 'list contact'\n"
            + "\t14. Find contact: 'find contact [keyword(s)]'\n\n"
            + "\t15. Take Mathematics quiz: 'quiz [no. of questions]'\n"
            + "\t16. Add quiz question: 'add quiz /q [question] /o1 [option 1] /o2 [option 2] /o3 [option 3]"
            + " /o4 [option 4] /a [option answer] /exp (explanation)'\n"
            + "\t17. Delete quiz question: 'delete quiz [question number]'\n"
            + "\t18. List quiz questions: 'list quiz'\n"
            + "\t19. Find quiz questions: 'find quiz [keyword(s)]'\n"
            + "\t20. Display former incorrect quiz question records: 'quiz record'\n\n"
            + "\t21. Exit program: 'bye'\n\n"
            + "\n\tNOTE:\n\t1. Please enter the date-time in the following format: YYYY-MM-DD "
            + "[time in 24hr format]\n\te.g. 2020-08-19 1300\n\n"
            + "\t2. For command 16 (Add quiz question), the 'explanation' field is OPTIONAL\n\n";

    public static final String MESSAGE_BYE = "BYE BYE! SEE YOU NEXT TIME! :3";
    public static final String MESSAGE_EXTRA_HELP_PARAM = "OOPS! Were you trying to ask for help? Just enter 'help'!";
    public static final String MESSAGE_NO_EVENTS_FOUND = "Sorry but your searches yield no results!";
    public static final String MESSAGE_NO_QUIZZES_FOUND = "Sorry but there is not such a quiz in your list!";
}
