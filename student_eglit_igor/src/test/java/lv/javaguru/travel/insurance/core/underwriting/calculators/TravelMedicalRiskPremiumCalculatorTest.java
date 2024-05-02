package lv.javaguru.travel.insurance.core.underwriting.calculators;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TravelMedicalRiskPremiumCalculatorTest {

    /*@Mock private DateTimeUtil dateTimeUtil;
    @Mock private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    @InjectMocks
    private TravelMedicalRiskPremiumCalculator calculator;

    @Test
    public void shouldCalculatePremium() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getCountry()).thenReturn("SPAIN");
        when(dateTimeUtil.daysCalculator(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)));
        CountryDefaultDayRate countryDefaultDayRate = mock(CountryDefaultDayRate.class);
        when(countryDefaultDayRate.getDefaultDayRate()).thenReturn(BigDecimal.TEN);
        when(countryDefaultDayRateRepository.findByCountryIc("SPAIN")).thenReturn(Optional.of(countryDefaultDayRate));
        BigDecimal premium = calculator.calculatePremium(request);
        assertEquals(premium.stripTrailingZeros(),
                new BigDecimal("20").stripTrailingZeros());
    }
*/
}


