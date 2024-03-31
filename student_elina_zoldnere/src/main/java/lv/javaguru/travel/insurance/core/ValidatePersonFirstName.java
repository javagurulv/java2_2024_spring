package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
class ValidatePersonFirstName {
    public Optional<ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isBlank())
                ? Optional.of(new ValidationError("personFirstName", "Must not be empty!"))
                : Optional.empty();
    }
}
