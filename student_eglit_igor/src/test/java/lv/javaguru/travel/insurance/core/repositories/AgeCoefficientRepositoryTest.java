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

    @Test
    public void findAgeCoefficientByAge_returnsCoefficient_whenAgeInRange() {
        Integer age = 25;
        BigDecimal expectedCoefficient = new BigDecimal("1.10");
        var valueOpt = ageCoefficientRepository.findAgeCoefficientByAge(age);
        assertTrue(valueOpt.isPresent());
        assertEquals(expectedCoefficient, valueOpt.get().getCoefficient());
    }

    @Test
    public void findAgeCoefficientByAge_returnsCoefficient_whenAgeAtLowerBoundary() {
        Integer age = 17;
        BigDecimal expectedCoefficient = new BigDecimal("1.00");
        var valueOpt = ageCoefficientRepository.findAgeCoefficientByAge(age);
        assertTrue(valueOpt.isPresent());
        assertEquals(expectedCoefficient, valueOpt.get().getCoefficient());
    }

    @Test
    public void findAgeCoefficientByAge_returnsCoefficient_whenAgeAtUpperBoundary() {
        Integer age = 65;
        BigDecimal expectedCoefficient = new BigDecimal("1.20");
        var valueOpt = ageCoefficientRepository.findAgeCoefficientByAge(age);
        assertTrue(valueOpt.isPresent());
        assertEquals(expectedCoefficient, valueOpt.get().getCoefficient());
    }
}