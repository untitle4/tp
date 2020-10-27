package seedu.duke.storage;

import seedu.duke.model.ConfigParameter;

import java.util.ArrayList;

public class ConfigDecoder {
        public ConfigParameter decodeConfig(String encodedConfig) {
            String[] splitEncodedConfig = encodedConfig.split("\\|", 3);
            String userName = splitEncodedConfig[0];
            int recommendedHours = Integer.parseInt(splitEncodedConfig[1]);
            boolean hasProgramRan = Boolean.parseBoolean(splitEncodedConfig[2]);

            return new ConfigParameter(userName, recommendedHours, hasProgramRan);
        }
}
