package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    TravelCalculatePremiumServiceImpl travelCalculatePremiumServiceimpl = new TravelCalculatePremiumServiceImpl();

    @Test
    public void shouldBeFilledFirstName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Alex");

        TravelCalculatePremiumResponse response = travelCalculatePremiumServiceimpl.calculatePremium(request);

        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }

    public void shouldBeFilledLastName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();

        request.setPersonLastName("Ivanov");

        TravelCalculatePremiumResponse response = travelCalculatePremiumServiceimpl.calculatePremium(request);;
        assertEquals(response.getPersonLastName(), request.getPersonLastName());

    }

    void shouldBeFilledAgreementDateFrom() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();

        request.setAgreementDateFrom(new Date());

        TravelCalculatePremiumResponse response = travelCalculatePremiumServiceimpl.calculatePremium(request);

        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());

    }

    void shouldBeFilledAgreementDateTo() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();

        request.setAgreementDateTo(new Date());

        TravelCalculatePremiumResponse response = travelCalculatePremiumServiceimpl.calculatePremium(request);

        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }
}