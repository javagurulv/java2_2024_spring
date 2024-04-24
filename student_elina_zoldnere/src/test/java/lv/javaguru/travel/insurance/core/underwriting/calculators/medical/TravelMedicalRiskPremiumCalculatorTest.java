package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelMedicalRiskPremiumCalculatorTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private DayCountCalculator dayCountCalculatorMock;
    @Mock
    private CountryDefaultDayRateRetriever countryDefaultDayRateRetrieverMock;
    @Mock
    private AgeCoefficientRetriever ageCoefficientRetrieverMock;

    @InjectMocks
    private TravelMedicalRiskPremiumCalculator medicalRiskPremiumCalculator;

    @Test
    public void calculateRiskPremium_shouldCalculateCorrectResult() {
        BigDecimal dayCount = BigDecimal.ONE;
        BigDecimal countryDefaultDayRate = BigDecimal.valueOf(2.5);
        BigDecimal ageCoefficient = BigDecimal.valueOf(1.1);

        when(dayCountCalculatorMock.calculateDayCount(requestMock)).thenReturn(dayCount);
        when(countryDefaultDayRateRetrieverMock.findCountryDefaultDayRate(requestMock))
                .thenReturn(countryDefaultDayRate);
        when(ageCoefficientRetrieverMock.findAgeCoefficient(requestMock)).thenReturn(ageCoefficient);

        BigDecimal expectedPremium = countryDefaultDayRate.multiply(dayCount).multiply(ageCoefficient);
        BigDecimal actualPremium = medicalRiskPremiumCalculator.calculateRiskPremium(requestMock);

        assertEquals(expectedPremium, actualPremium);
    }

}
