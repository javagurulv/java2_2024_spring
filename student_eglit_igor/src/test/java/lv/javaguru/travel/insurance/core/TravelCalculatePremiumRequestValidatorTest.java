package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class TravelCalculatePremiumRequestValidatorTest {
    TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
            "Igor",
            "Eglit",
            LocalDate.of(2024, 12, 12),
            LocalDate.of(2024, 12, 13));
    TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();

    @Test
    void validateNoErrorsWithAllFields() {
        assert validator.validate(request).isEmpty();

    }
    @Test
    void validatePersonFirstNameIsNull() {
        request.setPersonFirstName(null);
        assert validator.validate(request).size() == 1;
    }
    @Test
    void validatePersonFirstNameIsEmpty() {
        request.setPersonFirstName("");
        assert validator.validate(request).size() == 1;
    }
}