package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;


class TravelCalculatePremiumServiceImplTest {
    private DateTimeService dateTimeService;
    private TravelCalculatePremiumServiceImpl service;
    private TravelCalculatePremiumRequest request;
    @BeforeEach
    public void setUp() {
        request = createRequestWithAllFields();
        dateTimeService = mock(DateTimeService.class);
        when(dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(10L);
        service = new TravelCalculatePremiumServiceImpl(dateTimeService);
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
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        BigDecimal agreementPrice = new BigDecimal(10);
        assertEquals(response.getAgreementPrice(),agreementPrice);
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