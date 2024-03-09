package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {
    TravelCalculatePremiumService travelCalculatePremiumService = new TravelCalculatePremiumServiceImpl();
    String firstName = "Olegs";
    String lastName =  "Radigins";
    Date dateFrom = new Date(1);
    Date dateTo = new Date(2);

    @Test
    public void shouldTestResponse() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName(firstName);
        request.setPersonLastName(lastName);
        request.setAgreementDateFrom(dateFrom);
        request.setAgreementDateTo(dateTo);

        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertEquals(response.getPersonFirstName(), firstName);
        assertEquals(response.getPersonLastName(),lastName);
        assertEquals(response.getAgreementDateFrom(),dateFrom);
        assertEquals(response.getAgreementDateTo(),dateTo);



    }

}