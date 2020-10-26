package seedu.duke.storage;

import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.ui.UserInterface;
import seedu.duke.model.quiz.Quiz;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author untitle4
/**
 * Help manage the storage of quizzes into a text file and load quizzes from the file
 * when the program is executed.
 */
public class QuizStorageManager extends StorageManager {
    private final QuizListEncoder quizListEncoder;
    private final QuizListDecoder quizListDecoder;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();
    private final UserInterface userInterface;

    public QuizStorageManager(String fileName) {
        super(fileName);
        this.quizListEncoder = new QuizListEncoder();
        this.quizListDecoder = new QuizListDecoder();
        this.userInterface = UserInterface.getInstance();
    }

    /**
     * Load the content of the quiz text file
     * @return
     */
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
            userInterface.showToUser(Messages.MESSAGE_STORAGE_READ_ERROR);
            logger.log(Level.SEVERE, "Initialization failed");
        }
        return new ArrayList<>();
    }

    public void saveData(ArrayList<Quiz> quizList, String filePath) throws IOException {
        ArrayList<String> encodedQuizList = quizListEncoder.encodeQuizList(quizList);
        Files.write(Path.of(DIRECTORY_FOLDER_PATH + filePath), encodedQuizList);
    }
}
