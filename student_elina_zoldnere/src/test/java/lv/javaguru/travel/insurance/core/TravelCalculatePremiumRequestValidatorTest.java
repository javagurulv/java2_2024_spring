package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
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

    @BeforeEach
    public void setUp() {
        setUpRequestMockWithAllValues();
    }

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
    public void validate_ShouldReturnErrorWhenPersonLastNameIsNull() {
        when(requestMock.getPersonLastName()).thenReturn(null);

        List<ValidationError> result = requestValidator.validate(requestMock);

        assertEquals(1, result.size());
        assertEquals("personLastName", result.get(0).getField());
        assertEquals("Must not be empty!", result.get(0).getMessage());
    }

    @Test
    public void validate_ShouldReturnErrorWhenPersonLastNameIsEmpty() {
        when(requestMock.getPersonLastName()).thenReturn("");

        List<ValidationError> result = requestValidator.validate(requestMock);

        assertEquals(1, result.size());
        assertEquals("personLastName", result.get(0).getField());
        assertEquals("Must not be empty!", result.get(0).getMessage());
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateFromIsNull() {
        when(requestMock.getAgreementDateFrom()).thenReturn(null);

        List<ValidationError> result = requestValidator.validate(requestMock);

        assertEquals(1, result.size());
        assertEquals("agreementDateFrom", result.get(0).getField());
        assertEquals("Must not be empty!", result.get(0).getMessage());
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateToIsNull() {
        when(requestMock.getAgreementDateTo()).thenReturn(null);

        List<ValidationError> result = requestValidator.validate(requestMock);

        assertEquals(1, result.size());
        assertEquals("agreementDateTo", result.get(0).getField());
        assertEquals("Must not be empty!", result.get(0).getMessage());
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDatesAreEqual() {
        // requestMock.getAgreementDateFrom() returns (new Date (2024 - 1900, 2, 10))
        when(requestMock.getAgreementDateTo()).thenReturn(new Date(2024 - 1900, 2, 10));

        List<ValidationError> result = requestValidator.validate(requestMock);

        assertEquals(1, result.size());
        assertEquals("agreementDateFrom", result.get(0).getField());
        assertEquals("Must be before agreementDateTo!", result.get(0).getMessage());
    }

    @Test
    public void validate_ShouldReturnErrorAgreementDatesInWrongOrder() {
        // requestMock.getAgreementDateFrom() returns (new Date (2024 - 1900, 2, 10))
        when(requestMock.getAgreementDateTo()).thenReturn(new Date(2024 - 1900, 2, 9));

        List<ValidationError> result = requestValidator.validate(requestMock);

        assertEquals(1, result.size());
        assertEquals("agreementDateFrom", result.get(0).getField());
        assertEquals("Must be before agreementDateTo!", result.get(0).getMessage());
    }

    @Test
    public void validate_ShouldPassWhenAllFieldsAreValid() {

        List<ValidationError> result = requestValidator.validate(requestMock);

        assertTrue(result.isEmpty());
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateFromHasWrongFormat() {
        // Set up the mock to return a date with the wrong format
        when(requestMock.getAgreementDateFrom()).thenReturn(new Date());

        List<ValidationError> result = requestValidator.validate(requestMock);

        assertEquals(1, result.size());
        assertEquals("agreementDateFrom", result.get(0).getField());
        assertEquals("Date format must be \"yyyy-MM-dd\"!", result.get(0).getMessage());
    }

    private void setUpRequestMockWithAllValues() {
        Mockito.lenient().when(requestMock.getPersonFirstName()).thenReturn("Jānis");
        Mockito.lenient().when(requestMock.getPersonLastName()).thenReturn("Bērziņš");
        Mockito.lenient().when(requestMock.getAgreementDateFrom()).thenReturn(new Date(124, 2, 11));
        Mockito.lenient().when(requestMock.getAgreementDateTo()).thenReturn(new Date(124, 2, 11));
    }

}