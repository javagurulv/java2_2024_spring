package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

ValidationError validationError = new ValidationError();
    @Mock
    private DateTimeService dateTimeService;
    @Mock TravelCalculatePremiumRequestValidator requestValidator;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    private TravelCalculatePremiumRequest request;
    @BeforeEach
    public void setUp() {
        request = createRequestWithAllFields();
   }
    @Test
    public void checkPersonFirstName(){
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(request.getPersonFirstName(),response.getPersonFirstName());
    }
    @Test
    public void checkPersonLastName()
    {
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(request.getPersonLastName(),response.getPersonLastName());
    }

    @Test
    public void checkAgreementDateFrom()
    {
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateFrom(),response.getAgreementDateFrom());

    }

    @Test
    public void checkAgreementDateTo()
    {
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(),response.getAgreementDateTo());
    }

    @Test
    public void checkCalculatedAgreementPrice()
    {
        when(dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(10L);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        BigDecimal agreementPrice = new BigDecimal(10);
        assertEquals(response.getAgreementPrice(),agreementPrice);
    }

    @Test
    public void checkThatValidationErrorIsPresent (){
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("Empty","All fields are empty !");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
    }

    @Test
    public void checkValidationErrorCountIsCorrect(){
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("Empty","All fields are empty !");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = service.calculatePremium(request);
        assertEquals(response.getErrors().size(), 1);
    }

    public TravelCalculatePremiumRequest createRequestWithAllFields (){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(new Date(2024, 3, 11));
        request.setAgreementDateFrom(new Date(2024, 3, 1));
        request.setPersonFirstName("Vladislav");
        request.setPersonLastName("Kuznetsov");
        return request;
    }


}