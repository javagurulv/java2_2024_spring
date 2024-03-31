package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
class DateToIsNotInPast implements TravelRequestValidator {

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() != null && request.getAgreementDateTo().isBefore(LocalDate.now()))
                ? Optional.of(new ValidationError("agreementDateTo", "date cannot be in past!"))
                : Optional.empty();
    }

}