package lv.javaguru.travel.insurance.core.sercices;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AgreementTotalPremiumCalculatorTest {

    private AgreementTotalPremiumCalculator calculator = new AgreementTotalPremiumCalculator();

    @Test
    void shouldSumTotalPremiumForAllPersons() {
        RiskDTO risk11 = new RiskDTO("RISK_1", BigDecimal.ONE);
        RiskDTO risk12 = new RiskDTO("RISK_2", BigDecimal.ONE);
        RiskDTO risk21 = new RiskDTO("RISK_1", BigDecimal.ONE);
        RiskDTO risk22 = new RiskDTO("RISK_2", BigDecimal.ONE);

        var person1 = new PersonDTO("Joe", "Doe",
                LocalDate.of(2000, 1, 1),
                List.of(risk11, risk12));
        var person2 = new PersonDTO("Jane", "Doe",
                LocalDate.of(2000, 1, 1),
                List.of(risk21, risk22));

        var agreement = new AgreementDTO();
        agreement.setPersons(List.of(person1, person2));

        BigDecimal totalPremium = calculator.calculate(agreement);

        assertEquals(BigDecimal.valueOf(4), totalPremium);
    }
}