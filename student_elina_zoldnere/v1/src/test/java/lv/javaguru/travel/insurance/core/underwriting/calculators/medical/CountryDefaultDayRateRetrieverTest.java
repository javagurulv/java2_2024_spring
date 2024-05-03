package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryDefaultDayRateRetrieverTest {

    @Mock
    private TravelCalculatePremiumRequestV1 requestMock;
    @Mock
    private CountryDefaultDayRateRepository countryDefaultDayRateRepositoryMock;

    @InjectMocks
    private CountryDefaultDayRateRetriever countryDefaultDayRateRetriever;

    @Test
    void findCountryDefaultDayRate_shouldFindRateWhenRateExists() {
        BigDecimal countryDefaultDayRate = BigDecimal.valueOf(2.5);

        CountryDefaultDayRate countryDefaultDayRateMock = mock(CountryDefaultDayRate.class);
        when(countryDefaultDayRateRepositoryMock.findByCountryIc(any()))
                .thenReturn(Optional.of(countryDefaultDayRateMock));
        when(countryDefaultDayRateMock.getDefaultDayRate()).thenReturn(countryDefaultDayRate);

        BigDecimal actualDayRate = countryDefaultDayRateRetriever.findCountryDefaultDayRate(requestMock);
        assertEquals(countryDefaultDayRate, actualDayRate);
    }

    @Test
    void findCountryDefaultDayRate_shouldThrowExceptionWhenRateDoesNotExist() {
        when(requestMock.getCountry()).thenReturn("INVALID");
        when(countryDefaultDayRateRepositoryMock.findByCountryIc(any()))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> countryDefaultDayRateRetriever
                .findCountryDefaultDayRate(requestMock));
        assertEquals("Country ic = INVALID default day rate not found!", exception.getMessage());
    }

}
