/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.service.utils;

import java.util.Properties;

/**
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
    
    public static ConfigFileProperties getInstance() {
        return ConfigFilePropertiesHolder.INSTANCE;
    }
    
    public String getPropertyValue(String key) {
        return prop != null ? prop.getProperty(key) : ApplicationConstants.EMPTY_STRING;
    }
    
}
