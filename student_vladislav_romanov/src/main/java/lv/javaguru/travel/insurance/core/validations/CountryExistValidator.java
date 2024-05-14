package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class CountryExistValidator extends TravelRequestValidatorImpl {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getCountry() == null || request.getCountry().isEmpty())
                ? Optional.of(validationErrorFactory.buildError(10))
                : Optional.empty();
    }

}