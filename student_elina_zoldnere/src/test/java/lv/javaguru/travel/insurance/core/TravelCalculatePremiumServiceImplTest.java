package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private DateTimeService dateTimeServiceMock;

    @Mock
    private TravelCalculatePremiumRequestValidator validate;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl calculate;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void setUp() {
        request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Jānis");
        request.setPersonLastName("Bērziņš");
        request.setAgreementDateFrom(new Date(2024 - 1900, 2, 10)); // March 10, 2024
        request.setAgreementDateTo(new Date(2024 - 1900, 2, 11)); // March 11, 2024
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectPersonFirstName() {
        TravelCalculatePremiumResponse response = calculatePremiumTest();
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectPersonLastName() {
        TravelCalculatePremiumResponse response = calculatePremiumTest();
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectAgreementDateFrom() {
        TravelCalculatePremiumResponse response = calculatePremiumTest();
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectAgreementDateTo() {
        TravelCalculatePremiumResponse response = calculatePremiumTest();
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    private TravelCalculatePremiumResponse calculatePremiumTest() {
        return calculate.calculatePremium(request);
    }

}