package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class ValidateAgreementDateToNotLessThanToday {

    @Autowired
    private DateTimeService dateTimeService;

    public Optional<ValidationError> validateAgreementDateToNotLessThanToday(TravelCalculatePremiumRequest request) {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();

        return (agreementDateFrom != null && agreementDateTo != null
                && agreementDateTo.before(dateTimeService.midnightToday()))
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be in past!"))
                : Optional.empty();
    }
}
