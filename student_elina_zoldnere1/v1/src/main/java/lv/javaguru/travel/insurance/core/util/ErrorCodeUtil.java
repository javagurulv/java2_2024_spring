package lv.javaguru.travel.insurance.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ErrorCodeUtil {

    @Autowired
    private PropertyResolver resolver;

    public String getErrorDescription(String errorCode) {
        return resolver.getPropertyDescription(errorCode);
    }

    public String getErrorDescription(String errorCode, List<Placeholder> placeholders) {
        String descriptionWithPlaceholders = resolver.getPropertyDescription(errorCode);
        return replacePlaceholders(descriptionWithPlaceholders, placeholders);
    }

    private String replacePlaceholders(String descriptionWithPlaceholders, List<Placeholder> placeholders) {
        for (Placeholder placeholder : placeholders) {
            String placeholderName = placeholder.getPlaceholderName();
            String placeholderValue = placeholder.getPlaceholderValue();
            descriptionWithPlaceholders = descriptionWithPlaceholders.replace("{" + placeholderName + "}", placeholderValue);
        }
        return descriptionWithPlaceholders;
    }

}
