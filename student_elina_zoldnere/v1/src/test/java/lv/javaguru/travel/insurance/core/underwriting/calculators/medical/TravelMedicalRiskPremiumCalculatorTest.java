package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelMedicalRiskPremiumCalculatorTest {

    @Mock
    private TravelCalculatePremiumRequestV1 requestMock;
    @Mock
    private DayCountCalculator dayCountCalculatorMock;
    @Mock
    private CountryDefaultDayRateRetriever countryDefaultDayRateRetrieverMock;
    @Mock
    private AgeCoefficientRetriever ageCoefficientRetrieverMock;
    @Mock
    private MedicalRiskLimitLevelCoefficientRetriever limitLevelCoefficientRetrieverMock;

    @InjectMocks
    private TravelMedicalRiskPremiumCalculator medicalRiskPremiumCalculator;

    @Test
    void calculateRiskPremium_shouldCalculateCorrectResult() {
        BigDecimal dayCount = BigDecimal.ONE;
        BigDecimal countryDefaultDayRate = BigDecimal.valueOf(2.5);
        BigDecimal ageCoefficient = BigDecimal.valueOf(1.1);
        BigDecimal limitLevelCoefficient = BigDecimal.valueOf(1.2);

        when(dayCountCalculatorMock.calculateDayCount(requestMock)).thenReturn(dayCount);
        when(countryDefaultDayRateRetrieverMock.findCountryDefaultDayRate(requestMock))
                .thenReturn(countryDefaultDayRate);
        when(ageCoefficientRetrieverMock.setAgeCoefficient(requestMock)).thenReturn(ageCoefficient);
        when(limitLevelCoefficientRetrieverMock.setLimitLevelCoefficient(requestMock))
                .thenReturn(limitLevelCoefficient);

        BigDecimal expectedPremium = countryDefaultDayRate
                .multiply(dayCount)
                .multiply(ageCoefficient)
                .multiply(limitLevelCoefficient)
                .setScale(2, RoundingMode.HALF_UP);;
        BigDecimal actualPremium = medicalRiskPremiumCalculator.calculateRiskPremium(requestMock);

        assertEquals(expectedPremium, actualPremium);
    }

}
