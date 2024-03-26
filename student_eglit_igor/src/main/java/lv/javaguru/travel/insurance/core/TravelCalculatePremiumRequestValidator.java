package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TravelCalculatePremiumRequestValidator {

    @Autowired
    private RequestValidationPersonFirstName requestValidator_personFirstName;
    @Autowired
    private RequestValidationPersonLastName requestValidator_personLastName;
    @Autowired
    private RequestValidationAgreementDateFrom requestValidation_agreementDateFrom;
    @Autowired
    private RequestValidationAgreementDateTo requestValidation_agreementDateTo;
    @Autowired
    private RequestValidationAgreementDateToIsAfterDateFrom requestValidation_agreementDateToIsAfterDateFrom;
    @Autowired
    private RequestValidationAgreementDateFromNotInThePast requestValidator_agreementDateFromNotInThePast;
    @Autowired
    private RequestValidationAgreementDateToNotInThePast requestValidator_agreementDateToNotInThePast;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        requestValidator_personFirstName.validatePersonFirstName(request).ifPresent(errors::add);
        requestValidator_personLastName.validatePersonLastName(request).ifPresent(errors::add);
        requestValidation_agreementDateFrom.validateAgreementDateFrom(request).ifPresent(errors::add);
        requestValidation_agreementDateTo.validateAgreementDateTo(request).ifPresent(errors::add);
        requestValidation_agreementDateToIsAfterDateFrom.validateAgreementDateToIsAfterDateFrom(request).ifPresent(errors::add);
        requestValidator_agreementDateFromNotInThePast.validateAgreementDateFromNotInThePast(request).ifPresent(errors::add);
        requestValidator_agreementDateToNotInThePast.validateAgreementDateToNotInThePast(request).ifPresent(e -> errors.add(e));
        return errors;
    }

}


