package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidatePersonFirstNameTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;

    @InjectMocks
    private ValidatePersonFirstName validate;

    @Autowired
    @InjectMocks
    private ValidateHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpRequestMockWithAllValues(requestMock);
    }

    @Test
    public void validate_ShouldReturnErrorWhenPersonFirstNameIsNull() {
        when(requestMock.getPersonFirstName()).thenReturn(null);

        Optional<ValidationError> result = validate.validatePersonFirstName(requestMock);

        assertTrue(result.isPresent());
        assertEquals("personFirstName", result.get().getField());
        assertEquals("Must not be empty!", result.get().getMessage());
    }

    @Test
    public void validate_ShouldReturnErrorWhenPersonFirstNameIsEmpty() {
        when(requestMock.getPersonFirstName()).thenReturn("");

        Optional<ValidationError> result = validate.validatePersonFirstName(requestMock);

        assertTrue(result.isPresent());
        assertEquals("personFirstName", result.get().getField());
        assertEquals("Must not be empty!", result.get().getMessage());
    }

    @Test
    public void validate_ShouldReturnErrorWhenPersonFirstNameIsBlank() {
        when(requestMock.getPersonFirstName()).thenReturn("     ");

        Optional<ValidationError> result = validate.validatePersonFirstName(requestMock);

        assertTrue(result.isPresent());
        assertEquals("personFirstName", result.get().getField());
        assertEquals("Must not be empty!", result.get().getMessage());
    }

}