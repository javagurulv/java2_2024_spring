package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.Period;
import java.util.Optional;

@Component
class TravelPeriodIsValid implements TravelRequestValidator {

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null || request.getAgreementDateTo() == null || Period.between(request.getAgreementDateFrom(), request.getAgreementDateTo()).getDays() < 1)
                ? Optional.of(new ValidationError("Travel Period", "contain incorrect data!"))
                : Optional.empty();
    }

}