package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.domain.medical.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.medical.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryDefaultDayRateRetrieverTest {

    @Mock
    private CountryDefaultDayRateRepository countryDefaultDayRateRepositoryMock;

    @InjectMocks
    private CountryDefaultDayRateRetriever countryDefaultDayRateRetriever;

    @Autowired
    @InjectMocks
    private SetUpInstancesHelper helper;

    @Test
    void findCountryDefaultDayRate_shouldFindRateWhenRateExists() {
        BigDecimal countryDefaultDayRate = BigDecimal.valueOf(2.5);
        AgreementDTO agreement = helper.newAgreementDTO();

        CountryDefaultDayRate countryDefaultDayRateMock = mock(CountryDefaultDayRate.class);
        when(countryDefaultDayRateRepositoryMock.findByCountryIc(any()))
                .thenReturn(Optional.of(countryDefaultDayRateMock));
        when(countryDefaultDayRateMock.getDefaultDayRate()).thenReturn(countryDefaultDayRate);

        BigDecimal actualDayRate = countryDefaultDayRateRetriever.findCountryDefaultDayRate(agreement);
        assertEquals(countryDefaultDayRate, actualDayRate);
    }

    @Test
    void findCountryDefaultDayRate_shouldThrowExceptionWhenRateDoesNotExist() {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(helper.newDate("2025.03.10"))
                .withDateTo(helper.newDate("2025.03.11"))
                .withCountry("INVALID")
                .withSelectedRisks(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"))
                .withPerson(helper.newPersonDTO())
                .withPremium(BigDecimal.ZERO)
                .build();

        when(countryDefaultDayRateRepositoryMock.findByCountryIc("INVALID"))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> countryDefaultDayRateRetriever
                .findCountryDefaultDayRate(agreement));
        assertEquals("Country ic = INVALID default day rate not found!", exception.getMessage());
    }

}
