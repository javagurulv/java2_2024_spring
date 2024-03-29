package lv.javaguru.travel.insurance.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelCalculatePremiumRequestValidator validateMock;

    @Mock
    private TravelCalculatePremiumUnderwriting calculateUnderwritingMock;

    @Mock
    private DateTimeService dateTimeServiceMock;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl calculate;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void setUp() {
        request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Jānis");
        request.setPersonLastName("Bērziņš");
        request.setAgreementDateFrom(new Date(2025 - 1900, 2, 10)); // March 10, 2025
        request.setAgreementDateTo(new Date(2025 - 1900, 2, 11)); // March 11, 2025
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

    @Test
    public void calculatePremium_ShouldReturnCorrectAgreementDateFormat() throws JsonProcessingException {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();

        when(validateMock.validate(request)).thenReturn(emptyList());
        when(calculateUnderwritingMock.calculateAgreementPrice(agreementDateFrom, agreementDateTo))
                .thenReturn(BigDecimal.valueOf(1));
        TravelCalculatePremiumResponse response = calculatePremiumTest();

        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getTimeZone("EET"));
        String responseToJson = mapper.writeValueAsString(response);
        String expectedJson = "{\"errors\":null,\"personFirstName\":\"Jānis\",\"personLastName\":\"Bērziņš\"," +
                "\"agreementDateFrom\":\"2025-03-10\",\"agreementDateTo\":\"2025-03-11\",\"agreementPrice\":1}";
        assertEquals(expectedJson, responseToJson);
    }

    private TravelCalculatePremiumResponse calculatePremiumTest() {
        return calculate.calculatePremium(request);
    }

}