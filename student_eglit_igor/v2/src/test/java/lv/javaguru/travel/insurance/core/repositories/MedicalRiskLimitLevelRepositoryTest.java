package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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
    public void shouldNotFindForUnknownMedicalRiskLimitLevel() {
        assertTrue(limitLevelRepository.findByMedicalRiskLimitLevelIc("FAKE_LEVEL").isEmpty());
    }
    @ParameterizedTest
    @MethodSource("medicalRiskLimitLevelValues")
    public void searchMedicalRiskLimitLevel(String level, String coefficient) {
        Optional<MedicalRiskLimitLevel> limitLevelOpt = limitLevelRepository.findByMedicalRiskLimitLevelIc(level);
        assertEquals(level, limitLevelOpt.get().getMedicalRiskLimitLevelIc());
        assertEquals(coefficient, limitLevelOpt.get().getCoefficient().toString());
    }
    public static Stream<Arguments> medicalRiskLimitLevelValues() {
        return Stream.of(
                Arguments.of("LEVEL_10000", "1.00"),
                Arguments.of("LEVEL_15000", "1.20"),
                Arguments.of("LEVEL_20000", "1.50"),
                Arguments.of("LEVEL_50000", "2.00")
        );
    }
}