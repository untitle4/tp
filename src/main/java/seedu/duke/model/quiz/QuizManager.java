package seedu.duke.model.quiz;

import seedu.duke.common.LogManager;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.QuizParamException;
import seedu.duke.model.DataManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizManager extends DataManager {
    private final ArrayList<Quiz> quizzes;
    private static final Logger logger = LogManager.getLoggerInstance().getLogger();

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

    @Override
    public void delete(String[] userInputs) throws IndexOutOfBoundsException {
        int quizIndex;

        try {
            quizIndex = Integer.parseInt(userInputs[2]);
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
    @Override
    public void add(String userInput) throws QuizParamException, EmptyParameterException {
        if (!userInput.contains(" /q ")) {
            System.out.println("question not found");
            throw new QuizParamException();
        }
        if (!userInput.contains(" /a ")) {
            System.out.println("answer not found");
            throw new QuizParamException();
        }
        if (!userInput.contains(" /o1 ") && !userInput.contains(" /o2 ")
                && !userInput.contains(" /o3 ") && !userInput.contains(" /o4 ")) {
            System.out.println("options not provided");
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

        System.out.println("Quiz question added!");
    }

    @Override
    public void list() {
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
