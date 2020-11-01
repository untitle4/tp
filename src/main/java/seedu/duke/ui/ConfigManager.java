package seedu.duke.ui;

import seedu.duke.common.Messages;
import seedu.duke.exception.StorageCorruptedException;
import seedu.duke.model.ConfigParameter;
import seedu.duke.model.ModelMain;
import seedu.duke.storage.ConfigStorageManager;

import java.io.IOException;

public class ConfigManager extends ModelMain {
    public static final String CONFIG_FILE_NAME = "/config.txt";
    private final ConfigStorageManager configStorageManager;
    private static UserInterface userInterface;
    private ConfigParameter configParameter;
    private static ConfigManager INSTANCE = null;

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

    private ConfigParameter getConfig() {
        ConfigParameter configParameter = null;
        try {
            configParameter = configStorageManager.loadData();
        } catch (StorageCorruptedException e) {
            e.printStackTrace();
        }
        return configParameter;
    }

    public void getIntroductoryVariables(ConfigParameter configParameter) {
        if (configParameter.getHasProgramRan() == false) {
            userInterface.showToUser(Messages.MESSAGE_PROMPT_NAME);
            String userName = userInterface.getUserCommand();
            configParameter.setName(userName);
            userInterface.showToUser(Messages.MESSAGE_HELLO + userName, "");
            int recommendedHours = 0;
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

    private void saveConfigParameter(ConfigParameter configParameter) {
        try {
            configStorageManager.saveData(configParameter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editHours() {
        int newHours;
        newHours = getInputHours();
        userInterface.showToUser(Messages.MESSAGE_SHOW_NEW_HOURS + newHours);
        configParameter.setRecommendedHours(newHours);
        saveConfigParameter(configParameter);
    }
}
