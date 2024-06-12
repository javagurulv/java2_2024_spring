package lv.javaguru.travel.insurance.core.repositories.cancellation;

import lv.javaguru.travel.insurance.core.domain.cancellation.TCCountrySafetyRatingCoefficient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TCCountrySafetyRatingCoefficientRepositoryTest {

    @Autowired
    private TCCountrySafetyRatingCoefficientRepository repository;

    @Test
    void injectedRepositoryAreNotNull() {
        assertThat(repository).isNotNull();
    }

    @Test
    void findByCountryIc_ShouldFindExistingCoefficient() {
        Optional<TCCountrySafetyRatingCoefficient> dayRateOpt = repository.findCoefficientByCountryIc("SPAIN");

        assertThat(dayRateOpt)
                .get()
                .satisfies(d -> assertThat(d.getCoefficient()).isEqualTo(new BigDecimal("8.00")));
    }

    @Test
    void findByCountryIc_ShouldNotFindCoefficientWhenAgeIsNull() {
        Optional<TCCountrySafetyRatingCoefficient> dayRateOpt = repository.findCoefficientByCountryIc(null);

        assertThat(dayRateOpt).isEmpty();
    }

    @Test
    void findByCountryIc_ShouldNotFindCoefficientWhenCoefficientDoesNotExist() {
        Optional<TCCountrySafetyRatingCoefficient> dayRateOpt = repository.findCoefficientByCountryIc("INVALID");

        assertThat(dayRateOpt).isEmpty();
    }

}