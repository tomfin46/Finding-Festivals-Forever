package festivals.service.utils;

import java.util.Properties;

/**
 * Singleton implementation of a properties file reader
 * 
 * @author Tom
 */
public class ConfigFileProperties {
    
    private static class ConfigFilePropertiesHolder {
        private static final ConfigFileProperties INSTANCE = new ConfigFileProperties();
    }
    
    private final Properties prop;
    
    private ConfigFileProperties() {
        prop = ConfigReader.loadConfigFile(ApplicationConstants.RESOURCES_PATH + ApplicationConstants.CONFIG_FILENAME);
    }
    
    /**
     * Return singleton instance of class
     * 
     * @return
     */
    public static ConfigFileProperties getInstance() {
        return ConfigFilePropertiesHolder.INSTANCE;
    }
    
    /**
     * Accessor for config file's values
     * 
     * @param key Key to fetch value for
     * @return Value for key or empty string if properties file doesn't exist
     */
    public String getPropertyValue(String key) {
        return prop != null ? prop.getProperty(key) : ApplicationConstants.EMPTY_STRING;
    }
}
