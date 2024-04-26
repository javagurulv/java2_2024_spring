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

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MedicalRiskLimitLevelRepositoryTest {
    @Autowired
    private MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    @Test
    public void injectedRepositoryIsNotNull() {
        assertNotNull(medicalRiskLimitLevelRepository);
    }

    @Test
    public void findByMedicalRiskLimitLevelIc_LEVEL_10000() {
        Optional<MedicalRiskLimitLevel> coefficientOpt = medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc("LEVEL_10000");
        assertNotNull(coefficientOpt);
        assertEquals("LEVEL_10000", coefficientOpt.get().getMedicalRiskLimitLevelIc());
        assertEquals(new BigDecimal("1.00"), coefficientOpt.get().getCoefficient());
    }

    @Test
    public void findByMedicalRiskLimitLevelIc_LEVEL_15000() {
        Optional<MedicalRiskLimitLevel> coefficientOpt = medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc("LEVEL_15000");
        assertNotNull(coefficientOpt);
        assertEquals("LEVEL_15000", coefficientOpt.get().getMedicalRiskLimitLevelIc());
        assertEquals(new BigDecimal("1.20"), coefficientOpt.get().getCoefficient());
    }

    @Test
    public void findByMedicalRiskLimitLevelIc_LEVEL_20000() {
        Optional<MedicalRiskLimitLevel> coefficientOpt = medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc("LEVEL_20000");
        assertNotNull(coefficientOpt);
        assertEquals("LEVEL_20000", coefficientOpt.get().getMedicalRiskLimitLevelIc());
        assertEquals(new BigDecimal("1.50"), coefficientOpt.get().getCoefficient());
    }
    @Test
    public void findByMedicalRiskLimitLevelIc_LEVEL_50000() {
        Optional<MedicalRiskLimitLevel> coefficientOpt = medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc("LEVEL_50000");
        assertNotNull(coefficientOpt);
        assertEquals("LEVEL_50000", coefficientOpt.get().getMedicalRiskLimitLevelIc());
        assertEquals(new BigDecimal("2.00"), coefficientOpt.get().getCoefficient());
    }
    @Test
    public void noValidMedicalRiskReturnsNoCoefficient(){
        Optional<MedicalRiskLimitLevel> coefficientOpt = medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc("INVALID_IC");
        assertNotNull(coefficientOpt);
        assertEquals(Optional.empty(), coefficientOpt);
    }
}