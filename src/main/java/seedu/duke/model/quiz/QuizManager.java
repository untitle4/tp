package seedu.duke.model.quiz;

import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.InvalidInputException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.ModelManager;
import seedu.duke.ui.UserInterface;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.Collections;
import java.util.logging.Logger;

public class QuizManager extends ModelManager implements QuizInteractable {
    public static final int EMPTY_SIZE = 0;
    public static final int USER_INPUT_OFFSET = 9;
    private final ArrayList<Quiz> quizzes;
    private ArrayList<Quiz> lastIncorrectQuizzes = new ArrayList<>();
    private ArrayList<Integer> lastIncorrectAnswers = new ArrayList<>();
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
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_HELP_COMMAND);
        }
    }

    private void handleValidNumOfQuestions() {

        // Assert that noOfQues is within a valid range
        assert ((noOfQues > 0) && (noOfQues <= getQuizListSize())) : "noOfQues should be of a valid value, but"
                + "it is invalid";

        initialisingShufflingOfQuestions();

        int questionCounter = 0;
        while (questionCounter < noOfQues) {

            // Assert that questionCounter is less than noOfQues
            assert (questionCounter < noOfQues) : "questionCounter should not be more than noOfQues";

            questionCounter = testForValidInput(questionCounter);
        }

        // Initialising counter for correctly answered questions
        int correctCounter = 0;

        // Assert that correctCounter is 0 initially
        assert (correctCounter == 0) : "questionCounter should be 0 initially";

        // Clear arraylist to store incorrect quizzes
        lastIncorrectQuizzes.clear();
        lastIncorrectAnswers.clear();

        // Compare and note if students' answers are correct
        correctCounter = storeCorrectnessOfQuizAnswer(correctCounter);

        for (int l = 0; l < noOfQues; l++) {

            // Assigning the correctness logo to be printed with questions post-quiz
            assignCorrectnessLogo(l);

            // Print out all quiz questions, user's answers, correctness, correct answers and explanations
            System.out.println("Question " + (l + 1) + ": ");
            System.out.println(quizzes.get(quizIndexes.get(l)).printPostQuizQuestion(userAnswerManager
                    .getUserAnswers().get(l), correctnessLogo));
        }

        // Print out quiz score
        userInterface.showToUser(Messages.print_quiz_score(correctCounter, noOfQues));

        // Empty userAnswers ArrayList and correctness ArrayList
        userAnswerManager.getUserAnswers().clear();
        userAnswerManager.getCorrectness().clear();
    }

    private void assignCorrectnessLogo(int l) {
        if (userAnswerManager.getCorrectness().get(l).equals(true)) {

            // Assert that the correctness of the user's input is true in this if loop
            assert (userAnswerManager.getCorrectness().get(l).equals(true)) : "User's answer should be"
                    + " correct for this question";

            correctnessLogo = " [CORRECT ☺︎]";
        } else {

            // Assert that the correctness of the user's input is false in this else loop
            assert (userAnswerManager.getCorrectness().get(l).equals(false)) : "User's answer should be"
                    + " incorrect for this question";

            correctnessLogo = " [WRONG ☹︎]";
        }
    }

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

    private void initialisingShufflingOfQuestions() {
        // Create a new list of the question indexes
        quizIndexes = new ArrayList<>();
        for (int i = 0; i < quizzes.size(); i++) {
            quizIndexes.add(i);
        }

        // Shuffle the question indexes
        Collections.shuffle(quizIndexes);
    }

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
        System.out.println();
        System.out.println("Question " + (questionCounter + 1) + ": ");
        System.out.print(quizzes.get(quizIndexes.get(questionCounter)).printQuizQuestion());

        // Create a Scanner object
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        userInput.replaceAll("\\s+", "");

        if (userInput.equals("")) {

            // Assert that the user's input is ""
            assert (userInput.equals(""));

            System.out.println("OOPS! Please enter your answer for the question above!\n");
        } else {

            boolean b = userInput.equals("1") || userInput.equals("2") || userInput.equals("3")
                    || userInput.equals("4");

            if (b) {

                // Assert that the user's input is one of the valid options
                assert (b) : "User's input should be one of the valid options (1, 2, 3 or 4)";

                // Store user's quiz answers into ArrayList
                userAnswerManager.getUserAnswers().add(Integer.parseInt(userInput));

                questionCounter++;
                return questionCounter;
            } else {
                userInterface.showToUser("OOPS! Incorrect answer format! "
                        + "Your answer can only be either 1, 2, 3 or 4!\n");
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


        userInterface.showToUser("Noted. I've removed this quiz question: \n" + quizzes.get(quizIndex - 1));

        quizzes.remove(quizIndex - 1);
        getQuizStatement();
    }

    //@@author AndreWongZH

    /**
     * Adds a quiz to the ArrayList of quizzes.
     * Extracts the question, options, explanations if any before adding it as a quiz.
     *
     * @param userInput The input entered by the user.
     * @throws EmptyParameterException If there are missing parameters after the prefix.
     */
    @Override
    public void add(String userInput) throws EmptyParameterException {
        if (!userInput.contains(" /q ")) {
            userInterface.showToUser("question not found");
            throw new EmptyParameterException();
        }
        if (!userInput.contains(" /a ")) {
            userInterface.showToUser("answer not found");
            throw new EmptyParameterException();
        }
        if (!userInput.contains(" /o1 ") && !userInput.contains(" /o2 ")
                && !userInput.contains(" /o3 ") && !userInput.contains(" /o4 ")) {
            userInterface.showToUser("options not provided");
            throw new EmptyParameterException();
        }
        String[] separatedInputs = userInput.trim().split("/");

        try {
            quizzes.add(parseQuizQuestion(separatedInputs));
            userInterface.showToUser("Quiz question added!\n");
        } catch (NumberFormatException e) {
            userInterface.showToUser("OOPS! Incorrect answer format! Your answer can only be either 1, 2, 3 or 4!\n");
            throw new EmptyParameterException();
        }
    }

    //@@author AndreWongZH
    //Refactored by durianpancakes
    private Quiz parseQuizQuestion(String[] separatedInputs) throws EmptyParameterException {
        String question = separatedInputs[1].substring(1).trim();
        String option1 = separatedInputs[2].substring(2).trim();
        String option2 = separatedInputs[3].substring(2).trim();
        String option3 = separatedInputs[4].substring(2).trim();
        String option4 = separatedInputs[5].substring(2).trim();
        String answer = separatedInputs[6].substring(1).trim();

        String explanation = "";

        if (separatedInputs.length > 7) {
            explanation = separatedInputs[7].substring(3).trim();
        }

        if (question.equals(" ") || option1.equals(" ") || option2.equals(" ")
                || option3.equals(" ") || option4.equals(" ") || answer.equals(" ")) {
            logger.log(Level.WARNING, "question or options or answer is empty");
            throw new EmptyParameterException();
        }

        int answerInInt = Integer.parseInt(answer);

        if (answerInInt > 4 || answerInInt < 1) {
            throw new NumberFormatException();
        }

        return new Quiz(question, option1, option2, option3, option4, answerInInt, explanation);
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
        if (lastIncorrectQuizzes.size() == 0) {
            userInterface.showToUser("Congratulations! You get full marks in your last attempt!");
        } else {
            userInterface.showToUser("Here are the incorrect quizzes in your last quiz attempt:\n");
            for (int i = 0; i < lastIncorrectQuizzes.size(); i++) {
                userInterface.showToUser(lastIncorrectQuizzes.get(i).printQuizQuestion());
                userInterface.showToUser("Your answer: (" + lastIncorrectAnswers.get(i) + ")");
                userInterface.showToUser("Correct answer: (" + lastIncorrectQuizzes.get(i).getAnswer() + ")\n");
                if (!lastIncorrectQuizzes.get(i).getExplanation().equals("")) {
                    userInterface.showToUser("Explanation: " + lastIncorrectQuizzes.get(i).getExplanation() + "\n");
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
            userInterface.showToUser("Quiz list is empty. Add some!\n");
        } else {
            userInterface.showToUser("Here are the questions in your quiz list:");
            for (int i = 0; i < quizzes.size(); i++) {
                userInterface.showToUser("Question " + (i + 1) + ":\n" + quizzes.get(i));
            }
        }
    }

    private void getQuizStatement() {
        String quizStatement = getQuizListSize() == 1 ? " quiz" : " quizzes";
        userInterface.showToUser("Now you have " + getQuizListSize() + quizStatement + " in the quiz list.");
    }
}
