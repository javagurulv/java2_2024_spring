package lv.javaguru.travel.insurance.core.repositories.medical;

import lv.javaguru.travel.insurance.core.domain.medical.MedicalRiskLimitLevel;
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
class MedicalRiskLimitLevelRepositoryTest {

    @Autowired
    private MedicalRiskLimitLevelRepository limitLevelRepository;

    @Test
    void injectedRepositoryAreNotNull() {
        assertNotNull(limitLevelRepository);
    }

    @Test
    void shouldFindCoefficientForExistingMedicalRiskLimitLevel() {
        Optional<MedicalRiskLimitLevel> limitLevelOpt = limitLevelRepository
                .findByMedicalRiskLimitLevelIc("LEVEL_10000");

        assertThat(limitLevelOpt)
                .get()
                .satisfies(l -> assertThat(l.getCoefficient()).isEqualTo(new BigDecimal("1.00")));
    }

    @Test
    void shouldReturnEmptyForInvalidMedicalRiskLimitLevel() {
        Optional<MedicalRiskLimitLevel> limitLevelOpt = limitLevelRepository.findByMedicalRiskLimitLevelIc("INVALID");

        assertThat(limitLevelOpt).isEmpty();
    }

}