package seedu.duke.storage;

import seedu.duke.quiz.Quiz;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizStorageManager extends StorageManager {
    private final QuizListEncoder quizListEncoder;
    private final QuizListDecoder quizListDecoder;
    private static final Logger logger = Logger.getLogger("storage");

    public QuizStorageManager(String fileName) {
        super(fileName);
        this.quizListEncoder = new QuizListEncoder();
        this.quizListDecoder = new QuizListDecoder();
        try {
            FileHandler fileHandler = new FileHandler("./data/debog.txt");
            logger.setUseParentHandlers(false);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Quiz> loadData() {
        File quizFile = new File(DIRECTORY_FOLDER_PATH + fileName);
        ArrayList<String> data = new ArrayList<>();
        logger.log(Level.INFO, "Loading storage...");

        try {
            boolean fileCreated = createDataFile();
            if (!fileCreated) {
                logger.log(Level.INFO, "Data file found, reading data file...");
                Scanner sc = new Scanner(quizFile);
                while (sc.hasNext()) {
                    String dataString = sc.nextLine();
                    data.add(dataString);
                }
                logger.log(Level.INFO, "Load successful");
                return quizListDecoder.decodeQuizList(data);
            } else {
                logger.log(Level.INFO, "Data file not found, initializing data file...");
            }
        } catch (IOException e) {
            System.out.println("There was an error loading your files.");
            logger.log(Level.SEVERE, "Initialization failed");
        }
        return new ArrayList<>();
    }

    public void saveData(ArrayList<Quiz> quizList, String filePath) throws IOException {
        ArrayList<String> encodedQuizList = quizListEncoder.encodeQuizList(quizList);
        Files.write(Path.of(DIRECTORY_FOLDER_PATH + filePath), encodedQuizList);
    }
}
