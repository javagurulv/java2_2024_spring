package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class AgreementDateFromLessThenAgreementDateToValidation {

    public Optional<ValidationError> validateAgreementDateFromLessThenAgreementDateTo(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        return (dateFrom != null && dateTo != null && (dateFrom.after(dateTo)))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be less then agreementDateTo!"))
                : Optional.empty();
    }
}
