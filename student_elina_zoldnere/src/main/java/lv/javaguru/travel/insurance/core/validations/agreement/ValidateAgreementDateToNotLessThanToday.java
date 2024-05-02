package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class ValidateAgreementDateToNotLessThanToday extends RequestAgreementFieldValidationImpl {

    @Autowired
    private DateTimeUtil dateTimeUtil;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validateSingle(TravelCalculatePremiumRequestV1 request) {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();

        return (agreementDateFrom != null && agreementDateTo != null
                && agreementDateTo.before(dateTimeUtil.startOfToday()))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_12"))
                : Optional.empty();
    }

}
