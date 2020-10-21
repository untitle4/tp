package seedu.duke.model.quiz;

import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.QuizParamException;
import seedu.duke.model.DataManager;
import seedu.duke.ui.UserInterface;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizManager extends DataManager {
    private final ArrayList<Quiz> quizzes;
    private static final Logger logger = LogManager.getLoggerInstance().getLogger();
    private final UserInterface userInterface;

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

        userInterface.showToUser("Noted. I've removed this quiz: \n" + quizzes.get(quizIndex - 1));

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

        userInterface.showToUser("Quiz question added!");
    }

    @Override
    public void list() {
        if (quizzes.size() == 0) {
            userInterface.showToUser("Quiz list is empty. Add some!");
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
