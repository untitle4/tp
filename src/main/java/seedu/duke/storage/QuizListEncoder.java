package seedu.duke.storage;

import seedu.duke.quiz.Quiz;

import java.util.ArrayList;

public class QuizListEncoder {

    public ArrayList<String> encodeQuizList(ArrayList<Quiz> quizList) {
        ArrayList<String> encodedQuizs = new ArrayList<>();

        for (Quiz quiz: quizList) {
            encodedQuizs.add(quiz.convertToData());
        }

        return encodedQuizs;
    }
}
