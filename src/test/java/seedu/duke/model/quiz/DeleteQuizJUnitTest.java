package seedu.duke.model.quiz;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyParameterException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteQuizJUnitTest {
    @Test
    void deleteQuizToList() throws EmptyParameterException {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        QuizManager quizManager = new QuizManager(quizzes);
        String userInput = "delete quiz 1";
        String[] separatedInputs = userInput.split(" ");
        quizManager.add("add quiz /q What is 1+1? /o1 1 /o2 2 "
                + "/o3 3 /o4 4 /a 2 /exp 1 plus 1 must be equal to 2!");
        quizManager.delete(separatedInputs);

        int actualQuizNumber = quizManager.getQuizListSize();
        int expectedQuizNumber = 0;
        assertEquals(actualQuizNumber, expectedQuizNumber);

    }
}
