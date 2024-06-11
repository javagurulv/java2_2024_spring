package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.Classifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ClassifierRepositoryTest {

    @Autowired
    private ClassifierRepository classifierRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(classifierRepository);
    }

    @Test
    public void shouldFindRiskTypeClassifier() {
        Optional<Classifier> riskTypeOpt = classifierRepository.findByTitle("RISK_TYPE");

        assertThat(riskTypeOpt)
                .get()
                .satisfies(r -> assertThat(r.getTitle()).isEqualTo("RISK_TYPE"));
    }

    @Test
    public void shouldFindCountryClassifier() {
        Optional<Classifier> riskTypeOpt = classifierRepository.findByTitle("COUNTRY");

        assertThat(riskTypeOpt)
                .get()
                .satisfies(r -> assertThat(r.getTitle()).isEqualTo("COUNTRY"));
    }

    @Test
    public void shouldNotFindFakeClassifier() {
        Optional<Classifier> riskTypeOpt = classifierRepository.findByTitle("FAKE");

        assertThat(riskTypeOpt).isEmpty();
    }

}