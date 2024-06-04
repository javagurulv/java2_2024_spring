package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.*;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.List;

@Component
class TravelCalculatePremiumRequestValidator {

    @Autowired
    private PersonFirstNameValidation personFirstNameValidation;
    @Autowired
    private PersonLastNameValidation personLastNameValidation;
    @Autowired
    private AgreementDateFromValidation agreementDateFromValidation;
    @Autowired
    private AgreementDateToValidation agreementDateToValidation;
    @Autowired
    private AgreementDateFromLessThenAgreementDateToValidation agreementDateFromLessThenAgreementDateToValidation;
    @Autowired
    private AgreementDateFromNotInThePastValidation agreementDateFromNotInThePast;
    @Autowired
    private AgreementDateToNotInThePastValidation agreementDateToNotInThePastValidation;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        personFirstNameValidation.validatePersonFirstName(request).ifPresent(errors::add);
        personLastNameValidation.validatePersonLastName(request).ifPresent(errors::add);
        agreementDateFromValidation.validateAgreementDateFrom(request).ifPresent(errors::add);
        agreementDateToValidation.validateAgreementDateTo(request).ifPresent(errors::add);
        agreementDateFromLessThenAgreementDateToValidation.validateAgreementDateFromLessThenAgreementDateTo(request).ifPresent(errors::add);
        agreementDateFromNotInThePast.validateAgreementDateFromNotInThePast(request).ifPresent(errors::add);
        agreementDateToNotInThePastValidation.validateAgreementDateToNotInThePast(request).ifPresent(errors::add);
        return errors;
    }

}
