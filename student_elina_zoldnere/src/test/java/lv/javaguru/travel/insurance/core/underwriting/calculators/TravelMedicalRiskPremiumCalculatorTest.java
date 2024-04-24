package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelMedicalRiskPremiumCalculatorTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private DateTimeUtil dateTimeUtilMock;
    @Mock
    private CountryDefaultDayRateRepository countryDefaultDayRateRepositoryMock;
    @Mock
    private AgeCoefficientRepository ageCoefficientRepositoryMock;

    @InjectMocks
    private TravelMedicalRiskPremiumCalculator medicalRiskPremiumCalculator;

    @Test
    public void calculateRiskPremium_shouldCalculateCorrectResult() {
        BigDecimal days = BigDecimal.ONE;
        BigDecimal countryDefaultDayRate = BigDecimal.valueOf(2.5);
        BigDecimal ageCoefficient = BigDecimal.valueOf(1.1);

        when(dateTimeUtilMock.calculateDifferenceBetweenDatesInDays(any(), any())).thenReturn(days.longValue());

        CountryDefaultDayRate countryDefaultDayRateMock = mock(CountryDefaultDayRate.class);
        when(countryDefaultDayRateRepositoryMock.findByCountryIc(any()))
                .thenReturn(Optional.of(countryDefaultDayRateMock));
        when(countryDefaultDayRateMock.getDefaultDayRate()).thenReturn(countryDefaultDayRate);

        AgeCoefficient ageCoefficientMock = mock(AgeCoefficient.class);
        when(ageCoefficientRepositoryMock.findCoefficient(any())).thenReturn(Optional.of(ageCoefficientMock));
        when(ageCoefficientMock.getCoefficient()).thenReturn(ageCoefficient);

        BigDecimal expectedPremium = countryDefaultDayRate.multiply(days).multiply(ageCoefficient);
        BigDecimal actualPremium = medicalRiskPremiumCalculator.calculateRiskPremium(requestMock);

        assertEquals(expectedPremium, actualPremium);
    }

}
