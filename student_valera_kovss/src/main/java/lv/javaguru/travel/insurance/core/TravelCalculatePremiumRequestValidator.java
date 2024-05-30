package lv.javaguru.travel.insurance.core;

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
    private ValidateAgreementDateFrom agreementDateFromValidation;
    @Autowired
    private AgreementDateToValidation agreementDateToValidation;
    @Autowired
    private DateFromAndDateToValidation dateFromLessThenDateToValidation;
    @Autowired
    private AgreementDateFutureValidation agreementDateFromInFutureValidation;
    @Autowired
    private AgreementDateToInFutureValidation agreementDateToInFutureValidation;


    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        personFirstNameValidation.validatePersonFirstName(request).ifPresent(errors::add);
        personLastNameValidation.validatePersonLastName(request).ifPresent(errors::add);
        agreementDateFromValidation.validateAgreementDateFrom(request).ifPresent(errors::add);
        agreementDateToValidation.validateAgreementDateTo(request).ifPresent(errors::add);
        dateFromLessThenDateToValidation.validateDateFromLessThenDateTo(request).ifPresent(errors::add);
        agreementDateFromInFutureValidation.validateDateFromInFuture(request).ifPresent(errors::add);
        agreementDateToInFutureValidation.validateDateToInFuture(request).ifPresent(errors::add);
        return errors;
    }

}
