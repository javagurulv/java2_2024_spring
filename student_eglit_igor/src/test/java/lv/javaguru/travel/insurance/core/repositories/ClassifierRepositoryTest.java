package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.Classifier;
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
class ClassifierRepositoryTest {
    @Autowired
    private ClassifierRepository classifierRepository;

    @Test
    public void injectedRepositoryAreNotNull(){
        assertNotNull(classifierRepository);
    }
    @Test
    public void shouldNotFindFakeClassifier(){
        Optional<Classifier> riskTypeOpt = classifierRepository.findByTitle("FAKE");
        assertTrue(riskTypeOpt.isEmpty());
    }
    @ParameterizedTest
    @MethodSource("classifierTitles")
    public void shouldFindRiskTypeClassifier(String classifierTitle){
        Optional<Classifier> riskTypeOpt = classifierRepository.findByTitle("RISK_TYPE");
        assertTrue(riskTypeOpt.isPresent());
        assertEquals("RISK_TYPE", riskTypeOpt.get().getTitle());
    }
    public static Stream<Arguments> classifierTitles() {
        return Stream.of(
                Arguments.of("RISK_TYPE"),
                Arguments.of("COUNTRY"),
                Arguments.of("AGE_COEFFICIENT"),
                Arguments.of("MEDICAL_RISK_LIMIT_LEVEL")
        );
    }



}