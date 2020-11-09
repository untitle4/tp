package seedu.duke.storage.quiz;

import seedu.duke.exception.StorageCorruptedException;
import seedu.duke.model.quiz.Quiz;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;

//@@author untitle4
/**
 * To decode the content in the quizzes text file and add them in an Arraylist as quizzes
 * to initialize the quiz list.
 */
public class QuizListDecoder {
    public static final int NUM_OF_DAYS = -2;

    //@@author AndreWongZH
    /**
     * Reads and extracts out the information from quiz storage.
     *
     * @param encodedQuizList An array list of quizzes in string.
     * @return An arraylist of type quiz stored in the text file.
     * @throws StorageCorruptedException If data parsed is not what is expected.
     */
    public ArrayList<Quiz> decodeQuizList(ArrayList<String> encodedQuizList) throws StorageCorruptedException {
        final ArrayList<Quiz> decodedQuizzes = new ArrayList<>();
        for (String encodedQuiz : encodedQuizList) {
            decodedQuizzes.add(decodeQuizFromString(encodedQuiz));
        }

        return decodedQuizzes;
    }

    //@@author untitle4
    private Quiz decodeQuizFromString(String encodedQuiz) throws StorageCorruptedException {
        final String[] data = encodedQuiz.trim().split("\\|");

        try {
            String question = data[0];
            String option1 = data[1];
            String option2 = data[2];
            String option3 = data[3];
            String option4 = data[4];
            int answer = Integer.parseInt(data[5]);
            String explanation = data[6];
            LocalDate lastAccessed = LocalDate.parse(data[7]);
            return new Quiz(question, option1, option2, option3, option4, answer, explanation, lastAccessed);
        } catch (Exception e) {
            throw new StorageCorruptedException();
        }
    }
}
