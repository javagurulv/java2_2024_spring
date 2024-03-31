package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class ValidateAgreementDateFromNotLessThanToday {

    @Autowired
    private DateTimeService dateTimeService;

    public Optional<ValidationError> validateAgreementDateFromNotLessThanToday(TravelCalculatePremiumRequest request) {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();
        Date currentDate = dateTimeService.midnightToday();

        return (agreementDateFrom != null && agreementDateTo != null
                && agreementDateFrom.before(currentDate))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be in past!"))
                : Optional.empty();
    }
}
