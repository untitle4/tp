package seedu.duke.storage;

import seedu.duke.exception.InvalidValueException;
import seedu.duke.quiz.Quiz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizStorageManager {
    private static String storage_directory_path;

    private QuizListEncoder quizListEncoder;
    private QuizListDecoder quizListDecoder;
    private ArrayList<Quiz> quizList;
    private static Logger logger = Logger.getLogger("quiz storage");

    public QuizStorageManager(String directoryPath, String filePath) throws InvalidValueException {
        this.quizListEncoder = new QuizListEncoder();
        this.quizListDecoder = new QuizListDecoder();
        this.quizList = new ArrayList<>();

        assert !directoryPath.equals("");
        assert !filePath.equals("");

        if (!isFilePathValid(filePath)) {
            throw new InvalidValueException();
        }

        storage_directory_path = new File(directoryPath).getAbsolutePath();

        initializeQuizStorageManager(filePath);
    }

    public ArrayList<Quiz> getQuizList() {
        return quizList;
    }

    private void initializeQuizStorageManager(String filePath) {
        File quizFile = new File(storage_directory_path + filePath);
        ArrayList<String> data = new ArrayList<>();
        logger.log(Level.INFO, "Loading storage...");

        try {
            boolean fileCreated = createDataFile(filePath);
            if (!fileCreated) {
                logger.log(Level.INFO, "Data file found, reading data file...");
                Scanner sc = new Scanner(quizFile);
                while (sc.hasNext()) {
                    String dataString = sc.nextLine();
                    data.add(dataString);
                }
                quizList = quizListDecoder.decodeQuizList(data);
                logger.log(Level.INFO, "Load successful");
            } else {
                logger.log(Level.INFO, "Data file not found, initializing data file...");
            }
        } catch (IOException e) {
            System.out.println("There was an error loading your files.");
            logger.log(Level.SEVERE, "Initialization failed");
        }
    }

    private boolean isFilePathValid(String filePath) {
        int filePathLength = filePath.length();
        if (!filePath.substring(filePathLength - 4).equals(".txt")) {
            return false;
        }
        return true;
    }

    private boolean createDataFile(String filePath) throws IOException {
        File file = new File(storage_directory_path);
        boolean isDirectoryCreated = file.mkdir();
        file = new File(storage_directory_path + filePath);

        if (isDirectoryCreated) {
            logger.log(Level.INFO, "Directory not found, creating...");
        } else {
            logger.log(Level.INFO, "Directory found...");
        }

        return file.createNewFile();
    }

    public void save(ArrayList<Quiz> quizList, String filePath) throws IOException {
        ArrayList<String> encodedQuizList = quizListEncoder.encodeQuizList(quizList);
        Files.write(Path.of(storage_directory_path + filePath), encodedQuizList);
    }
}
