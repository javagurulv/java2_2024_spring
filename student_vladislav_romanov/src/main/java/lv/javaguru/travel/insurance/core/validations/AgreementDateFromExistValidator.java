package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class AgreementDateFromExistValidator extends TravelRequestValidatorImpl {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(validationErrorFactory.buildError(3))
                : Optional.empty();
    }

}