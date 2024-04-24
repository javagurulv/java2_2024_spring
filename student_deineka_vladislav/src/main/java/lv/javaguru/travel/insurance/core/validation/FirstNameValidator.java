package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class FirstNameValidator implements TravelRequestValidation {

    @Override
    public Optional<ValidationErrors> execute(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        return (travelCalculatePremiumRequest.getPersonFirstName() == null ||travelCalculatePremiumRequest.getPersonFirstName().isEmpty())
                ? Optional.of(new ValidationErrors("firstName", "Field cannot be empty"))
                : Optional.empty();

    }
}