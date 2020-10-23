package seedu.duke.model.quiz;

import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.exception.QuizParamException;
import seedu.duke.model.DataManager;
import seedu.duke.ui.UserInterface;
import seedu.duke.model.quiz.UserAnswerManager;
import seedu.duke.model.quiz.Quiz;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class QuizManager extends DataManager {
    public static final int EMPTY_SIZE = 0;
    public static final int USER_INPUT_OFFSET = 9;
    private final ArrayList<Quiz> quizzes;
    public static int noOfQues;

    private static final Logger logger = LogManager.getLoggerInstance().getLogger();
    private final UserInterface userInterface;

    public QuizManager(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
        this.userInterface = UserInterface.getInstance();
    }

    public ArrayList<Quiz> getQuizList() {
        return quizzes;
    }

    UserAnswerManager userAnswerManager = new UserAnswerManager();

    public int getQuizListSize() {
        assert quizzes != null;
        return quizzes.size();
    }

    //@@author elizabethcwt
    public void takeQuiz(String[] separatedInputs) {
        try {
            noOfQues = Integer.parseInt(separatedInputs[1]);

            if (!((noOfQues == 10) || (noOfQues == 20) || (noOfQues == 30))) {
                // If user inputs an invalid number of quiz questions (not 10, 20 or 30)
                userInterface.showToUser(Messages.MESSAGE_INVALID_NUM_OF_QUIZ_QUESTIONS);

                // If user inputs a valid number of quiz questions (either 10, 20 or 30)
            } else if ((noOfQues > getQuizListSize()) && (getQuizListSize() < 10)) {
                // If user wants to try more questions than he/she has in the current quiz, and has less than 10
                // questions
                userInterface.showToUser(String.format(Messages.MESSAGE_INSUFFICIENT_QUES_LESS_THAN_10,
                        getQuizListSize()));
            } else if (noOfQues > getQuizListSize() && (getQuizListSize() >= 10)) {
                // If user wants to try more questions than he/she has in the current quiz, but has at least 10
                // questions
                userInterface.showToUser(String.format(Messages.MESSAGE_INSUFFICIENT_QUES_MORE_THAN_10,
                        getQuizListSize()));
            } else {
                // If user enters a valid number of questions
                if (noOfQues <= getQuizListSize()) {

                    // Create a new list of the question indexes
                    List<Integer> quizIndexes = new ArrayList<>();
                    for (int i = 0; i < quizzes.size(); i++) {
                        quizIndexes.add(i);
                    }

                    // Shuffle the question indexes
                    Collections.shuffle(quizIndexes);

                    // Print out each question
                    for (int j = 0; j < noOfQues; j++) {
                        System.out.println();
                        System.out.println("Question " + (j + 1) + ": ");
                        System.out.println(quizzes.get(quizIndexes.get(j)).printQuizQuestion());

                        // Create a Scanner object
                        Scanner in = new Scanner(System.in);

                        // Store user's quiz answers into ArrayList
                        //TODO handle invalid answer input
                        userAnswerManager.getUserAnswers().add(Integer.parseInt(in.nextLine()));
                    }

                    // Initialising counter for correctly answered questions
                    int correctCounter = 0;
                    // Compare and note if students' answers are correct
                    for (int k = 0; k < noOfQues; k++) {
                        if (userAnswerManager.getUserAnswers().get(k).equals(Integer.parseInt(quizzes.get(quizIndexes
                                .get(k)).getAnswer()))) {
                            userAnswerManager.getCorrectness().add(true);
                            correctCounter++;
                        } else {
                            userAnswerManager.getCorrectness().add(false);
                        }
                    }

                    // TODO Print results, questions, user's answers, correct answers and explanations
                    System.out.println("\nYou scored " + correctCounter + " out of " + noOfQues + "!\n");


                    // Empty userAnswers ArrayList and correctness ArrayList
                    userAnswerManager.getUserAnswers().clear();
                    userAnswerManager.getCorrectness().clear();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_HELP_COMMAND);
        }
    }

    @Override
    public void delete(String[] userInputs) throws IndexOutOfBoundsException {
        int quizIndex;

        try {
            quizIndex = Integer.parseInt(userInputs[2]);
        } catch (NumberFormatException e) {
            userInterface.showToUser(Messages.MESSAGE_QUIZ_DELETE_ERROR_NON_NUMBER);
            return;
        } catch (IndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_QUIZ_DELETE_ERROR_NON_NUMBER);
            return;
        }

        if ((quizIndex <= 0) || (quizIndex > getQuizListSize())) {
            throw new IndexOutOfBoundsException();
        }

        userInterface.showToUser("Noted. I've removed this quiz question: \n" + quizzes.get(quizIndex - 1));

        quizzes.remove(quizIndex - 1);
        getQuizStatement();
    }

    // format: add quiz /q question /o1 option 1 /o2 option 2 /o3 option 3 /o4 option 4 /a answer /exp explanation
    @Override
    public void add(String userInput) throws QuizParamException, EmptyParameterException {
        if (!userInput.contains(" /q ")) {
            userInterface.showToUser("question not found");
            throw new QuizParamException();
        }
        if (!userInput.contains(" /a ")) {
            userInterface.showToUser("answer not found");
            throw new QuizParamException();
        }
        if (!userInput.contains(" /o1 ") && !userInput.contains(" /o2 ")
                && !userInput.contains(" /o3 ") && !userInput.contains(" /o4 ")) {
            userInterface.showToUser("options not provided");
            throw new QuizParamException();
        }
        String[] separatedInputs = userInput.trim().split("/");

        String question = separatedInputs[1].substring(1).trim();
        String option1 = separatedInputs[2].substring(2).trim();
        String option2 = separatedInputs[3].substring(2).trim();
        String option3 = separatedInputs[4].substring(2).trim();
        String option4 = separatedInputs[5].substring(2).trim();
        String answer = separatedInputs[6].substring(1).trim();

        if (question.equals(" ") || option1.equals(" ") || option2.equals(" ")
                || option3.equals(" ") || option4.equals(" ") || answer.equals(" ")) {
            logger.log(Level.WARNING, "question or options or answer is empty");
            throw new EmptyParameterException();
        }

        if (separatedInputs.length > 7) {
            String explanation = separatedInputs[7].substring(3).trim();
            quizzes.add(new Quiz(question, option1, option2, option3, option4, answer, explanation));
        } else {
            quizzes.add(new Quiz(question, option1, option2, option3, option4, answer));
        }

        userInterface.showToUser("Quiz question added!\n");
    }

    @Override
    public void find(String userInput) throws MissingParameterException {
        String param = userInput.substring(USER_INPUT_OFFSET).trim();

        if (param.length() == EMPTY_SIZE) {
            throw new MissingParameterException();
        }

        FindQuiz findQuiz = new FindQuiz(param, quizzes);
        ArrayList<String> filteredQuizzes = findQuiz.filterQuizzes();

        if (filteredQuizzes.size() == EMPTY_SIZE) {
            userInterface.showToUser(Messages.MESSAGE_NO_QUIZZES_FOUND);
            return;
        }

        userInterface.printArray(filteredQuizzes);
    }

    @Override
    public void list() {
        if (quizzes.size() == EMPTY_SIZE) {
            userInterface.showToUser("Quiz list is empty. Add some!\n");
        } else {
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
