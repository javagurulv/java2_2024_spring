package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ClassifierValueRepositoryTest {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(classifierValueRepository);
    }

    @ParameterizedTest
    @MethodSource("riskTypeValues")
    public void shouldFindRiskTypeValue(String riskType) {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", riskType);
        assertTrue(valueOpt.isPresent());
        assertEquals(riskType, valueOpt.get().getIc());
        assertEquals("RISK_TYPE", valueOpt.get().getClassifier().getTitle());
    }

    private static Stream<String> riskTypeValues() {
        return Stream.of(
                "TRAVEL_MEDICAL",
                "TRAVEL_CANCELLATION",
                "TRAVEL_LOSS_BAGGAGE",
                "TRAVEL_THIRD_PARTY_LIABILITY",
                "TRAVEL_EVACUATION",
                "TRAVEL_SPORT_ACTIVITIES"
        );
    }

    @ParameterizedTest
    @MethodSource("countryValues")
    public void shouldFindCountryValue(String country) {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "COUNTRY", country);
        assertTrue(valueOpt.isPresent());
        assertEquals(country, valueOpt.get().getIc());
        assertEquals("COUNTRY", valueOpt.get().getClassifier().getTitle());
    }

    private static Stream<String> countryValues() {
        return Stream.of(
                "LATVIA",
                "SPAIN",
                "JAPAN"
        );
    }

    @Test
    public void shouldNotFind_RiskType_FAKE() {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", "FAKE");
        assertTrue(valueOpt.isEmpty());
    }

}