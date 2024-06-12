package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTOBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CalculateTotalAgreementPremiumTest {

    @InjectMocks
    private CalculateTotalAgreementPremium calculate;

    @Test
    void calculateTotalAgreementPremium_ReturnsCorrectResult(){
        RiskDTO risk1 = RiskDTOBuilder.createRisk().withPremium(BigDecimal.TEN).build();
        RiskDTO risk2 = RiskDTOBuilder.createRisk().withPremium(BigDecimal.TEN).build();
        List<PersonDTO> persons = List.of(
                PersonDTOBuilder.createPerson().withPersonRisks(List.of(risk1, risk2)).build());

        BigDecimal result = calculate.calculateTotalAgreementPremium(persons);

        assertThat(result).isEqualTo(BigDecimal.valueOf(20));
    }

}
