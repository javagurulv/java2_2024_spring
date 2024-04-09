package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
class RequestValidationPersonFirstName implements RequestValidationInterface {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> executeValidation(TravelCalculatePremiumRequest request) {
        String errorCode = "ERROR_CODE_1";
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(validationErrorFactory.buildError(errorCode))
                : Optional.empty();
    }

}
