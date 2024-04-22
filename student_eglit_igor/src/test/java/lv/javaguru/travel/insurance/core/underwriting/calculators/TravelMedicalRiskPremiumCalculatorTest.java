package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelMedicalRiskPremiumCalculatorTest {

    @Mock
    private DateTimeUtil dateTimeUtil;

    @Mock
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    @InjectMocks
    private TravelMedicalRiskPremiumCalculator calculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCalculatePremium() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn("SPAIN");
        when(dateTimeUtil.daysCalculator(any(), any())).thenReturn(2L);
        CountryDefaultDayRate countryDefaultDayRate = mock(CountryDefaultDayRate.class);
        when(countryDefaultDayRate.getDefaultDayRate()).thenReturn(BigDecimal.TEN);
        when(countryDefaultDayRateRepository.findByCountryIc("SPAIN")).thenReturn(Optional.of(countryDefaultDayRate));
        BigDecimal premium = calculator.calculatePremium(request);
        assertEquals(premium.stripTrailingZeros(),
                new BigDecimal("20").stripTrailingZeros());
    }

    @Test
    @DisplayName("Should throw exception when country day rate is not found")
    void shouldThrowExceptionWhenCountryDayRateIsNotFound() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setCountry("LV");
        request.setAgreementDateFrom(LocalDate.of(2025, 1, 1));
        request.setAgreementDateTo(LocalDate.of(2025, 1, 10));

        when(dateTimeUtil.daysCalculator(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(10L);
        when(countryDefaultDayRateRepository.findByCountryIc(request.getCountry()))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> calculator.calculatePremium(request));
    }
}