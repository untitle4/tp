package seedu.duke.storage;

import seedu.duke.model.ConfigParameter;

public class ConfigEncoder {
    public String encodeConfig(ConfigParameter configParameter) {
        String userName = configParameter.getName();
        String recommendedHours = String.valueOf(configParameter.getRecommendedHours());

        return "userName: " + userName + "/n" + "recommendedHours: " + recommendedHours;
    }
}
