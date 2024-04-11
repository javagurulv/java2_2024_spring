package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.PropertyResolver;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class BuildError {

    @Autowired
    private PropertyResolver propertyResolver;

    public ValidationError buildError(String errorCode) {
        String errorDescription = propertyResolver.getPropertyDescription(errorCode);
        return new ValidationError(errorCode, errorDescription);
    }

}
