package lv.javaguru.travel.insurance.core.repositories.medical;

import lv.javaguru.travel.insurance.core.domain.medical.TMAgeCoefficient;
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
class TMAgeCoefficientRepositoryTest {

    @Autowired
    private TMAgeCoefficientRepository ageCoefficientRepository;

    @Test
    void injectedRepositoryAreNotNull() {
        assertNotNull(ageCoefficientRepository);
    }

    @Test
    void shouldFindAgeCoefficient() {
        Optional<TMAgeCoefficient> ageCoefficientOpt = ageCoefficientRepository.findCoefficient(30);

        assertThat(ageCoefficientOpt)
                .get()
                .satisfies(a -> assertThat(a.getCoefficient()).isEqualTo(new BigDecimal("1.10")));
    }

    @Test
    void shouldNotFindByInvalidAge() {
        Optional<TMAgeCoefficient> ageCoefficientOpt = ageCoefficientRepository.findCoefficient(null);

        assertThat(ageCoefficientOpt).isEmpty();
    }

}