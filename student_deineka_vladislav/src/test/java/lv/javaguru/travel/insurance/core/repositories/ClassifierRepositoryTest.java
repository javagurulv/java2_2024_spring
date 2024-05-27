package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.Classifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest

class ClassifierRepositoryTest {

    @Autowired
    private ClassifierRepository classifierRepository;

    @Test
    public void injectedRepositoryIsNotEmpty() {
        assertNotNull(classifierRepository);
    }

    @Test
    public void riskTypeClassifierFound() {
        Optional<Classifier> riskTypeOptional = classifierRepository.findByTitle("RISK_TYPE");
        assertTrue(riskTypeOptional.isPresent());
        assertEquals(riskTypeOptional.get().getTitle(), "RISK_TYPE");
    }

    @Test
    public void fakeClassifierNotFound() {
        Optional<Classifier> riskTypeOptional = classifierRepository.findByTitle("FAKE");
        assertTrue(riskTypeOptional.isEmpty());
    }
}
