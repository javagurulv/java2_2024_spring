package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

    @Test
    public void checkPersonFirstName(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Vladislav");
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response = service.calculatePremium(request);
        assertEquals(request.getPersonFirstName(),response.getPersonFirstName());
    }
    @Test
    public void checkPersonLastName()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Kuznetsov");
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response = service.calculatePremium(request);
        assertEquals(request.getPersonLastName(),response.getPersonLastName());
    }

    @Test
    public void checkAgreementDateFrom()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        request.setAgreementDateFrom(new Date());
        response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateFrom(),response.getAgreementDateFrom());

    }

    @Test
    public void checkAgreementDateTo()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        request.setAgreementDateTo(new Date());
        response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(),response.getAgreementDateTo());

    }


}