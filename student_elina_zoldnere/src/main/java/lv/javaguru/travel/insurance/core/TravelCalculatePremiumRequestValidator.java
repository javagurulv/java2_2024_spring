package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.*;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class TravelCalculatePremiumRequestValidator {

    @Autowired
    private ValidatePersonFirstName validatePersonFirstName;
    @Autowired
    private ValidatePersonLastName validatePersonLastName;
    @Autowired
    private ValidateAgreementDateFrom validateAgreementDateFrom;
    @Autowired
    private ValidateAgreementDateTo validateAgreementDateTo;
    @Autowired
    private ValidateAgreementDateChronology validateAgreementDateChronology;
    @Autowired
    private ValidateAgreementDateFromNotLessThanToday validateAgreementDateFromNotLessThanToday;
    @Autowired
    private ValidateAgreementDateToNotLessThanToday validateAgreementDateToNotLessThanToday;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName.validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName.validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDateFrom.validateAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateTo.validateAgreementDateTo(request).ifPresent(errors::add);
        validateAgreementDateChronology.validateAgreementDateChronology(request).ifPresent(errors::add);
        validateAgreementDateFromNotLessThanToday.validateAgreementDateFromNotLessThanToday(request).ifPresent(errors::add);
        validateAgreementDateToNotLessThanToday.validateAgreementDateToNotLessThanToday(request).ifPresent(errors::add);
        return errors;
    }

}