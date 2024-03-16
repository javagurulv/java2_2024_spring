package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplAIOneTest {

    @Test
    void testCalculatePremium() {
        // Given
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");
        request.setPersonLastName("Doe");
        Date fromDate = new Date(); // Replace this with your actual Date object
        Date toDate = new Date();   // Replace this with your actual Date object
        request.setAgreementDateFrom(fromDate);
        request.setAgreementDateTo(toDate);

        // When
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        // Then
        assertEquals("John", response.getPersonFirstName());
        assertEquals("Doe", response.getPersonLastName());
        assertEquals(fromDate, response.getAgreementDateFrom());
        assertEquals(toDate, response.getAgreementDateTo());
    }
}