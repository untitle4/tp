package seedu.duke.storage;

import seedu.duke.quiz.Quiz;

import java.io.FileWriter;
import java.io.IOException;

public class QuizStorageManager {
    private static final String FILE_PATH = "./data/quiz.txt";

    public void saveQuizData(Quiz quiz) {
        FileWriter fw;
        try {
            fw = new FileWriter(FILE_PATH);
            fw.write(quiz.convertToData());
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to save quiz to file :(");
        }
    }
}
