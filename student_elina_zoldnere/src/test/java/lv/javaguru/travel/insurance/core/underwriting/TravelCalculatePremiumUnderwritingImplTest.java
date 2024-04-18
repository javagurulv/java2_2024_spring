package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumUnderwritingImplTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private TravelRiskPremiumCalculator calculatorMock1;
    @Mock
    private TravelRiskPremiumCalculator calculatorMock2;
    @Mock
    private List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    @InjectMocks
    private TravelCalculatePremiumUnderwritingImpl calculateUnderwriting;

    @Test
    public void calculateAgreementPremium_ShouldReturnCorrectResultForOneValidRisk() {
        when(requestMock.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(riskPremiumCalculators.stream()).thenReturn(Stream.of(calculatorMock1));

        when(calculatorMock1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(calculatorMock1.calculateRiskPremium(requestMock)).thenReturn(BigDecimal.ONE);

        TravelPremiumCalculationResult result = calculateUnderwriting.calculateAgreementPremium(requestMock);
        assertEquals(BigDecimal.ONE, result.getAgreementPremium());
        assertEquals(1, result.getRiskPremiums().size());
    }

    @Test
    public void calculateAgreementPremium_ShouldReturnCorrectResultForTwoValidRisks() {
        when(requestMock.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION"));
        when(riskPremiumCalculators.stream()).thenAnswer(invocation -> Stream.of(calculatorMock1, calculatorMock2));

        when(calculatorMock1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(calculatorMock2.getRiskIc()).thenReturn("TRAVEL_CANCELLATION");

        when(calculatorMock1.calculateRiskPremium(requestMock)).thenReturn(BigDecimal.ONE);
        when(calculatorMock2.calculateRiskPremium(requestMock)).thenReturn(BigDecimal.ONE);

        TravelPremiumCalculationResult result = calculateUnderwriting.calculateAgreementPremium(requestMock);
        assertEquals(BigDecimal.valueOf(2), result.getAgreementPremium());
        assertEquals(2, result.getRiskPremiums().size());
    }

    @Test
    public void calculateAgreementPremium_ThrowsRuntimeExceptionWhenOnlyNotSupportedRisk() {
        when(requestMock.getSelectedRisks()).thenReturn(List.of("NOT_SUPPORTED_RISK"));

        assertThrows(RuntimeException.class, () -> calculateUnderwriting.calculateAgreementPremium(requestMock));
    }

    @Test
    public void calculateAgreementPremium_ThrowsRuntimeExceptionWhenSelectedRisksContainNonSupportedRisk() {
        when(requestMock.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "NOT_SUPPORTED_RISK"));
        when(riskPremiumCalculators.stream()).thenAnswer(invocation -> Stream.of(calculatorMock1, calculatorMock2));

        when(calculatorMock1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(calculatorMock2.getRiskIc()).thenReturn("NOT_SUPPORTED_RISK");

        when(calculatorMock1.calculateRiskPremium(requestMock)).thenReturn(BigDecimal.ONE);
        assertThrows(RuntimeException.class, () -> calculateUnderwriting.calculateAgreementPremium(requestMock));
    }

}