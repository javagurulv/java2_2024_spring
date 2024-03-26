package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RequestValidationAgreementDateFromNotInThePast {
    public Optional<ValidationError> validateAgreementDateFromNotInThePast(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateTo() == null || request.getAgreementDateFrom() == null) {
            return Optional.empty();
        }
        return (request.getAgreementDateFrom().isBefore(java.time.LocalDate.now().plusDays(1)))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be in the past!"))
                : Optional.empty();
    }
}
