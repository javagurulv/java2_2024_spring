package lv.javaguru.travel.insurance.core.sercices;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgreementPersonsPremiumCalculatorTest {
    @Mock
    private TravelPremiumUnderwriting premiumUnderwriting;
    @InjectMocks
    private AgreementPersonsPremiumCalculator calculator;

    @Test
    void shouldCalculateRiskPremiumForAllPersons() {
        var person1 = new PersonDTO("Joe", "Doe", "12345",LocalDate.of(2000, 1, 1), "MEDICAL_RISK_LIMIT", List.of());
        var person2 = new PersonDTO("Jane", "Doe", "12345",LocalDate.of(2000, 1, 1), "MEDICAL_RISK_LIMIT",List.of());

        AgreementDTO agreement = new AgreementDTO();
        agreement.setPersons(List.of(person1, person2));

        RiskDTO risk11 = new RiskDTO("RISK_1", BigDecimal.ONE);
        RiskDTO risk12 = new RiskDTO("RISK_2", BigDecimal.ONE);
        RiskDTO risk21 = new RiskDTO("RISK_1", BigDecimal.ONE);
        RiskDTO risk22 = new RiskDTO("RISK_2", BigDecimal.ONE);

        var calculationResult1 = new TravelPremiumCalculationResult(BigDecimal.ONE, List.of(risk11, risk12));
        var calculationResult2 = new TravelPremiumCalculationResult(BigDecimal.ONE, List.of(risk21, risk22));
        when(premiumUnderwriting.calculatePremium(agreement, person1)).thenReturn(calculationResult1);
        when(premiumUnderwriting.calculatePremium(agreement, person2)).thenReturn(calculationResult2);

        calculator.calculateRiskPremiums(agreement);

        assertEquals(2, agreement.getPersons().get(0).risks().size());
        assertEquals(2, agreement.getPersons().get(1).risks().size());
    }
}