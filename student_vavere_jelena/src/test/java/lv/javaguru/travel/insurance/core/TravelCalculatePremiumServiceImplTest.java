package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void testCalculatePremium() {
    TravelCalculatePremiumRequest request =new TravelCalculatePremiumRequest();
    request.setPersonFirstName("Jelena");
    request.setPersonLastName("Vavere");
    request.setAgreementDateFrom(new Date());
    request.setAgreementDateTo(new Date());

    TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();


        TravelCalculatePremiumResponse response = service.calculatePremium(request);

    assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    assertEquals(response.getPersonLastName(), request.getPersonLastName());
    assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());


    }

}