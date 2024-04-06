package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestValidationPersonLastNameTest {
    private RequestValidationPersonLastName requestValidationPersonLastName;
    private TravelCalculatePremiumRequest request;
    @BeforeEach
    void setUp(){
        requestValidationPersonLastName = new RequestValidationPersonLastName();
        request = new TravelCalculatePremiumRequest();
    }
    @Test
    void shouldReturnErrorWhenPersonLastNameIsNull(){
        request.setPersonLastName(null);
        Optional<ValidationError> result = requestValidationPersonLastName.executeValidation(request);
        assertTrue(result.isPresent());
        assertEquals("personLastName", result.get().getField());
        assertEquals("Must not be empty!", result.get().getMessage());
    }
    @Test
    void shouldReturnErrorWhenPersonLastNameIsEmpty(){
        request.setPersonLastName("");
        Optional<ValidationError> result = requestValidationPersonLastName.executeValidation(request);
        assertTrue(result.isPresent());
        assertEquals("personLastName", result.get().getField());
        assertEquals("Must not be empty!", result.get().getMessage());
    }
    @Test
    void shouldReturnEmptyWhenPersonLastNameIsNotEmpty(){
        request.setPersonLastName("Igor");
        Optional<ValidationError> result = requestValidationPersonLastName.executeValidation(request);
        assertTrue(result.isEmpty());
    }
}