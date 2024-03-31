package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.TravelRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class TravelCalculatePremiumRequestValidator {

    @Autowired
    private List<TravelRequestValidator> travelValidations;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();

        travelValidations.forEach(x -> x.execute(request).ifPresent(errors::add));

        return errors;
    }

}
