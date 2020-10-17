package seedu.duke.quiz;

import seedu.duke.LogManager;

import java.util.ArrayList;
import java.util.logging.Logger;

public class QuizManager {
    private final ArrayList<Quiz> quizzes;
    private static final Logger logger = LogManager.getLogger();

    public QuizManager(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public ArrayList<Quiz> getQuizList() {
        return quizzes;
    }

    public int getQuizListSize() {
        assert quizzes != null;
        return quizzes.size();
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
        System.out.println(quizzes.get(quizIndex - 1));

        quizzes.remove(quizIndex - 1);
        getQuizStatement();
    }

    // format: add quiz /q question /o1 option 1 /o2 option 2 /o3 option 3 /o4 option 4 /a answer /exp explanation
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

        quizzes.add(new Quiz(question, option1, option2, option3, option4, answer));
        System.out.println("Quiz question added!");
    }

    public void listQuiz() {
        if (quizzes.size() == 0) {
            System.out.println("Quiz list is empty. Add some!");
        } else {
            for (int i = 0; i < quizzes.size(); i++) {
                System.out.println("Question " + (i + 1) + ":");
                System.out.println(quizzes.get(i));
            }
        }
    }

    private void getQuizStatement() {
        String quizStatement = getQuizListSize() == 1 ? " quiz" : " quizzes";
        System.out.println("Now you have " + getQuizListSize() + quizStatement + " in the quiz list.");
    }
}
