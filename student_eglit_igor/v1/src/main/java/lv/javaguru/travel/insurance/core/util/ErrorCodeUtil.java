package lv.javaguru.travel.insurance.core.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Component
@Configuration

public class ErrorCodeUtil {

    private Properties props;

    ErrorCodeUtil() throws IOException {
        Resource resource = new ClassPathResource("errorCodes.properties");
        props = PropertiesLoaderUtils.loadProperties(resource);

    }

    public String getErrorCodeDescription(String errorCode) {
        return props.getProperty(errorCode);
    }

    public String getErrorCodeDescription(String errorCode, List<Placeholder> placeholders) {
        String errorDescription = props.getProperty(errorCode);
        for(Placeholder placeholder : placeholders) {
            String placeholderToReplace = "{" + placeholder.getPlaceholderName() + "}";
            errorDescription = errorDescription.replace(placeholderToReplace, placeholder.getPlaceholderValue());
        }
        return errorDescription;
    }
}
