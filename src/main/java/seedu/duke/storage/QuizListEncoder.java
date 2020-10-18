package seedu.duke.storage;

import seedu.duke.model.quiz.Quiz;

import java.util.ArrayList;

public class QuizListEncoder {

    public ArrayList<String> encodeQuizList(ArrayList<Quiz> quizList) {
        ArrayList<String> encodedQuizzes = new ArrayList<>();

        for (Quiz quiz: quizList) {
            encodedQuizzes.add(quiz.convertToData());
        }

        return encodedQuizzes;
    }
}
