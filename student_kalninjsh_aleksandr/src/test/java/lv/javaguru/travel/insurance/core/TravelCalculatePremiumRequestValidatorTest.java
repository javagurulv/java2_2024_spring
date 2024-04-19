package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @InjectMocks
    private TravelCalculatePremiumRequestValidator requestValidator;

    @Test
    public void returnErrorWhenPersonFirstNameIsNull() {
        when(request.getPersonFirstName()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void returnErrorWhenPersonFirstNameIsEmpty() {
        when(request.getPersonFirstName()).thenReturn("");
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void notReturnErrorIfThereIsPersonName() {
        when(request.getPersonFirstName()).thenReturn("Tom");
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

}