package lv.javaguru.travel.insurance.core.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class AgeCoefficientRepositoryTest {

    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;

    @Test
    public void injectedRepositoryAreNotNull(){
        assertNotNull(ageCoefficientRepository);
    }
    private void searchAgeCoefficient(Integer age, BigDecimal k) {
        var valueOpt = ageCoefficientRepository.findAgeCoefficientByAge(age);
        assertTrue(valueOpt.isPresent());
        assertEquals(valueOpt.get().getCoefficient(), k);
    }
}