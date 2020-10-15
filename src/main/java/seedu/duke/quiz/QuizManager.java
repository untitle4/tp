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
        int quizIndex;

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

    // format: add quiz /q question /o1 option1 /o2 option2 /o3 option3 /o4 options4 /a answer
    public void addQuiz(String userInput) {
        if (!userInput.contains(" /q ")) {
            System.out.println("question not found");
        }
        if (!userInput.contains(" /a ")) {
            System.out.println("answer not found");
        }
        if (!userInput.contains(" /o1 ") && !userInput.contains(" /o2 ")
                && !userInput.contains(" /o3 ") && !userInput.contains(" /o4 ")) {
            System.out.println("options not provided");
        }
        String[] separatedInputs = userInput.split(" ");

        String question = separatedInputs[3];
        String option1 = separatedInputs[5];
        String option2 = separatedInputs[7];
        String option3 = separatedInputs[9];
        String option4 = separatedInputs[11];
        String answer = separatedInputs[13];

        Quiz quiz = new Quiz(question, option1, option2, option3, option4, answer);
        new QuizStorageManager().saveQuizData(quiz);

        System.out.println("Quiz question added!");
    }

    private void getQuizStatement() {
        String quizStatement = getQuizListSize() == 1 ? " quiz" : " quizzes";
        System.out.println("Now you have " + getQuizListSize() + quizStatement + " in the quiz list.");
    }
}
