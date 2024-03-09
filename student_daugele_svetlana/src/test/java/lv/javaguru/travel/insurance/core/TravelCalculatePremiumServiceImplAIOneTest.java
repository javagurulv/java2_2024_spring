package lv.javaguru.travel.insurance.core;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
public class TravelCalculatePremiumServiceImplAIOneTest {
    @Test
    public void testCalculatePremium() {
        // Create a sample request
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");
        request.setPersonLastName("Doe");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());

        // Create an instance of the TravelInsuranceCalculator
        TravelCalculatePremiumServiceImpl calculator = new TravelCalculatePremiumServiceImpl();

        // Call the method to calculate the premium
        TravelCalculatePremiumResponse response = calculator.calculatePremium(request);

        // Assert that the response object is not null
        assertNotNull(response);

        // Assert that the response contains correct values from the request
        assertEquals("John", response.getPersonFirstName());
        assertEquals("Doe", response.getPersonLastName());
        assertEquals(response.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(response.getAgreementDateTo(), response.getAgreementDateTo());
    }
}
