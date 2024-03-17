package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelCalculatePremiumImplForEachModuleTest {
    TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

    @Test
    void testPersonFirsName() {
        TravelCalculatePremiumRequest request= new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Jelena");
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }
    @Test

    void testPersonLastName() {
        TravelCalculatePremiumRequest request= new TravelCalculatePremiumRequest();
        request.setPersonLastName("Vavere");
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
    }
    @Test
    void AgreementDateFrom() {
        TravelCalculatePremiumRequest request= new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(new Date ());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @Test
    void AgreementDateTo() {
        TravelCalculatePremiumRequest request= new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(new Date ());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

}
