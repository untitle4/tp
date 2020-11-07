package seedu.duke.ui;

import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.model.ConfigParameter;
import seedu.duke.model.ModelMain;
import seedu.duke.storage.config.ConfigStorageManager;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a handler that manages the recommended hours features.
 */
public class ConfigManager extends ModelMain {
    public static final String CONFIG_FILE_NAME = "/config.txt";
    private final ConfigStorageManager configStorageManager;
    private static UserInterface userInterface;
    private final ConfigParameter configParameter;
    private static ConfigManager INSTANCE = null;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();

    /**
     * Get the instance of ConfigManager.
     *
     * @return INSTANCE instance of ConfigManager
     */
    public static ConfigManager getInstance()  {
        if (INSTANCE == null) {
            INSTANCE = new ConfigManager();
        }
        return INSTANCE;
    }

    /**
     * Constructor for ConfigManager.
     */
    private ConfigManager() {
        userInterface = UserInterface.getInstance();
        this.configStorageManager = new ConfigStorageManager(CONFIG_FILE_NAME);
        this.configParameter = getConfig();
    }

    /**
     * To get the configParameter from the ConfigParameter Class.
     *
     * @return configParameter
     */
    public ConfigParameter getConfigParameter() {
        return configParameter;
    }

    /**
     * Load date from configStorageManager to configParam.
     *
     * @return configParam containing data from configStorageManager
     */
    private ConfigParameter getConfig() {
        ConfigParameter configParameter = null;
        assert configParameter == null;
        configParameter = configStorageManager.loadData();
        return configParameter;
    }

    /**
     * Get the username and recommended hours from user.
     *
     * @param configParameter configParameter class
     */
    public void getIntroductoryVariables(ConfigParameter configParameter) {
        logger.log(Level.INFO, "getting username and recommended hours from user");
        if (!configParameter.getHasProgramRan()) {
            userInterface.showToUser(Messages.MESSAGE_PROMPT_NAME);
            String userName = userInterface.getUserCommand();
            configParameter.setName(userName);
            int recommendedHours;
            recommendedHours = getInputHours();
            configParameter.setRecommendedHours(recommendedHours);
            configParameter.setHasProgramRan(true);
        }
        saveConfigParameter(configParameter);
    }

    /**
     * Prompt the user to key in the number of recommended hours.
     *
     * @return recommendedHours hours that the user has inputted which is not less than 0 or more than 12
     * @exception NumberFormatException exception thrown when non-integer is inputted
     */
    private int getInputHours() {
        int recommendedHours = 0;
        assert recommendedHours == 0;
        do {
            try {
                userInterface.showToUser(Messages.MESSAGE_PROMPT_HOURS);
                recommendedHours = Integer.parseInt(userInterface.getUserCommand());
            } catch (NumberFormatException e) {
                userInterface.showToUser(Messages.MESSAGE_HOURS_ERROR_NON_NUMBER);
            }
        } while (recommendedHours <= 0 || recommendedHours > 12);
        return recommendedHours;
    }

    /**
     * Save the new ConfigParam into ConfigStorageManager.
     *
     * @param configParameter configParam storing the new inputs
     */
    private void saveConfigParameter(ConfigParameter configParameter) {
        try {
            configStorageManager.saveData(configParameter);
        } catch (IOException e) {
            userInterface.showToUser(Messages.MESSAGE_STORAGE_INITIALIZATION_ERROR);
        }
    }

    /**
     * Prompt and edit hours according to user's new recommended hours.
     */
    public void editHours() {
        logger.log(Level.INFO, "editing hours to take in new hours input");
        int newHours;
        newHours = getInputHours();
        userInterface.showToUser(Messages.MESSAGE_SHOW_NEW_HOURS + newHours);
        configParameter.setRecommendedHours(newHours);
        saveConfigParameter(configParameter);
    }
}
