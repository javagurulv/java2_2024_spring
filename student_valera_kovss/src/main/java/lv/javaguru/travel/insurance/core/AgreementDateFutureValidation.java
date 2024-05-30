package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class AgreementDateFutureValidation {

    @Autowired
    private DateTimeService dateTimeService;

    protected Optional<ValidationError> validateDateFromInFuture(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date currentDateTime = dateTimeService.getCurrentDateTime();
        return (dateFrom != null && dateFrom.before(currentDateTime))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be in the future!"))
                : Optional.empty();
    }
}
