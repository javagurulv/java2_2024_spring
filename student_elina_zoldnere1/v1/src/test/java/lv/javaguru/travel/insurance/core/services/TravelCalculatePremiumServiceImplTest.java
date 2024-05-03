package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.TravelCalculatePremiumUnderwriting;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;

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
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelCalculatePremiumRequestValidator validateMock;
    @Mock
    private TravelCalculatePremiumUnderwriting calculateUnderwritingMock;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl calculate;

    private TravelCalculatePremiumRequestV1 request;

    @BeforeEach
    public void setUp() {
        request = new TravelCalculatePremiumRequestV1();
        request.setPersonFirstName("Jānis");
        request.setPersonLastName("Bērziņš");
        request.setPersonBirthDate(new Date(90, 1, 1));
        request.setAgreementDateFrom(new Date(2025 - 1900, 2, 10)); // March 10, 2025
        request.setAgreementDateTo(new Date(2025 - 1900, 2, 11)); // March 11, 2025
        request.setSelectedRisks(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"));
        request.setCountry("SPAIN");

        when(validateMock.validate(request)).thenReturn(emptyList());
        when(calculateUnderwritingMock.calculateAgreementPremium(request))
                .thenReturn(new TravelPremiumCalculationResult(BigDecimal.valueOf(2.50),
                        List.of(new RiskPremium("TRAVEL_MEDICAL", BigDecimal.valueOf(2.50)),
                                new RiskPremium("TRAVEL_CANCELLATION", BigDecimal.ZERO),
                                new RiskPremium("TRAVEL_LOSS_BAGGAGE", BigDecimal.ZERO))));
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectPersonFirstName() {
        TravelCalculatePremiumResponseV1 response = calculatePremiumTest();
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectPersonLastName() {
        TravelCalculatePremiumResponseV1 response = calculatePremiumTest();
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectPersonBirthDate() {
        TravelCalculatePremiumResponseV1 response = calculatePremiumTest();
        assertEquals(request.getPersonBirthDate(), response.getPersonBirthDate());
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectAgreementDateFrom() {
        TravelCalculatePremiumResponseV1 response = calculatePremiumTest();
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectAgreementDateTo() {
        TravelCalculatePremiumResponseV1 response = calculatePremiumTest();
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectAgreementPremium() {
        TravelCalculatePremiumResponseV1 response = calculatePremiumTest();
        assertEquals(BigDecimal.valueOf(2.50), response.getAgreementPremium());
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectRiskDetails() {
        TravelCalculatePremiumResponseV1 response = calculatePremiumTest();
        List<RiskPremium> riskPremiums = response.getRiskPremiums();

        assertEquals(3, riskPremiums.size());

        RiskPremium medicalRisk = riskPremiums.get(0);
        assertEquals("TRAVEL_MEDICAL", medicalRisk.getRiskIc());
        assertEquals(BigDecimal.valueOf(2.50), medicalRisk.getPremium());

        RiskPremium cancellationRisk = riskPremiums.get(1);
        assertEquals("TRAVEL_CANCELLATION", cancellationRisk.getRiskIc());
        assertEquals(BigDecimal.ZERO, cancellationRisk.getPremium());

        RiskPremium lossBaggageRisk = riskPremiums.get(2);
        assertEquals("TRAVEL_LOSS_BAGGAGE", lossBaggageRisk.getRiskIc());
        assertEquals(BigDecimal.ZERO, lossBaggageRisk.getPremium());
    }

    private TravelCalculatePremiumResponseV1 calculatePremiumTest() {
        return calculate.calculatePremium(request);
    }

}