package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.javaguru.travel.insurance.core.services.AgreementPersonsPremiumCalculator;
import org.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import org.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgreementPersonsPremiumCalculatorTest {

    @Mock private TravelPremiumUnderwriting premiumUnderwriting;

    @InjectMocks
    private AgreementPersonsPremiumCalculator calculator;

    @Test
    public void shouldCalculateRiskPremiumForAllPersons() {
        PersonDTO person1 = new PersonDTO();
        PersonDTO person2 = new PersonDTO();

        AgreementDTO agreement = new AgreementDTO();
        agreement.setPersons(List.of(person1, person2));

        RiskDTO risk11 = new RiskDTO("RISK_1", BigDecimal.ONE);
        RiskDTO risk12 = new RiskDTO("RISK_2", BigDecimal.ONE);
        RiskDTO risk21 = new RiskDTO("RISK_1", BigDecimal.ONE);
        RiskDTO risk22 = new RiskDTO("RISK_2", BigDecimal.ONE);

        TravelPremiumCalculationResult calculationResult1 = new TravelPremiumCalculationResult(BigDecimal.ONE, List.of(risk11, risk12));
        TravelPremiumCalculationResult calculationResult2 = new TravelPremiumCalculationResult(BigDecimal.ONE, List.of(risk21, risk22));
        when(premiumUnderwriting.calculatePremium(agreement, person1)).thenReturn(calculationResult1);
        when(premiumUnderwriting.calculatePremium(agreement, person2)).thenReturn(calculationResult2);

        calculator.calculateRiskPremiums(agreement);

        assertEquals(agreement.getPersons().get(0).getRisks().size(), 2);
        assertEquals(agreement.getPersons().get(1).getRisks().size(), 2);
    }

}