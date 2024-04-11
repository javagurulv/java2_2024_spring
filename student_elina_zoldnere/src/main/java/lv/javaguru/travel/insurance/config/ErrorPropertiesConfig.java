package lv.javaguru.travel.insurance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:errorCodes.properties")
public class ErrorPropertiesConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer errorPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}