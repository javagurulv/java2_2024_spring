package lv.javaguru.travel.insurance.core.repositories.medical;

import lv.javaguru.travel.insurance.core.domain.medical.CountryDefaultDayRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CountryDefaultDayRepositoryTest {

    @Autowired
    private CountryDefaultDayRateRepository dayRateRepository;

    @Test
    void injectedRepositoryAreNotNull() {
        assertNotNull(dayRateRepository);
    }

    @Test
    void shouldFindCountryDayRate() {
        Optional<CountryDefaultDayRate> dayRateOpt = dayRateRepository.findByCountryIc("SPAIN");

        assertThat(dayRateOpt)
                .get()
                .satisfies(d -> assertThat(d.getDefaultDayRate()).isEqualTo(new BigDecimal("2.50")));
    }

    @Test
    void shouldNotFindInvalidDayRate() {
        Optional<CountryDefaultDayRate> dayRateOpt = dayRateRepository.findByCountryIc("INVALID");

        assertThat(dayRateOpt).isEmpty();
    }

}