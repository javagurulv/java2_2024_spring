package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class RequestValidationPersonLastName implements RequestValidationInterface{

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> executeValidation(TravelCalculatePremiumRequest request) {
        String errorCode = "ERROR_CODE_2";
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(validationErrorFactory.buildError(errorCode))
                : Optional.empty();
    }
}
