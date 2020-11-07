package seedu.duke.storage.config;

import seedu.duke.model.ConfigParameter;

public class ConfigEncoder {
    public String encodeConfig(ConfigParameter configParameter) {
        String userName = configParameter.getName();
        String recommendedHours = String.valueOf(configParameter.getRecommendedHours());
        String hasProgramRan = String.valueOf(configParameter.getHasProgramRan());

        return userName + "|" + recommendedHours + "|" + hasProgramRan;
    }
}
