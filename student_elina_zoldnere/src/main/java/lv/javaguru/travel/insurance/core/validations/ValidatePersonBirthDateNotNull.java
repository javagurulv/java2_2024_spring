package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ValidatePersonBirthDateNotNull extends RequestFieldValidationImpl {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validateSingle(TravelCalculatePremiumRequest request) {
        return (request.getPersonBirthDate() == null)
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_7"))
                : Optional.empty();
    }

}
