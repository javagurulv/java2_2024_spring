package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ValidatePersonFirstName implements RequestFieldValidation {

    @Autowired
    private BuildError buildError;

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isBlank())
                ? Optional.of(buildError.buildError("ERROR_CODE_1"))
                : Optional.empty();
    }

}
