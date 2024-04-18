package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {
private TravelCalculatePremiumServiceImpl travelCalculatePremiumService = new TravelCalculatePremiumServiceImpl();
    @Test
    public void shouldTestResponse() {
        String personFirstName = "Olegs";
        String personLastName = "Radigins";
        Date agreementDateFrom = new Date(2000, Calendar.JANUARY,1);
        Date agreementDateTo = new Date(2000, Calendar.FEBRUARY,1);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(personFirstName,personLastName,agreementDateFrom,agreementDateTo);
        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertEquals(response.getPersonFirstName(), personFirstName);
        assertEquals(response.getPersonLastName(), personLastName);
        assertEquals(response.getAgreementDateFrom(),agreementDateFrom);
        assertEquals(response.getAgreementDateTo(),agreementDateTo);


    }

}