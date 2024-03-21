package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl premiumService = new TravelCalculatePremiumServiceImpl();

    @Test
    public void checkResponseFirstName() {
       TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
       premiumRequest.setPersonFirstName("Bob");
       TravelCalculatePremiumResponse premiumResponse = premiumService.calculatePremium(premiumRequest);
       assertEquals(premiumResponse.getPersonFirstName(), premiumRequest.getPersonFirstName());
   }

   @Test
   public void checkResponseLastName() {
       TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
       premiumRequest.setPersonLastName("Johnson");
       TravelCalculatePremiumResponse premiumResponse = premiumService.calculatePremium(premiumRequest);
       assertEquals(premiumResponse.getPersonLastName(), premiumRequest.getPersonLastName());

   }

   @Test
   public void checkResponseDateFrom() {
       TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
       premiumRequest.setAgreementDateFrom(new Date());
       TravelCalculatePremiumResponse premiumResponse = premiumService.calculatePremium(premiumRequest);
       assertEquals(premiumResponse.getAgreementDateFrom(), premiumRequest.getAgreementDateFrom());

   }

   @Test
   public void checkResponseDateTo() {
       TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
       premiumRequest.setAgreementDateTo(new Date());
       TravelCalculatePremiumResponse premiumResponse = premiumService.calculatePremium(premiumRequest);
       assertEquals(premiumResponse.getAgreementDateTo(), premiumRequest.getAgreementDateTo());

   }

   private DateTimeService dateTimeService = new DateTimeService();

   @Test
   public void checkDateTimeService() {
        TravelCalculatePremiumRequest travelRequest= new TravelCalculatePremiumRequest();
        travelRequest.setAgreementDateFrom(new Date());
        travelRequest.setAgreementDateTo(new Date());
        long travelCalculatePremiumRequest = travelRequest.getAgreementDateFrom().getTime() - travelRequest.getAgreementDateTo().getTime();
        long travelCalculatePremiumResponse = dateTimeService.calculateDateFromTo(travelRequest);

        assertEquals(travelCalculatePremiumResponse, travelCalculatePremiumRequest);

   }





}