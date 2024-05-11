package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelPremiumUnderwriting travelPremiumUnderwriting;

    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @Test
    void responseFirstNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        when(travelPremiumUnderwriting.calculatePremium(request)).thenReturn(calculationResult);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), "personFirstName");
    }

    @Test
    void responseLastNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        when(travelPremiumUnderwriting.calculatePremium(request)).thenReturn(calculationResult);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonLastName(), "personLastName");
    }

    @Test
    void responseDateFromTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        LocalDate dateFrom = LocalDate.of(2030, 3, 31);
        when(request.getAgreementDateFrom()).thenReturn(dateFrom);
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        when(travelPremiumUnderwriting.calculatePremium(request)).thenReturn(calculationResult);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), dateFrom);
    }

    @Test
    void responseDateToTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        LocalDate dateTo = LocalDate.of(2030, 3, 31);
        when(request.getAgreementDateTo()).thenReturn(dateTo);
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        when(travelPremiumUnderwriting.calculatePremium(request)).thenReturn(calculationResult);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), dateTo);
    }

    @Test
    void responseWithErrorsTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        ValidationError validationError = new ValidationError(1, "message");
        doReturn(List.of(validationError)).when(requestValidator).validate(request);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertTrue(response.hasErrors());
    }

    @Test
    void responseWithErrorCountTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        ValidationError validationError = new ValidationError(1, "message");
        doReturn(List.of(validationError)).when(requestValidator).validate(request);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(response.getErrors().size(), 1);
    }

    @Test
    void responseWithCorrectErrorMessageTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        ValidationError validationError = new ValidationError(1, "message");
        doReturn(List.of(validationError)).when(requestValidator).validate(request);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(response.getErrors().get(0).getErrorCode(), 1);
        assertEquals(response.getErrors().get(0).getDescription(), "message");
    }

    @Test
    void emptyResponseFieldsInCaseOfErrorsTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        ValidationError validationError = new ValidationError(1, "message");
        doReturn(List.of(validationError)).when(requestValidator).validate(request);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertNull(response.getPersonFirstName());
        assertNull(response.getPersonLastName());
        assertNull(response.getAgreementDateFrom());
        assertNull(response.getAgreementDateTo());
        assertNull(response.getAgreementPremium());
    }

}