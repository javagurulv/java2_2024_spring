package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest

class ClassifierValueRepositoryTest {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Test
    public void injectedRepositoryIsNotEmpty() {
        assertNotNull(classifierValueRepository);
    }

    @Test
    public void TRAVEL_MEDICAL_RiskTypeFound() {
        classifierValueChecking
                ("RISK_TYPE", "TRAVEL_MEDICAL");

    }

    @Test
    public void TRAVEL_CANCELLATION_RiskTypeFound() {
        classifierValueChecking
                ("RISK_TYPE", "TRAVEL_CANCELLATION");

    }

    @Test
    public void TRAVEL_BAGGAGE_LOSS_RiskTypeFound() {
        classifierValueChecking
                ("RISK_TYPE", "TRAVEL_BAGGAGE_LOSS");

    }

    @Test
    public void TRAVEL_THIRD_PARTY_LIABILITY_RiskTypeFound() {
        classifierValueChecking
                ("RISK_TYPE", "TRAVEL_THIRD_PARTY_LIABILITY");

    }

    @Test
    public void TRAVEL_EVACUATION_RiskTypeFound() {
        classifierValueChecking
                ("RISK_TYPE", "TRAVEL_EVACUATION");

    }

    @Test
    public void TRAVEL_SPORT_ACTIVITIES_RiskTypeFound() {
        classifierValueChecking
                ("RISK_TYPE", "TRAVEL_SPORT_ACTIVITIES" );
    }

    @Test
    public void fakeRiskTypeNotFound() {
        Optional<ClassifierValue> valueOptional = classifierValueRepository.findByClassifierTitleAndIc
                ("RISK_TYPE", "FAKE");
        assertTrue(valueOptional.isEmpty());
    }

    private void classifierValueChecking(String classifierTitle, String ic) {
        Optional<ClassifierValue> valueOptional = classifierValueRepository.findByClassifierTitleAndIc
                (classifierTitle, ic);
        assertTrue(valueOptional.isPresent());
        assertEquals(valueOptional.get().getClassifier().getTitle(), classifierTitle);
        assertEquals(valueOptional.get().getIc(), ic);
    }



}
