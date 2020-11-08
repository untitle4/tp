package seedu.duke.storage.config;

import seedu.duke.exception.StorageCorruptedException;
import seedu.duke.model.ConfigParameter;

public class ConfigDecoder {
    public ConfigParameter decodeConfig(String encodedConfig) throws StorageCorruptedException {
        String[] splitEncodedConfig = encodedConfig.split("\\|", 3);
        String userName = splitEncodedConfig[0];
        int recommendedHours = Integer.parseInt(splitEncodedConfig[1]);
        boolean hasProgramRan = Boolean.parseBoolean(splitEncodedConfig[2]);

        if (recommendedHours <= 0 || recommendedHours > 12) {
            throw new StorageCorruptedException();
        }

        return new ConfigParameter(userName, recommendedHours, hasProgramRan);
    }
}
