package seedu.duke.model.quiz;

import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.exception.SwappedParameterException;
import seedu.duke.model.ModelManager;
import seedu.duke.ui.UserInterface;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.Collections;
import java.util.logging.Logger;

public class QuizManager extends ModelManager implements QuizInteractable {
    public static final int BEGIN_INDEX = 0;
    public static final int END_INDEX = 1;
    public static final int EMPTY_SIZE = 0;
    public static final int USER_INPUT_OFFSET = 9;
    public static final int INDEX_QUESTION = 1;
    public static final int INDEX_OP1 = 2;
    public static final int INDEX_OP2 = 3;
    public static final int INDEX_OP3 = 4;
    public static final int INDEX_OP4 = 5;
    public static final int INDEX_ANS = 6;
    public static final int INDEX_EXP = 7;
    public static final int OFFSET_QUESTION = 1;
    public static final int OFFSET_OPTION = 2;
    public static final int OFFSET_ANS = 1;
    public static final int OFFSET_EXP = 3;
    public static final int ANS_MIN = 1;
    public static final int ANS_MAX = 4;
    public static final String EMPTY_STRING = "";
    public static final int INDEX_OFFSET = 1;
    public static final int MAX_INPUT_LENGTH_NO_EXP = 7;
    public static final int MAX_INPUT_LENGTH_WITH_EXP = 8;
    public static final String QUESTION_PREFIX = " /q ";
    public static final String ANSWER_PREFIX = " /a ";
    public static final String OPTION_ONE_PREFIX = " /o1 ";
    public static final String OPTION_TWO_PREFIX = " /o2 ";
    public static final String OPTION_THREE_PREFIX = " /o3 ";
    public static final String OPTION_FOUR_PREFIX = " /o4 ";
    public static final String EXPLANATION_PREFIX = " /exp ";
    public static final int END_DOUBLE_INDEX = 2;
    public static final int END_TRIPLE_INDEX = 3;
    public static final int SLASH_INDEX = 2;
    public static final int OFFSET_1 = 3;
    public static final int OFFSET_2 = 4;
    public static final int OFFSET_3 = 5;
    public static int QUIZ_ATTEMPTS = 0;
    private final ArrayList<Quiz> quizzes;
    private final ArrayList<Quiz> lastIncorrectQuizzes = new ArrayList<>();
    private final ArrayList<Integer> lastIncorrectAnswers = new ArrayList<>();
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();
    public static int noOfQues;
    private final UserInterface userInterface;
    public static String correctnessLogo;
    public static ArrayList<Integer> quizIndexes = new ArrayList<>();
    UserAnswerManager userAnswerManager = new UserAnswerManager();

    public QuizManager(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
        this.userInterface = UserInterface.getInstance();
    }

    public ArrayList<Quiz> getQuizList() {
        return quizzes;
    }

    public int getQuizListSize() {
        assert quizzes != null;
        return quizzes.size();
    }

    //@@author elizabethcwt
    public void checkQuizSizeValidity(String[] separatedInputs) {

        if (getQuizListSize() == 0) {

            // If user attempts to take a quiz, but the quiz list is empty
            userInterface.showToUser(Messages.MESSAGE_EMPTY_QUIZ_LIST);
        } else {

            // If user attempts to take a quiz, and the quiz list has at least 1 quiz question
            takeQuiz(separatedInputs);
        }
    }

    //@@author elizabethcwt
    private void takeQuiz(String[] separatedInputs) {
        try {
            noOfQues = Integer.parseInt(separatedInputs[1]);
            assert noOfQues != 0 : "noOfQues should not be 0";

            if (!((noOfQues > 0) && (noOfQues <= getQuizListSize()))) {

                // If user inputs an invalid number of questions to be attempted (NOT within range of 1 to quiz size)
                handleInvalidNumOfQuestions();
            } else {

                // If user inputs a valid number of quiz questions (within range of 1 to quiz size)
                handleValidNumOfQuestions();
                QUIZ_ATTEMPTS++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_HELP_COMMAND);
        }
    }

    //@@author elizabethcwt
    private void handleValidNumOfQuestions() {

        // Assert that noOfQues is within a valid range
        assert ((noOfQues > 0) && (noOfQues <= getQuizListSize())) : "noOfQues should be of a valid value, but"
                + "it is invalid";

        initialisingShufflingOfQuestions();

        int questionCounter = 0;

        // Assert that questionCounter is less than noOfQues
        assert (questionCounter < noOfQues) : "questionCounter should not be more than noOfQues";

        while (questionCounter < noOfQues) {

            questionCounter = testForValidInput(questionCounter);
        }

        // Initialising counter for correctly answered questions
        int correctCounter = 0;

        // Clear arraylist to store incorrect quizzes
        lastIncorrectQuizzes.clear();
        lastIncorrectAnswers.clear();

        // Compare and note if students' answers are correct
        correctCounter = storeCorrectnessOfQuizAnswer(correctCounter);

        for (int l = 0; l < noOfQues; l++) {

            // Assigning the correctness logo to be printed with questions post-quiz
            assignCorrectnessLogo(l);

            // Print out all quiz questions, user's answers, correctness, correct answers and explanations
            userInterface.showToUser("Question " + (l + INDEX_OFFSET) + ": ",
                    quizzes.get(quizIndexes.get(l)).printPostQuizQuestion(userAnswerManager
                            .getUserAnswers().get(l), correctnessLogo));
        }

        // Print out quiz score
        userInterface.showToUser(Messages.print_quiz_score(correctCounter, noOfQues));

        // Empty userAnswers ArrayList and correctness ArrayList
        userAnswerManager.getUserAnswers().clear();
        userAnswerManager.getCorrectness().clear();
    }

    //@@author elizabethcwt
    private void assignCorrectnessLogo(int l) {

        // Assert that the correctness of the user's input is true in this if loop
        assert ((userAnswerManager.getCorrectness().get(l).equals(true))
                || (userAnswerManager.getCorrectness().get(l).equals(false))) : "User's answer should either be correct"
                + "or false for this question";

        if (userAnswerManager.getCorrectness().get(l).equals(true)) {

            correctnessLogo = " [CORRECT ☺︎]";
        } else {

            // Assert that the correctness of the user's input is false in this else loop
            assert (userAnswerManager.getCorrectness().get(l).equals(false)) : "User's answer should be"
                    + " incorrect for this question";

            correctnessLogo = " [WRONG ☹︎]";
        }
    }

    //@@author elizabethcwt
    private int storeCorrectnessOfQuizAnswer(int correctCounter) {
        for (int k = 0; k < noOfQues; k++) {
            if (userAnswerManager.getUserAnswers().get(k).equals(quizzes.get(quizIndexes
                    .get(k)).getAnswer())) {
                userAnswerManager.getCorrectness().add(true);
                correctCounter++;
            } else {
                userAnswerManager.getCorrectness().add(false);
                lastIncorrectQuizzes.add(quizzes.get(quizIndexes.get(k)));
                lastIncorrectAnswers.add(userAnswerManager.getUserAnswers().get(k));
            }
            quizzes.get(quizIndexes.get(k)).updateLastAccessed();
        }
        return correctCounter;
    }

    //@@author elizabethcwt
    private void initialisingShufflingOfQuestions() {
        // Create a new list of the question indexes
        quizIndexes = new ArrayList<>();
        for (int i = 0; i < quizzes.size(); i++) {
            quizIndexes.add(i);
        }

        // Shuffle the question indexes
        Collections.shuffle(quizIndexes);
    }

    //@@author elizabethcwt
    private void handleInvalidNumOfQuestions() {

        // Assert that noOfQues is NOT an acceptable value
        assert (!((noOfQues > 0) && (noOfQues <= getQuizListSize()))) : "noOfQues should not be of a valid"
                + " value, but it is";

        // If user inputs an invalid number of quiz questions (not within range of 1 to quiz size)
        userInterface.showToUser(Messages.invalid_number_of_quiz_questions_message(quizzes.size()));
    }

    //@@author elizabethcwt
    public int testForValidInput(int questionCounter) {
        // Print out each question
        userInterface.showToUser("",
                "Question " + (questionCounter + INDEX_OFFSET) + ": ",
                quizzes.get(quizIndexes.get(questionCounter)).printQuizQuestion());

        // Create a Scanner object
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        userInput.replaceAll("\\s+", "");

        if (userInput.equals("")) {

            userInterface.showToUser(Messages.MESSAGE_QUIZ_MISSING_ANSWER);
        } else {

            boolean b = userInput.equals("1") || userInput.equals("2") || userInput.equals("3")
                    || userInput.equals("4");

            if (b) {

                // Store user's quiz answers into ArrayList
                userAnswerManager.getUserAnswers().add(Integer.parseInt(userInput));

                questionCounter++;
                return questionCounter;
            } else {

                // Assert that the user's input is NOT one of the valid options
                assert (!b) : "User's input should not be one of the valid options (1, 2, 3 or 4)";

                userInterface.showToUser(Messages.MESSAGE_QUIZ_INVALID_ANS_PROVIDED);
            }
        }

        return questionCounter;
    }

    //@@author untitle4
    /**
     * Delete a quiz in the Arraylist of quizzes.
     * Extract the index of the quiz that the user want to delete.
     *
     * @param userInputs The input entered by the user.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    @Override
    public void delete(String[] userInputs) throws IndexOutOfBoundsException {
        int quizIndex;

        try {
            quizIndex = Integer.parseInt(userInputs[2]);
            if ((quizIndex <= 0) || (quizIndex > getQuizListSize())) {
                throw new IndexOutOfBoundsException();
            }
        } catch (NumberFormatException e) {
            userInterface.showToUser(Messages.MESSAGE_QUIZ_DELETE_ERROR_NON_NUMBER);
            return;
        } catch (IndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_QUIZ_INDEX_OUT_OF_BOUND);
            return;
        }

        userInterface.showToUser("Noted. I've removed this quiz question:",
                quizzes.get(quizIndex - INDEX_OFFSET).toString());

        quizzes.remove(quizIndex - INDEX_OFFSET);
        getQuizStatement();
    }

    //@@author AndreWongZH
    /**
     * Adds a quiz to the ArrayList of quizzes.
     * Extracts the question, options, explanations if any before adding it as a quiz.
     * If user command contains extra parameters, inform the user and return.
     *
     * @param userInput The input entered by the user.
     * @throws EmptyParameterException If there are missing parameters after the prefix.
     */
    @Override
    public void add(String userInput) throws EmptyParameterException, SwappedParameterException {
        if (!userInput.contains(QUESTION_PREFIX)) {
            userInterface.showToUser(Messages.MESSAGE_QUIZ_QUESTION_NOT_FOUND);
            return;
        }

        if (!userInput.contains(ANSWER_PREFIX)) {
            userInterface.showToUser(Messages.MESSAGE_QUIZ_ANSWER_NOT_FOUND);
            return;
        }

        if (!userInput.contains(OPTION_ONE_PREFIX) || !userInput.contains(OPTION_TWO_PREFIX)
                || !userInput.contains(OPTION_THREE_PREFIX) || !userInput.contains(OPTION_FOUR_PREFIX)) {
            userInterface.showToUser(Messages.MESSAGE_QUIZ_OPTIONS_NOT_FOUND);
            return;
        }
        String[] separatedInputs = userInput.trim().split("/");

        validateSwappedParameters(separatedInputs);

        if (separatedInputs.length > MAX_INPUT_LENGTH_NO_EXP && !userInput.contains(EXPLANATION_PREFIX)) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_EXTRA_PARAM);
            return;
        }

        if (separatedInputs.length > MAX_INPUT_LENGTH_WITH_EXP) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_EXTRA_PARAM);
            return;
        }

        try {
            quizzes.add(parseQuizQuestion(separatedInputs));
            userInterface.showToUser(Messages.MESSAGE_QUIZ_ADD_SUCCESSFUL);
        } catch (NumberFormatException e) {
            userInterface.showToUser(Messages.MESSAGE_QUIZ_INVALID_ANS_PROVIDED);
        }
    }

    //@@author AndreWongZH
    //Refactored by durianpancakes
    /**
     * Extracts out the question, options, answers and explanation from user input.
     * If there is no explanation given, explanation will be empty string by default.
     *
     * @param separatedInputs An array string of user's input.
     * @return A Quiz object.
     * @throws EmptyParameterException If parameters are empty spaces.
     * @throws NumberFormatException   If answer index is not between 1 and 4.
     */
    private Quiz parseQuizQuestion(String[] separatedInputs) throws EmptyParameterException, NumberFormatException {
        String question = separatedInputs[INDEX_QUESTION].substring(OFFSET_QUESTION).trim();
        String option1 = separatedInputs[INDEX_OP1].substring(OFFSET_OPTION).trim();
        String option2 = separatedInputs[INDEX_OP2].substring(OFFSET_OPTION).trim();
        String option3 = separatedInputs[INDEX_OP3].substring(OFFSET_OPTION).trim();
        String option4 = separatedInputs[INDEX_OP4].substring(OFFSET_OPTION).trim();
        String answer = separatedInputs[INDEX_ANS].substring(OFFSET_ANS).trim();

        String explanation = "";

        if (separatedInputs.length > INDEX_EXP) {
            explanation = separatedInputs[INDEX_EXP].substring(OFFSET_EXP).trim();
        }

        if (question.equals(EMPTY_STRING) || option1.equals(EMPTY_STRING) || option2.equals(EMPTY_STRING)
                || option3.equals(EMPTY_STRING) || option4.equals(EMPTY_STRING) || answer.equals(EMPTY_STRING)) {
            logger.log(Level.WARNING, "question or options or answer is empty");
            throw new EmptyParameterException();
        }

        int answerInInt = Integer.parseInt(answer);

        if (answerInInt > ANS_MAX || answerInInt < ANS_MIN) {
            throw new NumberFormatException();
        }

        return new Quiz(question, option1, option2, option3, option4, answerInInt, explanation);
    }

    //@@author AndreWongZH
    /**
     * Validates if the parameters are swapped.
     *
     * @param userInputs An arraylist of type string of the user input.
     * @throws SwappedParameterException If letter does not match up with the required prefix.
     */
    protected void validateSwappedParameters(String[] userInputs) throws SwappedParameterException {
        boolean hasQ = userInputs[INDEX_QUESTION].substring(BEGIN_INDEX, END_INDEX)
                .contentEquals(QUESTION_PREFIX.substring(SLASH_INDEX, OFFSET_1));
        boolean hasO1 = userInputs[INDEX_OP1].substring(BEGIN_INDEX, END_DOUBLE_INDEX)
                .contentEquals(OPTION_ONE_PREFIX.substring(SLASH_INDEX,OFFSET_2));
        boolean hasO2 = userInputs[INDEX_OP2].substring(BEGIN_INDEX, END_DOUBLE_INDEX)
                .contentEquals(OPTION_TWO_PREFIX.substring(SLASH_INDEX,OFFSET_2));
        boolean hasO3 = userInputs[INDEX_OP3].substring(BEGIN_INDEX, END_DOUBLE_INDEX)
                .contentEquals(OPTION_THREE_PREFIX.substring(SLASH_INDEX, OFFSET_2));
        boolean hasO4 = userInputs[INDEX_OP4].substring(BEGIN_INDEX, END_DOUBLE_INDEX)
                .contentEquals(OPTION_FOUR_PREFIX.substring(SLASH_INDEX, OFFSET_2));
        boolean hasA = userInputs[INDEX_ANS].substring(BEGIN_INDEX, END_INDEX)
                .contentEquals(ANSWER_PREFIX.substring(SLASH_INDEX, OFFSET_1));
        boolean hasE = true;

        if (userInputs.length == MAX_INPUT_LENGTH_WITH_EXP) {
            hasE = userInputs[INDEX_EXP].substring(BEGIN_INDEX, END_TRIPLE_INDEX)
                    .contentEquals(EXPLANATION_PREFIX.substring(SLASH_INDEX, OFFSET_3));
        }

        if (!hasQ || !hasO1 || !hasO2 || !hasO3 || !hasO4 || !hasA || !hasE) {
            throw new SwappedParameterException();
        }
    }

    //@@author untitle4
    /**
     * To find the quiz with certain keyword(s) in the Arraylist of quizzes.
     *
     * @param userInput The input entered by the user.
     * @throws MissingParameterException if there is no keyword(s) provided.
     */
    @Override
    public void find(String userInput) throws MissingParameterException {
        String param = userInput.substring(USER_INPUT_OFFSET).trim();

        if (param.length() == EMPTY_SIZE) {
            throw new MissingParameterException("keywords as");
        }

        FindQuiz findQuiz = new FindQuiz(param, quizzes);
        ArrayList<String> filteredQuizzes = findQuiz.filterQuizzes();

        if (filteredQuizzes.size() == EMPTY_SIZE) {
            userInterface.showToUser(Messages.MESSAGE_NO_QUIZZES_FOUND);
            return;
        }

        userInterface.printArray(filteredQuizzes);
    }

    //@@author untitle4
    /**
     * Show the incorrect quizzes in the user's last attempt.
     */
    public void recordedQuizzes() {
        if (lastIncorrectQuizzes.size() == 0 && QUIZ_ATTEMPTS != 0) {
            userInterface.showToUser(Messages.MESSAGE_QUIZ_FULL_MARKS);
        } else if (QUIZ_ATTEMPTS == 0) {
            userInterface.showToUser(Messages.MESSAGE_NO_QUIZ_ATTEMPTS);
        } else {
            userInterface.showToUser(Messages.MESSAGE_QUIZ_WRONG_QUESTIONS_HEADER);
            for (int i = 0; i < lastIncorrectQuizzes.size(); i++) {
                userInterface.showToUser(lastIncorrectQuizzes.get(i).printQuizQuestion());
                userInterface.showToUser("Your answer: (" + lastIncorrectAnswers.get(i) + ")",
                        "Correct answer: (" + lastIncorrectQuizzes.get(i).getAnswer() + ")");
                if (!lastIncorrectQuizzes.get(i).getExplanation().equals("")) {
                    userInterface.showToUser("Explanation: " + lastIncorrectQuizzes.get(i).getExplanation());
                }
            }
        }

    }

    //@@author untitle4
    /**
     * List the Arraylist of quiz.
     */
    @Override
    public void list() {
        if (quizzes.size() == EMPTY_SIZE) {
            userInterface.showToUser(Messages.MESSAGE_EMPTY_QUIZ_LIST);
        } else {
            userInterface.showToUser(Messages.MESSAGE_QUIZ_LIST_HEADER);
            for (int i = 0; i < quizzes.size(); i++) {
                userInterface.showToUser("Question " + (i + INDEX_OFFSET) + ":",
                        quizzes.get(i).toString());
            }
        }
    }

    private void getQuizStatement() {
        String quizStatement = getQuizListSize() == 1 ? " quiz" : " quizzes";
        userInterface.showToUser("Now you have " + getQuizListSize() + quizStatement + " in the quiz list.");
    }
}
