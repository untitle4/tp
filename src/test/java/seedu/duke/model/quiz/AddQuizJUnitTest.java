package seedu.duke.model.quiz;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyParameterException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddQuizJUnitTest {
    @Test
    void addQuizToList() throws EmptyParameterException {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        QuizManager quizManager = new QuizManager(quizzes);
        quizManager.add("add quiz /q What is 1+1? /o1 1 /o2 2 "
                + "/o3 3 /o4 4 /a 2 /exp 1 plus 1 must be equal to 2!");

        int actualQuizNumber = quizManager.getQuizListSize();
        int expectedQuizNumber = 1;
        assertEquals(actualQuizNumber, expectedQuizNumber);

        String actualQuizExplanation = "1 plus 1 must be equal to 2!";
        String expectedQuizExplanation = quizManager.getQuizList().get(0).getExplanation();
        assertEquals(actualQuizExplanation, expectedQuizExplanation);
    }

    //@@author durianpancakes
    @Test
    void parseQuiz_invalidAnswerIndex_exceptionThrown() {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        QuizManager quizManager = new QuizManager(quizzes);

        assertThrows(EmptyParameterException.class, () -> quizManager.add("add quiz /q What is 1+1? /o1 1 /o2 2 "
                + "/o3 3 /o4 4 /a 6 /exp 1 plus 1 must be equal to 2!"));
    }
}
