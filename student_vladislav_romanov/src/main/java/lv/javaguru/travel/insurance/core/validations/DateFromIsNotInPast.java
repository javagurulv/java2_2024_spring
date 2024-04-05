package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
class DateFromIsNotInPast implements TravelRequestValidator {

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() != null && request.getAgreementDateFrom().isBefore(LocalDate.now()))
                ? Optional.of(new ValidationError("agreementDateFrom", "date cannot be in past!"))
                : Optional.empty();
    }

}