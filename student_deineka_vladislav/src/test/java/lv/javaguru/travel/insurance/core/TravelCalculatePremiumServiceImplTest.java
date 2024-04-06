package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock private TravelCalculatePremiumRequestValidator travelCalculatePremiumRequestValidator;
    @Mock private DateTimeService dateTimeService;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl premiumService;


    @Test
    public void checkResponseFirstName() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = createdFullRequest();
        when(dateTimeService.calculateDateFromTo(travelCalculatePremiumRequest.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateTo())).thenReturn(0L);
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(List.of());
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getPersonFirstName(), travelCalculatePremiumRequest.getPersonFirstName());
    }

    @Test
    public void checkResponseLastName() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = createdFullRequest();
        when(dateTimeService.calculateDateFromTo(travelCalculatePremiumRequest.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateTo())).thenReturn(0L);
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(List.of());
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getPersonLastName(), travelCalculatePremiumRequest.getPersonLastName());
    }

    @Test
    public void checkResponseDateFrom() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = createdFullRequest();
        when(dateTimeService.calculateDateFromTo(travelCalculatePremiumRequest.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateTo())).thenReturn(0L);
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(List.of());
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateFrom());
    }

    @Test
    public void checkResponseDateTo() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = createdFullRequest();
        when(dateTimeService.calculateDateFromTo(travelCalculatePremiumRequest.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateTo())).thenReturn(0L);
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(List.of());
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getAgreementDateTo(), travelCalculatePremiumRequest.getAgreementDateTo());
    }

    @Test
    public void checkResponseAgreementPrice() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = createdFullRequest();
        when(dateTimeService.calculateDateFromTo(travelCalculatePremiumRequest.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateTo())).thenReturn(0L);
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(List.of());
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertNotNull(travelCalculatePremiumResponse.getAgreementPrice());
    }

    @Test
    public void checkResponseWithErrors() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = createdFullRequest();
        ValidationErrors validationErrors = new ValidationErrors("field", "message");
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(List.of(validationErrors));
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertTrue(travelCalculatePremiumResponse.errorsFound());
    }

    @Test
    public void checkResponseWithRightErrorCounting() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = createdFullRequest();
        ValidationErrors validationErrors = new ValidationErrors("field", "message");
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(List.of(validationErrors));
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getValidationErrors().size(), 1);
    }

    @Test
    public void checkResponseWithRightError() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = createdFullRequest();
        ValidationErrors validationErrors = new ValidationErrors("field", "message");
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(List.of(validationErrors));
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getValidationErrors().get(0).getField(), "field");
        assertEquals(travelCalculatePremiumResponse.getValidationErrors().get(0).getMessage(), "message");

    }

    @Test
    public void checkIfFieldsEmptyWhenResponseWithError() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = createdFullRequest();
        ValidationErrors validationErrors = new ValidationErrors("field", "message");
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(List.of(validationErrors));
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(travelCalculatePremiumRequest);     // зачем это здесь? без него тест валится
        verifyNoInteractions(dateTimeService);

    }


    private TravelCalculatePremiumRequest createdFullRequest() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
        premiumRequest.setPersonFirstName("Bob");
        premiumRequest.setPersonLastName("Johnson");
        premiumRequest.setAgreementDateFrom(new Date());
        premiumRequest.setAgreementDateTo(new Date());
        return premiumRequest;
    }

}