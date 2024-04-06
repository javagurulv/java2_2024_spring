package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorTest {

    private TravelCalculatePremiumRequestValidator travelCalculatePremiumRequestValidator = new TravelCalculatePremiumRequestValidator();

    @Test
    public void errorIfFirstNameIsNull() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonFirstName()).thenReturn(null);
        when(premiumRequest.getPersonLastName()).thenReturn("lastName");
        when(premiumRequest.getAgreementDateFrom()).thenReturn(new Date());
        when(premiumRequest.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationErrors> validationErrors = travelCalculatePremiumRequestValidator.validation(premiumRequest);
        assertFalse(validationErrors.isEmpty());
        assertEquals(validationErrors.size(), 1);
        assertEquals(validationErrors.get(0).getField(), "firstName");
        assertEquals(validationErrors.get(0).getMessage(), "Field cannot be empty");

    }

    @Test
    public void errorIfLastNameIsNull() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonFirstName()).thenReturn("firstName");
        when(premiumRequest.getPersonLastName()).thenReturn(null);
        when(premiumRequest.getAgreementDateFrom()).thenReturn(new Date());
        when(premiumRequest.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationErrors> validationErrors = travelCalculatePremiumRequestValidator.validation(premiumRequest);
        assertFalse(validationErrors.isEmpty());
        assertEquals(validationErrors.size(), 1);
        assertEquals(validationErrors.get(0).getField(), "lastName");
        assertEquals(validationErrors.get(0).getMessage(), "Field cannot be empty");

    }

    @Test
    public void errorIfFirstNameIsEmpty() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonFirstName()).thenReturn("");
        when(premiumRequest.getPersonLastName()).thenReturn("lastName");
        when(premiumRequest.getAgreementDateFrom()).thenReturn(new Date());
        when(premiumRequest.getAgreementDateTo()).thenReturn(new Date());        List<ValidationErrors> validationErrors = travelCalculatePremiumRequestValidator.validation(premiumRequest);
        assertFalse(validationErrors.isEmpty());
        assertEquals(validationErrors.size(), 1);
        assertEquals(validationErrors.get(0).getField(), "firstName");
        assertEquals(validationErrors.get(0).getMessage(), "Field cannot be empty");
    }

    @Test
    public void errorIfLastNameIsEmpty() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonFirstName()).thenReturn("firstName");
        when(premiumRequest.getPersonLastName()).thenReturn("");
        when(premiumRequest.getAgreementDateFrom()).thenReturn(new Date());
        when(premiumRequest.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationErrors> validationErrors = travelCalculatePremiumRequestValidator.validation(premiumRequest);
        assertFalse(validationErrors.isEmpty());
        assertEquals(validationErrors.size(), 1);
        assertEquals(validationErrors.get(0).getField(), "lastName");
        assertEquals(validationErrors.get(0).getMessage(), "Field cannot be empty");
    }

    @Test
    public void noErrorIfAllFieldsArePresent() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonFirstName()).thenReturn("firstName");
        when(premiumRequest.getPersonLastName()).thenReturn("lastName");
        when(premiumRequest.getAgreementDateFrom()).thenReturn(new Date());
        when(premiumRequest.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationErrors> validationErrors = travelCalculatePremiumRequestValidator.validation(premiumRequest);
        assertTrue(validationErrors.isEmpty());
    }

    @Test
    public void errorIfDateFromIsEmpty() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonFirstName()).thenReturn("firstName");
        when(premiumRequest.getPersonLastName()).thenReturn("lastName");
        when(premiumRequest.getAgreementDateFrom()).thenReturn(null);
        when(premiumRequest.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationErrors> validationErrors = travelCalculatePremiumRequestValidator.validation(premiumRequest);
        assertFalse(validationErrors.isEmpty());
        assertEquals(validationErrors.size(), 1);
        assertEquals(validationErrors.get(0).getField(), "dateFrom");
        assertEquals(validationErrors.get(0).getMessage(), "Field cannot be empty");
    }

    @Test
    public void errorIfDateToIsEmpty() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonFirstName()).thenReturn("firstName");
        when(premiumRequest.getPersonLastName()).thenReturn("lastName");
        when(premiumRequest.getAgreementDateFrom()).thenReturn(new Date());
        when(premiumRequest.getAgreementDateTo()).thenReturn(null);
        List<ValidationErrors> validationErrors = travelCalculatePremiumRequestValidator.validation(premiumRequest);
        assertFalse(validationErrors.isEmpty());
        assertEquals(validationErrors.size(), 1);
        assertEquals(validationErrors.get(0).getField(), "dateTo");
        assertEquals(validationErrors.get(0).getMessage(), "Field cannot be empty");
    }


}
