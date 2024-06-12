package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TMCountryDefaultDayRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TMCountryDefaultDayRateRepositoryTest {

    @Autowired
    private TMCountryDefaultDayRateRepository TMCountryDefaultDayRateRepository;



    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(TMCountryDefaultDayRateRepository);
    }
    @Test
    public void shouldNotFindForUnknownCountry() {
        Optional<TMCountryDefaultDayRate> valueOpt = TMCountryDefaultDayRateRepository.findByCountryIc("FAKE_COUNTRY");
        assertTrue(valueOpt.isEmpty());
    }
    @ParameterizedTest
    @MethodSource("countryValues")
    public void searchCountryDefaultDayRate(String country, BigDecimal dayRate) {
        Optional<TMCountryDefaultDayRate> valueOpt = TMCountryDefaultDayRateRepository.findByCountryIc(country);
        assertTrue(valueOpt.isPresent());
        assertEquals(valueOpt.get().getCountryIc(), country);
        assertEquals(valueOpt.get().getDefaultDayRate(), dayRate);

        assertEquals(dayRate.stripTrailingZeros(),
                valueOpt.get().getDefaultDayRate().stripTrailingZeros());
    }
    public static Stream<Arguments> countryValues() {
        return Stream.of(
                Arguments.of("LATVIA", new BigDecimal("1.00")),
                Arguments.of("SPAIN", new BigDecimal("2.50")),
                Arguments.of("JAPAN", new BigDecimal("3.50"))
        );
    }
}