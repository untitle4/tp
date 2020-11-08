package seedu.duke.ui;

import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.model.ConfigParameter;
import seedu.duke.model.ModelMain;
import seedu.duke.storage.config.ConfigStorageManager;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigManager extends ModelMain {
    public static final String CONFIG_FILE_NAME = "/config.txt";
    private final ConfigStorageManager configStorageManager;
    private static UserInterface userInterface;
    private final ConfigParameter configParameter;
    private static ConfigManager INSTANCE = null;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();

    public static ConfigManager getInstance()  {
        if (INSTANCE == null) {
            INSTANCE = new ConfigManager();
        }
        return INSTANCE;
    }

    private ConfigManager() {
        userInterface = UserInterface.getInstance();
        this.configStorageManager = new ConfigStorageManager(CONFIG_FILE_NAME);
        this.configParameter = getConfig();
    }

    public ConfigParameter getConfigParameter() {
        return configParameter;
    }


    /**
     * Load data from configStorageManager to configParam.
     *
     * @return configParam containing data from configStorageManager
     */
    private ConfigParameter getConfig() {
        ConfigParameter configParameter = null;
        configParameter = configStorageManager.loadData();
        return configParameter;
    }

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

    private int getInputHours() {
        int recommendedHours = 0;
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

    public void editHours() {
        logger.log(Level.INFO, "editing hours to take in new hours input");
        int newHours;
        newHours = getInputHours();
        userInterface.showToUser(Messages.MESSAGE_SHOW_NEW_HOURS + newHours);
        configParameter.setRecommendedHours(newHours);
        saveConfigParameter(configParameter);
    }
}
