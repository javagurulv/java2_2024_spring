package lv.javaguru.travel.insurance.core.repositories;

import com.fasterxml.jackson.core.JsonParseException;
import lv.javaguru.travel.insurance.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.repositories.ClassifierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AgeCoefficientRepositoryTest {
    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(ageCoefficientRepository);
    }

    @Test
    public void shouldFindCorrectAgeCoefficient() {
        assertEquals(BigDecimal.valueOf(1.10).setScale(2), ageCoefficientRepository.findCoefficient(25).get().getCoefficient());
        assertEquals(BigDecimal.valueOf(1.10).setScale(2), ageCoefficientRepository.findCoefficient(5).get().getCoefficient());
        assertEquals(BigDecimal.valueOf(0.70).setScale(2), ageCoefficientRepository.findCoefficient(8).get().getCoefficient());
        assertEquals(BigDecimal.valueOf(1.00).setScale(2), ageCoefficientRepository.findCoefficient(11).get().getCoefficient());
        assertEquals(BigDecimal.valueOf(1.20).setScale(2), ageCoefficientRepository.findCoefficient(65).get().getCoefficient());
        assertEquals(BigDecimal.valueOf(1.50).setScale(2), ageCoefficientRepository.findCoefficient(150).get().getCoefficient());
    }

    @Test
    public void shouldNotFindCorrectAgeCoefficient() {
        try{
            ageCoefficientRepository.findCoefficient(155).get().getCoefficient();
        } catch (NoSuchElementException e) {
            assertEquals("No value present", e.getMessage());
        }
    }
}
