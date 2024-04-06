package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestValidationPersonFirstNameTest {
    private RequestValidationPersonFirstName requestValidationPersonFirstName;
    private TravelCalculatePremiumRequest travelCalculatePremiumRequest;
    @BeforeEach
    void setUp(){
        requestValidationPersonFirstName = new RequestValidationPersonFirstName();
        travelCalculatePremiumRequest = new TravelCalculatePremiumRequest();
    }
    @Test
    void shouldReturnErrorWhenPersonFirstNameIsNull(){
        travelCalculatePremiumRequest.setPersonFirstName(null);
        Optional<ValidationError> error = requestValidationPersonFirstName.executeValidation(travelCalculatePremiumRequest);
        assert error.isPresent();
        assertEquals("personFirstName", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }
    @Test
    void shouldReturnErrorWhenPersonFirstNameIsEmptty(){
        travelCalculatePremiumRequest.setPersonFirstName("");
        Optional<ValidationError> error = requestValidationPersonFirstName.executeValidation(travelCalculatePremiumRequest);
        assert error.isPresent();
        assertEquals("personFirstName", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }
    @Test
    void shouldReturnEmptyWhenPersonFirstNameIsNotNull(){
        travelCalculatePremiumRequest.setPersonFirstName("Igor");
        Optional<ValidationError> error = requestValidationPersonFirstName.executeValidation(travelCalculatePremiumRequest);
        assert error.isEmpty();
    }
}