package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void calculatePremium_ShouldReturnCorrectResponse() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Jānis");
        request.setPersonLastName("Bērziņš");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());

        TravelCalculatePremiumResponse response = new TravelCalculatePremiumServiceImpl().calculatePremium(request);

        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

}