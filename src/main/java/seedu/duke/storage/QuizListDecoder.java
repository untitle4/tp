package seedu.duke.storage;

import dorkbox.notify.Notify;
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

    public ArrayList<Quiz> decodeQuizList(ArrayList<String> encodedQuizList) {
        final ArrayList<Quiz> decodedQuizzes = new ArrayList<>();
        for (String encodedQuiz : encodedQuizList) {
            decodedQuizzes.add(decodeQuizFromString(encodedQuiz));
        }

        for (Quiz quiz : decodedQuizzes) {
            long numDays = DAYS.between(LocalDate.now(), quiz.getLastAccessed());
            if (numDays <= -2) {
                Notify.create()
                        .title("Plan&score Notification")
                        .text("you have outdated quizzes! Attempt them now!")
                        .hideAfter(10000)
                        .showInformation();
                break;
            }
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
        int answer = Integer.parseInt(data[5]);
        String explanation = data[6];
        LocalDate lastAccessed = LocalDate.parse(data[7]);

        return new Quiz(question, option1, option2, option3, option4, answer, explanation, lastAccessed);
    }
}
