package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.api.repositories.AgeCoefficientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

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
    public void shouldReturnEmptyOptional_whenAgeIsNotInRange() {
        assertTrue(ageCoefficientRepository.findCoefficient(-1).isEmpty());
        assertTrue(ageCoefficientRepository.findCoefficient(151).isEmpty());
    }

    @ParameterizedTest
    @MethodSource("ageValues")
    public void findAgeCoefficientByAge_returnsCoefficient_whenAgeInRange(Integer age, BigDecimal expectedCoefficient) {

        var valueOpt = ageCoefficientRepository.findCoefficient(age);
        assertTrue(valueOpt.isPresent());
        assertEquals(expectedCoefficient, valueOpt.get().getCoefficient());
    }
    private static Stream<Arguments> ageValues() {
        return Stream.of(
                Arguments.of(0, new BigDecimal("1.10")),
                Arguments.of(5, new BigDecimal("1.10")),
                Arguments.of(6, new BigDecimal("0.70")),
                Arguments.of(10, new BigDecimal("0.70")),
                Arguments.of(11, new BigDecimal("1.00")),
                Arguments.of(17, new BigDecimal("1.00")),
                Arguments.of(18, new BigDecimal("1.10")),
                Arguments.of(40, new BigDecimal("1.10")),
                Arguments.of(41, new BigDecimal("1.20")),
                Arguments.of(65, new BigDecimal("1.20")),
                Arguments.of(66, new BigDecimal("1.50")),
                Arguments.of(150, new BigDecimal("1.50"))
        );
    }
}