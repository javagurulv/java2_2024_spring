package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {
   @Mock private DateTimeService dateTimeService;

   @Mock private TravelCalculatePremiumRequestValidator requestValidator;
   @InjectMocks private TravelCalculatePremiumServiceImpl travelCalculatePremiumService;
  // @InjectMocks private TravelCalculatePremiumRequest request;
  // private TravelCalculatePremiumResponse response;




    @Test
    public void shouldTestResponseFirstName() {
       var request = createReadyMadeRequest();
        when(dateTimeService.daysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(31L);
        when(requestValidator.validate(request)).thenReturn(List.of());
        var response = travelCalculatePremiumService.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());

    }

 @Test
    public void shouldTestResponseLastname() {
        var request = createReadyMadeRequest();
        when(dateTimeService.daysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(31L);
        when(requestValidator.validate(request)).thenReturn(List.of());
        var response = travelCalculatePremiumService.calculatePremium(request);
        assertEquals(response.getPersonLastName(), request.getPersonLastName());

    }

    @Test
    public void shouldTestResponseDateFrom() {
        var request = createReadyMadeRequest();
        when(dateTimeService.daysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(31L);
        when(requestValidator.validate(request)).thenReturn(List.of());
        var response = travelCalculatePremiumService.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(),request.getAgreementDateFrom());

    }

    @Test
    public void shouldTestResponseDateTo() {
        var request = createReadyMadeRequest();
        when(dateTimeService.daysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(31L);
        when(requestValidator.validate(request)).thenReturn(List.of());
        var response = travelCalculatePremiumService.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(),request.getAgreementDateTo());

    }


    @Test
    public void shouldTestResponseAgreementPrice() {
        var request = createReadyMadeRequest();
        when(dateTimeService.daysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(31L);
        when(requestValidator.validate(request)).thenReturn(List.of());
        var response = travelCalculatePremiumService.calculatePremium(request);
        assertEquals(response.getAgreementPrice(),new BigDecimal(31));

    }
    @Test
    public void shouldReturnResponseWithErrors() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = travelCalculatePremiumService.calculatePremium(request);
        assertTrue(response.hasErrors());
    }
    @Test
    public void shouldReturnResponseWithCorrectErrorCount() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = travelCalculatePremiumService.calculatePremium(request);
        assertEquals(response.getErrors().size(), 1);
    }

    @Test
    public void shouldReturnResponseWithCorrectError() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = travelCalculatePremiumService.calculatePremium(request);
        assertEquals(response.getErrors().get(0).getField(), "field");
        assertEquals(response.getErrors().get(0).getMessage(), "message");
        assertNull(response.getPersonFirstName());
    }

    @Test
    public void allFieldsMustBeEmptyWhenResponseContainsError() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = travelCalculatePremiumService.calculatePremium(request);
        assertNull(response.getPersonFirstName());
        assertNull(response.getPersonLastName());
        assertNull(response.getAgreementDateFrom());
        assertNull(response.getAgreementDateTo());
        assertNull(response.getAgreementPrice());
    }


    @Test
    public void shouldNOtBeInteractionWithDateTimeServiceWhenResponseContainsError() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = travelCalculatePremiumService.calculatePremium(request);
        verifyNoInteractions(dateTimeService);
    }
    private TravelCalculatePremiumRequest createReadyMadeRequest(){
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Olegs");
        request.setPersonLastName("Radigins");
        request.setAgreementDateFrom(new Date(2000,0,1));
        request.setAgreementDateTo(new Date(2000,1,1));
        return request;
    }


}