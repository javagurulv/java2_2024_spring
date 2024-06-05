package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class FirstNameValidator extends TravelRequestValidationImpl {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrors> validate(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        return (travelCalculatePremiumRequest.getPersonFirstName() == null ||travelCalculatePremiumRequest.getPersonFirstName().isEmpty())
                ? Optional.of(validationErrorFactory.createError("ERROR_CODE_1"))
                : Optional.empty();

    }
}