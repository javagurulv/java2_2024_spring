package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class ValidateAgreementDateFromNotLessThanToday extends RequestFieldValidationImpl {

    @Autowired
    private DateTimeUtil dateTimeService;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validateSingle(TravelCalculatePremiumRequest request) {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();
        Date currentDate = dateTimeService.midnightToday();

        return (agreementDateFrom != null && agreementDateTo != null
                && agreementDateFrom.before(currentDate))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_11"))
                : Optional.empty();
    }

}
