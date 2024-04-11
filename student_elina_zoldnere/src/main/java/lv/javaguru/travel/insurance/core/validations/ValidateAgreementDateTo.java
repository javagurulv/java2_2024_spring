package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ValidateAgreementDateTo implements RequestFieldValidation {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_4"))
                : Optional.empty();
    }

}
