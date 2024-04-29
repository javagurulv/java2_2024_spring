package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private DateTimeService service;
    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl premiumService;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void setUp() {
        request = checkingTheRequestForAllFields();
        when(service.calculateAgreementDaysBetweenDates(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(5L);
        when(requestValidator.validate(request)).thenReturn(List.of());
    }

    @Test
    public void personFirstName() {
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }

    @Test
    public void personLastName() {
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
    }

    @Test
    public void agreementDateFrom() {
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @Test
    public void agreementDateTo() {
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    public void calculateAgreementDaysBetweenDates() {
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getAgreementPrice(), new BigDecimal(5));

    }

    private TravelCalculatePremiumRequest checkingTheRequestForAllFields() {
        request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Tom");
        request.setPersonLastName("Sawyer");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
        return request;

    }


}