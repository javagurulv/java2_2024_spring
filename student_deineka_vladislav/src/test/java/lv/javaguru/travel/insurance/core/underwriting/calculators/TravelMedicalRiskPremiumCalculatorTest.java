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
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelMedicalRiskPremiumCalculatorTest {

    @Mock
    private DateTimeUtil dateTimeUtil;
    @Mock
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;
    @Mock
    private AgeCoefficientRepository ageCoefficientRepository;

    @InjectMocks
    private TravelMedicalRiskPremiumCalculator medicalRiskPremiumCalculator;
    private TravelCalculatePremiumRequest premiumRequest;

    @BeforeEach
    void setUp() {
        premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonBirthDate()).thenReturn(new Date());
    }

    @Test
    public void checkPremiumCalculating() {
        BigDecimal dayCounting = BigDecimal.valueOf(10);
        BigDecimal countryDefaultRate = BigDecimal.valueOf(20);
        BigDecimal ageCoef = BigDecimal.valueOf(1.2);

        LocalDate today = LocalDate.now();
        when(dateTimeUtil.getTodayDateTime()).thenReturn(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        when(dateTimeUtil.calculateDateFromTo(premiumRequest.getAgreementDateFrom(), premiumRequest.getAgreementDateTo())).thenReturn(dayCounting.longValue());
        CountryDefaultDayRate countryDefaultDayRate = mock(CountryDefaultDayRate.class);
        when(countryDefaultDayRate.getDefaultDayRate()).thenReturn(countryDefaultRate);
        when(countryDefaultDayRateRepository.findByCountryIc(premiumRequest.getCountry())).thenReturn(Optional.of(countryDefaultDayRate));
        AgeCoefficient ageCoefficientDomain = mock(AgeCoefficient.class);
        when(ageCoefficientDomain.getCoefficient()).thenReturn(ageCoef);
        when(ageCoefficientRepository.findCoefficient(ageCalculating(premiumRequest))).thenReturn(Optional.of(ageCoefficientDomain));
        BigDecimal premiumToExpect = countryDefaultRate.multiply(dayCounting).multiply(ageCoef)
                        .setScale(2, RoundingMode.HALF_UP);
        BigDecimal result = medicalRiskPremiumCalculator.calculatePremium(premiumRequest);
        assertEquals(premiumToExpect, result);
    }

    private Integer ageCalculating(TravelCalculatePremiumRequest premiumRequest) {
        LocalDate birthDate = toLocalDate(premiumRequest.getPersonBirthDate());
        LocalDate today = toLocalDate(dateTimeUtil.getTodayDateTime());
        return Period.between(birthDate, today).getYears();
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }


}
