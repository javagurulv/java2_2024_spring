package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class AgreementDateFromNotInThePastValidation {

    @Autowired
    private DateTimeService service;

    public Optional<ValidationError> validateAgreementDateFromNotInThePast(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date currentDate = service.currentDate();
        return (dateFrom != null && dateFrom.before(currentDate))
                ? Optional.of(new ValidationError("agreementDateFrom", "Should not be in the past!"))
                : Optional.empty();
    }
}
