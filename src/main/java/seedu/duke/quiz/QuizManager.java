package seedu.duke.quiz;

import seedu.duke.storage.QuizStorageManager;

import java.util.ArrayList;
import java.util.logging.Logger;

public class QuizManager {
    private final ArrayList<Quiz> quiz;
    private static final Logger logger = Logger.getLogger("QUIZ");

    public QuizManager(QuizStorageManager quizStorageManager) {
        quiz = quizStorageManager.getQuizList();
    }

    public ArrayList<Quiz> getQuizList() {
        return quiz;
    }

    public int getQuizListSize() {
        assert quiz != null;
        return quiz.size();
    }

    public void deleteQuiz(String[] userInput) throws IndexOutOfBoundsException {
        int quizIndex = 0;

        try {
            quizIndex = Integer.parseInt(userInput[2]);
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please indicate in NUMERALS, which cca you'd like to delete!");
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please indicate which cca you'd like to delete!");
            return;
        }

        if ((quizIndex <= 0) || (quizIndex > getQuizListSize())) {
            throw new IndexOutOfBoundsException();
        }

        System.out.println("Noted. I've removed this quiz: ");
        System.out.println(quiz.get(quizIndex - 1));

        quiz.remove(quizIndex - 1);
        getQuizStatement();
    }

    private void getQuizStatement() {
        String quizStatement = getQuizListSize() == 1 ? " quiz" : " quizzes";
        System.out.println("Now you have " + getQuizListSize() + quizStatement + " in the quiz list.");
    }
}
