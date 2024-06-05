package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class LastNameValidator extends TravelRequestValidationImpl{

    @Autowired
    ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrors> validate(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(validationErrorFactory.createError("ERROR_CODE_2"))
                : Optional.empty();

    }
}