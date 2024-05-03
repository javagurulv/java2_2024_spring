package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.junit.jupiter.api.Nested;
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

@Nested
@ExtendWith(SpringExtension.class)
@DataJpaTest
class ClassifierValueRepositoryTest {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(classifierValueRepository);
    }

    @Test
    public void shouldNotFind_RiskType() {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", "FAKE");
        assertTrue(valueOpt.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("valuesTitleAndIc")
    public void checksTitleAndIc(String classifierTitle, String ic) {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                classifierTitle, ic);
        assertTrue(valueOpt.isPresent());
        assertEquals(ic, valueOpt.get().getIc());
        assertEquals(classifierTitle, valueOpt.get().getClassifier().getTitle());
    }

    public static Stream<Arguments> valuesTitleAndIc() {
        return Stream.of(
                Arguments.of("RISK_TYPE", "TRAVEL_MEDICAL"),
                Arguments.of("RISK_TYPE", "TRAVEL_CANCELLATION"),
                Arguments.of("RISK_TYPE", "TRAVEL_LOSS_BAGGAGE"),
                Arguments.of("RISK_TYPE", "TRAVEL_THIRD_PARTY_LIABILITY"),
                Arguments.of("RISK_TYPE", "TRAVEL_EVACUATION"),
                Arguments.of("RISK_TYPE", "TRAVEL_SPORT_ACTIVITIES"),
                Arguments.of("COUNTRY", "LATVIA"),
                Arguments.of("COUNTRY", "SPAIN"),
                Arguments.of("COUNTRY", "JAPAN")
        );
    }
}
