package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.Classifier;
import lv.javaguru.travel.insurance.repositories.ClassifierRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

    @Nested
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
            assertTrue(riskTypeOpt.isPresent());
            assertEquals(riskTypeOpt.get().getTitle(), "RISK_TYPE");
        }

        @Test
        public void shouldNotFindFakeClassifier() {
            Optional<Classifier> riskTypeOpt = classifierRepository.findByTitle("FAKE");
            assertTrue(riskTypeOpt.isEmpty());
        }
    }
