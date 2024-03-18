/*
package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplAIEachFieldTest {

    @Test
    void testCalculatePremium_setsPersonFirstName() {
        // Given
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");

        // When
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        // Then
        assertEquals("John", response.getPersonFirstName());
    }

    @Test
    void testCalculatePremium_setsPersonLastName() {
        // Given
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Doe");

        // When
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        // Then
        assertEquals("Doe", response.getPersonLastName());
    }

    @Test
    void testCalculatePremium_setsAgreementDateFrom() {
        // Given
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        Date fromDate = new Date(); // Replace this with your actual Date object
        request.setAgreementDateFrom(fromDate);

        // When
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        // Then
        assertEquals(fromDate, response.getAgreementDateFrom());
    }

    @Test
    void testCalculatePremium_setsAgreementDateTo() {
        // Given
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        Date toDate = new Date();   // Replace this with your actual Date object
        request.setAgreementDateTo(toDate);

        // When
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        // Then
        assertEquals(toDate, response.getAgreementDateTo());
    }
}
*/