package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class CountryDefaultDayRateRepositoryTest {
    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    @Test
    public void injectedRepositoryIsNotNull() {
        assertNotNull(countryDefaultDayRateRepository);
    }

    @Test
    public void findByCountryIc_returnsRate_whenCountryIcLATVIA() {
        Optional<CountryDefaultDayRate> rateOpt = countryDefaultDayRateRepository.findByCountryIc("LATVIA");
        assertTrue(rateOpt.isPresent());
        assertEquals("LATVIA", rateOpt.get().getCountryIc());
        assertEquals(new BigDecimal("1.00"), rateOpt.get().getDefaultDayRate());
    }
    @Test
    public void findByCountryIc_returnsRate_whenCountryIcSPAIN() {
        Optional<CountryDefaultDayRate> rateOpt = countryDefaultDayRateRepository.findByCountryIc("SPAIN");
        assertTrue(rateOpt.isPresent());
        assertEquals("SPAIN", rateOpt.get().getCountryIc());
        assertEquals(new BigDecimal("2.50"), rateOpt.get().getDefaultDayRate());
    }
    @Test
    public void findByCountryIc_returnsRate_whenCountryIcJAPAN() {
        Optional<CountryDefaultDayRate> rateOpt = countryDefaultDayRateRepository.findByCountryIc("JAPAN");
        assertTrue(rateOpt.isPresent());
        assertEquals("JAPAN", rateOpt.get().getCountryIc());
        assertEquals(new BigDecimal("3.50"), rateOpt.get().getDefaultDayRate());
    }


    @Test
    public void findByCountryIc_returnsEmpty_whenCountryIcDoesNotExist() {
        Optional<CountryDefaultDayRate> rateOpt = countryDefaultDayRateRepository.findByCountryIc("INVALID_IC");
        assertTrue(rateOpt.isEmpty());
    }
}