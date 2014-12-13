/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.service.security;

import festivals.service.utils.ConfigFileProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Tom
 */
@Configuration
@EnableWebMvc
public class AppConfig {

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();

        ConfigFileProperties config = ConfigFileProperties.getInstance();
        source.setDriverClassName(config.getPropertyValue("dbDriver"));
        source.setUrl(config.getPropertyValue("dbUrl"));
        source.setUsername(config.getPropertyValue("dbUserName"));
        source.setPassword(config.getPropertyValue("dbPassword"));

        return source;
    }
}
