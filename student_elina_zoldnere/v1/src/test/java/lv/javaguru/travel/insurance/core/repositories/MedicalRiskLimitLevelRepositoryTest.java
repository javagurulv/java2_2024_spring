package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
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
class MedicalRiskLimitLevelRepositoryTest {

    @Autowired
    private MedicalRiskLimitLevelRepository limitLevelRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(limitLevelRepository);
    }

    @Test
    public void shouldFindCoefficientForExistingMedicalRiskLimitLevel() {
        Optional<MedicalRiskLimitLevel> limitLevelOpt = limitLevelRepository
                .findByMedicalRiskLimitLevelIc("LEVEL_10000");
        assertTrue(limitLevelOpt.isPresent());
        assertEquals(new BigDecimal("1.00"), limitLevelOpt.get().getCoefficient());
    }

    @Test
    public void shouldReturnEmptyForInvalidMedicalRiskLimitLevel() {
        Optional<MedicalRiskLimitLevel> limitLevelOpt = limitLevelRepository.findByMedicalRiskLimitLevelIc("INVALID");
        assertTrue(limitLevelOpt.isEmpty());
    }

}