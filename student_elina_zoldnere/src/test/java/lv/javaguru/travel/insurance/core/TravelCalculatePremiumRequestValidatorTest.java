package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;

    @InjectMocks
    private TravelCalculatePremiumRequestValidator requestValidator;

    @Test
    public void validate_ShouldReturnErrorWhenPersonFirstNameIsNull() {
        when(requestMock.getPersonFirstName()).thenReturn(null);

        List<ValidationError> result = requestValidator.validate(requestMock);

        assertEquals(1, result.size());
        assertEquals("personFirstName", result.get(0).getField());
        assertEquals("Must not be empty!", result.get(0).getMessage());
    }

    @Test
    public void validate_ShouldReturnErrorWhenPersonFirstNameIsEmpty() {
        when(requestMock.getPersonFirstName()).thenReturn("");

        List<ValidationError> result = requestValidator.validate(requestMock);

        assertEquals(1, result.size());
        assertEquals("personFirstName", result.get(0).getField());
        assertEquals("Must not be empty!", result.get(0).getMessage());
    }

    @Test
    public void validate_ShouldPassWhenPersonFirstNameIsValid() {
        when(requestMock.getPersonFirstName()).thenReturn("Jānis");

        List<ValidationError> result = requestValidator.validate(requestMock);

        assertTrue(result.isEmpty());
    }

}
