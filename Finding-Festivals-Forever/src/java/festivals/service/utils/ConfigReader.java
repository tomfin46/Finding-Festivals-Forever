package festivals.service.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * Load config file into Properties object
 *
 * @author Tom Finlayson
 */
@Service
public class ConfigReader {
      
    /**
     * Statically load a properties file
     * 
     * @param resourceLocation Properties file location
     * @return Properties file
     */
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
