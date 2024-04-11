package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class ValidateAgreementDateToNotLessThanToday implements RequestFieldValidation {

    @Autowired
    private DateTimeService dateTimeService;
    @Autowired
    private BuildError buildError;

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();

        return (agreementDateFrom != null && agreementDateTo != null
                && agreementDateTo.before(dateTimeService.midnightToday()))
                ? Optional.of(buildError.buildError("ERROR_CODE_12"))
                : Optional.empty();
    }

}
