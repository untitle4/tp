package seedu.duke.storage;

import seedu.duke.quiz.Quiz;

import java.util.ArrayList;

public class QuizListEncoder {

    public ArrayList<String> encodeQuizList(ArrayList<Quiz> quizList) {
        ArrayList<String> encodedQuizs = new ArrayList<>();

        for (Quiz quiz: quizList) {
            encodedQuizs.add(encodeQuizToString(quiz));
        }

        return encodedQuizs;
    }

    private String encodeQuizToString(Quiz quiz) {
        String result = "";
        assertValidQuiz(quiz.getQuestion(), quiz.getOption1(), quiz.getOption2(),
                quiz.getOption3(), quiz.getOption4(), quiz.getAnswer());
        result = quiz.getQuestion() + "|"
                + quiz.getOption1() + "|"
                + quiz.getOption2() + "|"
                + quiz.getOption3() + "|"
                + quiz.getOption4() + "|"
                + quiz.getAnswer();

        return result;
    }

    private void assertValidQuiz(String description, String option1,
                                 String option2, String option3,
                                 String option4, String answer) {
        assert description != null;
        assert !description.equals("");
        assert option1 != null;
        assert !option1.equals("");
        assert option2 != null;
        assert !option2.equals("");
        assert option3 != null;
        assert !option3.equals("");
        assert option4 != null;
        assert !option4.equals("");
        assert answer != null;
        assert !answer.equals("");
    }
}
