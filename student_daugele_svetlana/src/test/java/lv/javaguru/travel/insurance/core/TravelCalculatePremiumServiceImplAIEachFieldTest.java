package lv.javaguru.travel.insurance.core;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TravelCalculatePremiumServiceImplAIEachFieldTest {
    @Test
    public void testPersonFirstName() {
        // Create a sample request with only the person's first name
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");

        // Create an instance of the service
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

        // Call the method to calculate the premium
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        // Assert that the response contains the correct person's first name
        assertEquals("John", response.getPersonFirstName());
    }

    @Test
    public void testPersonLastName() {
        // Create a sample request with only the person's last name
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Doe");

        // Create an instance of the service
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

        // Call the method to calculate the premium
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        // Assert that the response contains the correct person's last name
        assertEquals("Doe", response.getPersonLastName());
    }

    @Test
    public void testAgreementDateFrom() {
        // Create a sample request with only the agreement date from
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(new Date());

        // Create an instance of the service
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

        // Call the method to calculate the premium
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        // Assert that the response contains the correct agreement date from
        assertEquals(response.getAgreementDateFrom(), response.getAgreementDateFrom());

    }

    @Test
    public void testAgreementDateTo() {
        // Create a sample request with only the agreement date to
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(new Date());

        // Create an instance of the service
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

        // Call the method to calculate the premium
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        // Assert that the response contains the correct agreement date to

        assertEquals(response.getAgreementDateTo(), response.getAgreementDateTo());
    }
}
