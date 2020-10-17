package seedu.duke;

import seedu.duke.common.Messages;
import seedu.duke.ui.UserInterface;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogManager {
    private static final Logger LOGGER = Logger.getLogger("log");
    private UserInterface userInterface;

    public LogManager() {
        userInterface = UserInterface.getInstance();

        try {
            FileHandler fileHandler = new FileHandler("./logfile.txt");
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.setUseParentHandlers(false);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            userInterface.showToUser(Messages.MESSAGE_INITIALIZATION_LOGGER_FAILED);
        }
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
