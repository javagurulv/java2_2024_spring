package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
class AgreementDateToInFutureValidator extends TravelRequestValidatorImpl {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() != null && request.getAgreementDateTo().isBefore(LocalDate.now()))
                ? Optional.of(validationErrorFactory.buildError(6))
                : Optional.empty();
    }

}