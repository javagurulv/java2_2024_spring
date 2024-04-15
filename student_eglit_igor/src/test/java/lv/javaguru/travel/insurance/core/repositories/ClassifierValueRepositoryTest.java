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
    public void injectedRepositoryAreNotNull() {
        assertNotNull(classifierValueRepository);
    }

    @Test
    public void shouldFind_RiskType_TRAVEL_CANCELLATION() {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", "TRAVEL_MEDICAL");
        assertTrue(valueOpt.isPresent());
        assertEquals("TRAVEL_MEDICAL", valueOpt.get().getIc());
        assertEquals("RISK_TYPE", valueOpt.get().getClassifier().getTitle());
    }

    @Test
    public void shouldFind_RiskType_TRAVEL_CANSELLATION() {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", "TRAVEL_CANCELLATION");
        assertTrue(valueOpt.isPresent());
        assertEquals("TRAVEL_CANCELLATION", valueOpt.get().getIc());
        assertEquals("RISK_TYPE", valueOpt.get().getClassifier().getTitle());
    }

    @Test
    public void shouldFind_RiskType_TRAVEL_LOSS_BAGGAGE() {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", "TRAVEL_LOSS_BAGGAGE");
        assertTrue(valueOpt.isPresent());
        assertEquals("TRAVEL_LOSS_BAGGAGE", valueOpt.get().getIc());
        assertEquals("RISK_TYPE", valueOpt.get().getClassifier().getTitle());
    }

    @Test
    public void shouldFind_RiskType_TRAVEL_THIRD_PARTY_LIABILITY() {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", "TRAVEL_THIRD_PARTY_LIABILITY");
        assertTrue(valueOpt.isPresent());
        assertEquals("TRAVEL_THIRD_PARTY_LIABILITY", valueOpt.get().getIc());
        assertEquals("RISK_TYPE", valueOpt.get().getClassifier().getTitle());
    }

    @Test
    public void shouldFind_RiskType_TRAVEL_EVACUATION() {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", "TRAVEL_EVACUATION");
        assertTrue(valueOpt.isPresent());
        assertEquals("TRAVEL_EVACUATION", valueOpt.get().getIc());
        assertEquals("RISK_TYPE", valueOpt.get().getClassifier().getTitle());
    }

    @Test
    public void shouldFind_RiskType_TRAVEL_SPORT_ACTIVITIES(){
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", "TRAVEL_SPORT_ACTIVITIES");
        assertTrue(valueOpt.isPresent());
        assertEquals("TRAVEL_SPORT_ACTIVITIES", valueOpt.get().getIc());
        assertEquals("RISK_TYPE", valueOpt.get().getClassifier().getTitle());
    }

    @Test
    public void shouldNotFind_RiskType_TRAVEL_SPORT_ACTIVITIES(){
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", "FAKE");
        assertTrue(valueOpt.isEmpty());
    }
}