package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator {

    @Autowired
    List<RequestFieldValidation> fieldValidation;


    @Override
    public List <ValidationError> validate (TravelCalculatePremiumRequest request) {
        List<ValidationError> collectedErrors = new ArrayList<>();
        collectedErrors.addAll(collectSingleErrors(request));
        collectedErrors.addAll(collectListErrors(request));
        return collectedErrors;
    }

    private List<ValidationError> collectSingleErrors(TravelCalculatePremiumRequest request) {
        return fieldValidation.stream()
                .map(validation -> validation.validateSingle(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationError> collectListErrors (TravelCalculatePremiumRequest request) {
        return fieldValidation.stream()
                .map(validation -> validation.validateList(request))
                .filter((Objects::nonNull))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

}