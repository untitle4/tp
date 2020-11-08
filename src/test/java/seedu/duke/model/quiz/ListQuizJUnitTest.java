package seedu.duke.model.quiz;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.SwappedParameterException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListQuizJUnitTest {
    @Test
    void listQuizTest() throws EmptyParameterException, SwappedParameterException {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        QuizManager quizManager = new QuizManager(quizzes);
        quizManager.add("add quiz /q What is 1+1? /o1 1 /o2 2 "
                + "/o3 3 /o4 4 /a 2 /exp 1 plus 1 must be equal to 2!");

        String actualOutput = "Question " + (0 + 1) + ":\n" + quizzes.get(0);
        String expectedOutput = "Question 1:\n"
                + "What is 1+1?\n"
                + "\n"
                + "(1) 1\n"
                + "(2) 2\n"
                + "(3) 3\n"
                + "(4) 4\n"
                + "\n"
                + "Explanation: 1 plus 1 must be equal to 2!\n\n";
        assertEquals(actualOutput, expectedOutput);
    }
}
