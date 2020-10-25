package seedu.duke.model.quiz;

import seedu.duke.common.LogManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author untitle4
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

        for (int i = 0; i < quizzes.size(); i++) {
            for (String keyword: seperatedInputs) {
                if (quizzes.get(i).getQuestion().toLowerCase().contains(keyword.toLowerCase())) {

                    filteredQuizzes.add("Question " + (i + 1) + ":\n" + quizzes.get(i).toString());
                    break;
                }
            }
        }
        return filteredQuizzes;
    }

}