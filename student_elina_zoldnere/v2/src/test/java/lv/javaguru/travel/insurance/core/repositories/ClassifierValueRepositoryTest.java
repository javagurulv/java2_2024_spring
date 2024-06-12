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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class ClassifierValueRepositoryTest {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Test
    void injectedRepositoryAreNotNull() {
        assertNotNull(classifierValueRepository);
    }

    @ParameterizedTest
    @MethodSource("riskTypeValues")
    void shouldFindRiskTypeValue(String riskType) {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", riskType);

        assertThat(valueOpt)
                .get()
                .satisfies(value -> {
                    assertThat(value.getIc()).isEqualTo(riskType);
                    assertThat(value.getClassifier().getTitle()).isEqualTo("RISK_TYPE");
                });
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
    void shouldFindCountryValue(String country) {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "COUNTRY", country);

        assertThat(valueOpt)
                .get()
                .satisfies(value -> {
                    assertThat(value.getIc()).isEqualTo(country);
                    assertThat(value.getClassifier().getTitle()).isEqualTo("COUNTRY");
                });
    }

    private static Stream<String> countryValues() {
        return Stream.of(
                "LATVIA",
                "SPAIN",
                "JAPAN"
        );
    }

    @Test
    void shouldNotFind_RiskType_FAKE() {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", "FAKE");

        assertThat(valueOpt).isEmpty();
    }

}