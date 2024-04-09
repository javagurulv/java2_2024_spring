package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.javaguru.travel.insurance.core.services.AgreementTotalPremiumCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AgreementTotalPremiumCalculatorTest {

    private AgreementTotalPremiumCalculator calculator = new AgreementTotalPremiumCalculator();

    @Test
    public void shouldSumTotalPremiumForAllPersons() {
        RiskDTO risk11 = new RiskDTO("RISK_1", BigDecimal.ONE);
        RiskDTO risk12 = new RiskDTO("RISK_2", BigDecimal.ONE);
        RiskDTO risk21 = new RiskDTO("RISK_1", BigDecimal.ONE);
        RiskDTO risk22 = new RiskDTO("RISK_2", BigDecimal.ONE);

        PersonDTO person1 = new PersonDTO();
        person1.setRisks(List.of(risk11, risk12));
        PersonDTO person2 = new PersonDTO();
        person2.setRisks(List.of(risk21, risk22));

        AgreementDTO agreement = new AgreementDTO();
        agreement.setPersons(List.of(person1, person2));

        BigDecimal totalPremium = calculator.calculate(agreement);

        assertEquals(totalPremium, BigDecimal.valueOf(4));
    }

}