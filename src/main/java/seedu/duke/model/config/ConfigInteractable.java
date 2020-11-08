package seedu.duke.model.config;

import seedu.duke.model.ConfigParameter;

//@@author AndreWongZH
/**
 * Represents the public api methods for ConfigManager that the controller can call.
 */
public interface ConfigInteractable {
    /**
     * Changes the recommended hours a day.
     */
    void editHours();

    void getIntroductoryVariables(ConfigParameter configParameter);
}
