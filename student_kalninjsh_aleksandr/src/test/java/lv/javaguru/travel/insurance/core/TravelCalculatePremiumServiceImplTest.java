package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl premiumService = new TravelCalculatePremiumServiceImpl();

    @Test
    public void personFirstName() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
        premiumRequest.setPersonFirstName("Tom");
        TravelCalculatePremiumResponse premiumResponse = premiumService.calculatePremium(premiumRequest);
        assertEquals(premiumResponse.getPersonFirstName(), "Tom");
    }

}