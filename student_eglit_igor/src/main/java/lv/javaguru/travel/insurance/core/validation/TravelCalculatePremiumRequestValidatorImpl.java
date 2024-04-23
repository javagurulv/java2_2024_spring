package lv.javaguru.travel.insurance.core.validation;


import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidatorInterface {
    @Autowired
    private List<RequestValidationInterface> travelValidations;

    public TravelCalculatePremiumRequestValidatorImpl(List<RequestValidationInterface> travelValidations) {
        this.travelValidations = travelValidations;
    }

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> singleErrors = collectSingleErrors(request);
        List<ValidationError> listErrors = collectListErrors(request);
        return concatenateLists(singleErrors, listErrors);
    }

    public List<ValidationError> collectSingleErrors(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        travelValidations.stream()
                .map(validation -> validation.validateSingle(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(errors::add);
        return errors;
    }

    public List<ValidationError> collectListErrors(TravelCalculatePremiumRequest request) {
        return travelValidations.stream()
                .map(validation -> validation.validateList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

    }

    private List<ValidationError> concatenateLists(List<ValidationError> singleErrors,
                                                   List<ValidationError> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }


}
       /*requestValidator_personFirstName.validatePersonFirstName(request).ifPresent(errors::add);
        requestValidator_personLastName.validatePersonLastName(request).ifPresent(errors::add);
        requestValidation_agreementDateFrom.validateAgreementDateFrom(request).ifPresent(errors::add);
        requestValidation_agreementDateTo.validateAgreementDateTo(request).ifPresent(errors::add);
        requestValidation_agreementDateToIsAfterDateFrom.validateAgreementDateToIsAfterDateFrom(request).ifPresent(errors::add);
        requestValidator_agreementDateFromNotInThePast.validateAgreementDateFromNotInThePast(request).ifPresent(errors::add);
        requestValidator_agreementDateToNotInThePast.validateAgreementDateToNotInThePast(request).ifPresent(errors::add);*/

