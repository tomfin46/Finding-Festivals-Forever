package festivals.service.security;

import festivals.service.utils.ConfigFileProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Configure application settings
 *
 * @author Tom
 */
@Configuration
@EnableWebMvc
public class AppConfig {
    
    /**
     * Establish MySQL database as Spring Security users data source
     * 
     * @return Data source established with config properties
     */
    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        ConfigFileProperties config = ConfigFileProperties.getInstance();

        source.setDriverClassName(config.getPropertyValue("dbDriver"));
        source.setUrl(config.getPropertyValue("dbUrl") + config.getPropertyValue("dbName"));
        source.setUsername(config.getPropertyValue("dbUserName"));
        source.setPassword(config.getPropertyValue("dbPassword"));

        return source;
    }
}
