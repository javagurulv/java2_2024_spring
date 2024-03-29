package lv.javaguru.travel.insurance.core.validation;


import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TravelCalculatePremiumRequestValidator {
    @Autowired
    private List<RequestValidationInterface> travelValidations;

    public TravelCalculatePremiumRequestValidator(List<RequestValidationInterface> travelValidations) {
        this.travelValidations = travelValidations;
    }

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        travelValidations.stream()
                .map(validation -> validation.executeValidation(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(errors::add);
        return errors;
    }

}
       /*requestValidator_personFirstName.validatePersonFirstName(request).ifPresent(errors::add);
        requestValidator_personLastName.validatePersonLastName(request).ifPresent(errors::add);
        requestValidation_agreementDateFrom.validateAgreementDateFrom(request).ifPresent(errors::add);
        requestValidation_agreementDateTo.validateAgreementDateTo(request).ifPresent(errors::add);
        requestValidation_agreementDateToIsAfterDateFrom.validateAgreementDateToIsAfterDateFrom(request).ifPresent(errors::add);
        requestValidator_agreementDateFromNotInThePast.validateAgreementDateFromNotInThePast(request).ifPresent(errors::add);
        requestValidator_agreementDateToNotInThePast.validateAgreementDateToNotInThePast(request).ifPresent(errors::add);*/

