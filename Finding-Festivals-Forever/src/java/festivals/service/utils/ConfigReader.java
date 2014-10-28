/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.service.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tom Finlayson
 */
@Service
public class ConfigReader {

    private static Properties prop = new Properties();

    public ConfigReader() {
        loadConfigFile();
    }
    
    public String getPropertyValue(String key) {
        String value = ApplicationConstants.EMPTY_STRING;
        
        if(prop != null){
            value = prop.getProperty(key);   
        }
        
        return value;
    }    
    
    private void loadConfigFile() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(ApplicationConstants.RESOURCES_PATH + ApplicationConstants.CONFIG_FILENAME);

        try {
            prop.load(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, "Property file '" + ApplicationConstants.CONFIG_FILENAME + "' not found in classpath", ex);
        }
    }
}
