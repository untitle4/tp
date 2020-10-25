package seedu.duke.model.quiz;

import seedu.duke.common.LogManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FindQuiz {
    private final ArrayList<Quiz> quizzes;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();
    private final String userInput;
    private final ArrayList<String> filteredQuizzes;

    public FindQuiz(String userInput, ArrayList<Quiz> quizzes) {
        assert userInput.length() != 0;
        this.quizzes = quizzes;
        this.userInput = userInput;
        filteredQuizzes = new ArrayList<>();
    }

    public ArrayList<String> filterQuizzes() {
        logger.log(Level.INFO, "loop through all the keywords to check if in quiz description");
        String[] seperatedInputs = userInput.split(" ");

        for (Quiz quiz : quizzes) {
            for (String keyword: seperatedInputs) {
                if (quiz.getQuestion().toLowerCase().contains(keyword.toLowerCase())) {
                    filteredQuizzes.add(quiz.toString());
                    break;
                }
            }
        }
        return filteredQuizzes;
    }

}