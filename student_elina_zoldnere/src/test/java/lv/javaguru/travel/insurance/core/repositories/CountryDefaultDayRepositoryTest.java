package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class CountryDefaultDayRepositoryTest {

    @Autowired
    private CountryDefaultDayRateRepository dayRateRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(dayRateRepository);
    }

    @Test
    public void shouldFindCountryDayRate() {
        Optional<CountryDefaultDayRate> dayRateOpt = dayRateRepository.findByCountryIc("SPAIN");
        assertTrue(dayRateOpt.isPresent());
        assertEquals(new BigDecimal("2.50"), dayRateOpt.get().getDefaultDayRate());
    }

    @Test
    public void shouldNotFindInvalidDayRate() {
        Optional<CountryDefaultDayRate> dayRateOpt = dayRateRepository.findByCountryIc("INVALID");
        assertTrue(dayRateOpt.isEmpty());
    }

}