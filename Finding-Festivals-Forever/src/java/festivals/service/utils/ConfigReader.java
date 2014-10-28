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
      
    public static Properties loadConfigFile(String resourceLocation) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation);
        Properties prop = new Properties();
        
        try {
            prop.load(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, "Property file '" + ApplicationConstants.CONFIG_FILENAME + "' not found in classpath", ex);
        }
        
        return prop;
    }
}
