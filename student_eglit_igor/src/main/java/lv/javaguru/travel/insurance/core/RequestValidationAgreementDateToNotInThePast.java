package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class RequestValidationAgreementDateToNotInThePast {
   public Optional<ValidationError> validateAgreementDateToNotInThePast(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateTo() == null || request.getAgreementDateFrom() == null) {
            return Optional.empty();
        }
        return (request.getAgreementDateTo().isBefore(java.time.LocalDate.now().plusDays(1)))
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be in the past!"))
                : Optional.empty();
    }
}
