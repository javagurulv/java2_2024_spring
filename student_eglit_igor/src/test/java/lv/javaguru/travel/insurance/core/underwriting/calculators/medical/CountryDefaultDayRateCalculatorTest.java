package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryDefaultDayRateCalculatorTest {

    @Mock
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    @InjectMocks
    private CountryDefaultDayRateCalculator calculator;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        request = new TravelCalculatePremiumRequest();
        request.setCountry("LATVIA");
    }

    @Test
    void shouldFindCountryDefaultDayRateWhenRateExists() {
        CountryDefaultDayRate defaultDayRate = new CountryDefaultDayRate();
        defaultDayRate.setDefaultDayRate(BigDecimal.valueOf(1.00));

        when(countryDefaultDayRateRepository.findByCountryIc("LATVIA"))
                .thenReturn(Optional.of(defaultDayRate));

        BigDecimal result = calculator.findCountryDefaultDayRate(request);

        assertEquals(BigDecimal.valueOf(1.00), result);
    }

    @Test
    void shouldThrowExceptionWhenCountryDefaultDayRateNotFound() {
        when(countryDefaultDayRateRepository.findByCountryIc("LV")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> calculator.findCountryDefaultDayRate(request));
    }
}
