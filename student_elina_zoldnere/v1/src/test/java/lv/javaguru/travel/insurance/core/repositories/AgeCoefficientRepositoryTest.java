package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
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
class AgeCoefficientRepositoryTest {

    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(ageCoefficientRepository);
    }

    @Test
    public void shouldFindAgeCoefficient() {
        Optional<AgeCoefficient> ageCoefficientOpt = ageCoefficientRepository.findCoefficient(30);
        assertTrue(ageCoefficientOpt.isPresent());
        assertEquals(new BigDecimal("1.10"), ageCoefficientOpt.get().getCoefficient());
    }

    @Test
    public void shouldNotFindByInvalidAge() {
        Optional<AgeCoefficient> ageCoefficientOpt = ageCoefficientRepository.findCoefficient(null);
        assertTrue(ageCoefficientOpt.isEmpty());
    }

}