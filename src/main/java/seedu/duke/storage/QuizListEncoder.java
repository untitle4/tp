package seedu.duke.storage;

import seedu.duke.model.quiz.Quiz;

import java.util.ArrayList;

//@@author untitle4
/**
 * To encode the String form of quizzes in the Arraylist and store them in a text file.
 */
public class QuizListEncoder {

    public ArrayList<String> encodeQuizList(ArrayList<Quiz> quizList) {
        ArrayList<String> encodedQuizzes = new ArrayList<>();

        for (Quiz quiz: quizList) {
            encodedQuizzes.add(quiz.convertToData());
        }

        return encodedQuizzes;
    }
}
