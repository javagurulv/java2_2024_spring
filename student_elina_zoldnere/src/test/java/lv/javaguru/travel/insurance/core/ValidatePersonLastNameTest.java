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
public class ValidatePersonLastNameTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;

    @InjectMocks
    private ValidatePersonLastName validate;

    @Autowired
    @InjectMocks
    private ValidateHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpRequestMockWithAllValues(requestMock);
    }

    @Test
    public void validate_ShouldReturnErrorWhenPersonLastNameIsNull() {
        when(requestMock.getPersonLastName()).thenReturn(null);

        Optional<ValidationError> result = validate.validatePersonLastName(requestMock);

        assertTrue(result.isPresent());
        assertEquals("personLastName", result.get().getField());
        assertEquals("Must not be empty!", result.get().getMessage());
    }

    @Test
    public void validate_ShouldReturnErrorWhenPersonLastNameIsEmpty() {
        when(requestMock.getPersonLastName()).thenReturn("");

        Optional<ValidationError> result = validate.validatePersonLastName(requestMock);

        assertTrue(result.isPresent());
        assertEquals("personLastName", result.get().getField());
        assertEquals("Must not be empty!", result.get().getMessage());
    }

    @Test
    public void validate_ShouldReturnErrorWhenPersonLastNameIsBlank() {
        when(requestMock.getPersonLastName()).thenReturn("     ");

        Optional<ValidationError> result = validate.validatePersonLastName(requestMock);

        assertTrue(result.isPresent());
        assertEquals("personLastName", result.get().getField());
        assertEquals("Must not be empty!", result.get().getMessage());
    }

}