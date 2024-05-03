package lv.javaguru.travel.insurance.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:errorCodes.properties")
public class ErrorPropertiesConfig {
}