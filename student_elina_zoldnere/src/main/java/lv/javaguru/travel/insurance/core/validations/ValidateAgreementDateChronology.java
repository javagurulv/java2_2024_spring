package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class ValidateAgreementDateChronology implements RequestFieldValidation {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();

        return (agreementDateFrom != null && agreementDateTo != null
                && !agreementDateFrom.before(agreementDateTo))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_13"))
                : Optional.empty();
    }

}
