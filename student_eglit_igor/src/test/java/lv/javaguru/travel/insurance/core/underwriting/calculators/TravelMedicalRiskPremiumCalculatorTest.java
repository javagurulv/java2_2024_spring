package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelMedicalRiskPremiumCalculatorTest {


  /*  @Mock
    private DateTimeUtil dateTimeUtil;
    @Mock
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;
    @Mock
    private AgeCoefficientRepository ageCoefficientRepository;

    @InjectMocks
    private TravelMedicalRiskPremiumCalculator calculator;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonBirthDate()).thenReturn(LocalDate.now().minusYears(30));
    }

    @Test
    void shouldCalculatePremiumCorrectly() {
        BigDecimal daysCount = BigDecimal.valueOf(10);
        BigDecimal countryDefaultRate = BigDecimal.valueOf(20);
        BigDecimal ageCoefficient = BigDecimal.valueOf(1.2);

        when(dateTimeUtil.daysCalculator(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(daysCount.longValue());
        CountryDefaultDayRate countryDefaultDayRate = mock(CountryDefaultDayRate.class);
        when(countryDefaultDayRate.getDefaultDayRate()).thenReturn(countryDefaultRate);
        when(countryDefaultDayRateRepository.findByCountryIc(request.getCountry())).thenReturn(Optional.of(countryDefaultDayRate));
        AgeCoefficient ageCoefficientDomain = mock(AgeCoefficient.class);
        when(ageCoefficientDomain.getCoefficient()).thenReturn(ageCoefficient);
        when(ageCoefficientRepository.findAgeCoefficientByAge(calculateAge(request))).thenReturn(Optional.of(ageCoefficientDomain));

        BigDecimal expectedPremium = countryDefaultRate.multiply(daysCount).multiply(ageCoefficient)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal result = calculator.calculatePremium(request);

        assertEquals(expectedPremium, result);
    }

    private Integer calculateAge(TravelCalculatePremiumRequest request) {
        LocalDate personBirthDate = request.getPersonBirthDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(personBirthDate, currentDate).getYears();
    }
*/
}



