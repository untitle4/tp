package seedu.duke.storage;

import seedu.duke.model.ConfigParameter;

import java.util.ArrayList;

public class ConfigDecoder {
    public ConfigParameter decodeConfig(ArrayList<String> encodedConfig) {
        ConfigParameter configParameter = new ConfigParameter();

        for (String encodedString : encodedConfig) {
            switch (encodedString) {
            case "userName":
                configParameter.setName(encodedString);
                break;
            case "recommendedHours":
                configParameter.setRecommendedHours(Integer.parseInt(encodedString));
                break;
            default:
                break;
            }
        }

        return configParameter;
    }
}
