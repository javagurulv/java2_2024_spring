package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class AgreementDateToNotInThePastValidation {

    @Autowired
    private DateTimeService service;

    public Optional<ValidationError> validateAgreementDateToNotInThePast(TravelCalculatePremiumRequest request) {
        Date dateTo = request.getAgreementDateTo();
        Date currentDate = service.currentDate();
        return (dateTo != null && dateTo.before(currentDate))
                ? Optional.of(new ValidationError("agreementDateTo", "Should not be in the past!"))
                : Optional.empty();
    }
}
