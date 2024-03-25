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
    Date dateFrom = new  Date(2000,0,1);;
    Date dateTo = new Date(2000,1,1);;

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
    @Test
    public void shouldTestResponseFirstNameField(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName(firstName);

        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertEquals(response.getPersonFirstName(), firstName);
    }


    @Test
    public void shouldTestResponseLastNameField(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName(lastName);

        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertEquals(response.getPersonLastName(), lastName);
    }

    @Test
    public void shouldTestResponseDateFromField(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(dateFrom);

        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertEquals(response.getAgreementDateFrom(), dateFrom);
    }

    @Test
    public void shouldTestResponseDateToField(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(dateTo);

        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertEquals(response.getAgreementDateTo(), dateTo);
    }

}