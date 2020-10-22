package seedu.duke.common;

import seedu.duke.ui.UserInterface;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Represents the objects that logs all the processes of the program at each step.
 */
public class LogManager {
    public static final String LOG_FILE_LOCATION = "./logfile.txt";
    private static LogManager logManager = null;
    private static final Logger LOGGER = Logger.getLogger("log");

    /**
     * The constructor here is only called once when program runs to initialize a new LogManager instance.
     */
    private LogManager() {
        UserInterface userInterface = UserInterface.getInstance();

        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE_LOCATION);
            fileHandler.setFormatter(new SimpleFormatter());

            // set log level, defaults to Level.INFO
            LOGGER.setLevel(Level.INFO);

            // prevents printing of log messages to console
            LOGGER.setUseParentHandlers(false);

            // redirects log messages to a file instead
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            userInterface.showToUser(Messages.MESSAGE_INITIALIZATION_LOGGER_FAILED);
        }
    }

    /**
     * Returns an instance of the LogManager class for users to get the logger instance.
     *
     * @return LogManager instance.
     */
    public static LogManager getLogManagerInstance() {
        if (logManager == null) {
            logManager = new LogManager();
        }
        return logManager;
    }

    /**
     * Returns a logger instance that the programmer can use to log process info.
     *
     * @return A logger instance.
     */
    public Logger getLogger() {
        return LOGGER;
    }
}
