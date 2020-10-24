package seedu.duke.storage;

import seedu.duke.model.quiz.Quiz;

import java.util.ArrayList;

public class QuizListDecoder {
    public ArrayList<Quiz> decodeQuizList(ArrayList<String> encodedQuizList) {
        final ArrayList<Quiz> decodedQuizzes = new ArrayList<>();
        for (String encodedQuiz : encodedQuizList) {
            decodedQuizzes.add(decodeQuizFromString(encodedQuiz));
        }

        return decodedQuizzes;
    }

    private Quiz decodeQuizFromString(String encodedQuiz) {
        final String[] data = encodedQuiz.trim().split("\\|");

        String question = data[0];
        String option1 = data[1];
        String option2 = data[2];
        String option3 = data[3];
        String option4 = data[4];
        String answer = data[5];

        return new Quiz(question, option1, option2, option3, option4, answer);
    }
}
