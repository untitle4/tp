package seedu.duke;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogManager {
    private static final Logger LOGGER = Logger.getLogger("log");

    public LogManager() {
        try {
            FileHandler fileHandler = new FileHandler("./logfile.txt");
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.setUseParentHandlers(false);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            System.out.println("Failed to set up logger");
        }
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
