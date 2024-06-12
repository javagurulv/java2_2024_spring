package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTOBuilder;
import lv.javaguru.travel.insurance.core.underwriting.TravelCalculatePremiumUnderwriting;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateAndUpdatePersonsWithRiskPremiumsTest {

    @Mock
    private TravelCalculatePremiumUnderwriting calculateUnderwriting;

    @InjectMocks
    private CalculateAndUpdatePersonsWithRiskPremiums calculateAndUpdatePersons;

    @Test
    void calculateRiskPremiumsForAllPersons_ShouldReturnCorrectResult() {
        RiskDTO risk1 = RiskDTOBuilder.createRisk().withPremium(BigDecimal.TEN).build();
        RiskDTO risk2 = RiskDTOBuilder.createRisk().withPremium(BigDecimal.TEN).build();
        PersonDTO person1 = PersonDTOBuilder.createPerson().build();
        PersonDTO person2 = PersonDTOBuilder.createPerson().build();
        List<PersonDTO> persons = List.of(person1, person2);

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withPersons(persons)
                .build();

        when(calculateUnderwriting.calculateAgreementPremium(agreement, person1))
                .thenReturn(new TravelPremiumCalculationResult(BigDecimal.TEN, List.of(risk1)));
        when(calculateUnderwriting.calculateAgreementPremium(agreement, person2))
                .thenReturn(new TravelPremiumCalculationResult(BigDecimal.TEN, List.of(risk2)));

        List<PersonDTO> personsWithRisks = calculateAndUpdatePersons.calculateRiskPremiumsForAllPersons(agreement);

        assertThat(personsWithRisks.get(0).personRisks().get(0).premium()).isEqualTo(BigDecimal.TEN);
        assertThat(personsWithRisks.get(1).personRisks().get(0).premium()).isEqualTo(BigDecimal.TEN);
        assertThat(personsWithRisks.get(0).personFirstName()).isEqualTo(persons.get(0).personFirstName());
        assertThat(personsWithRisks.get(1).personFirstName()).isEqualTo(persons.get(1).personFirstName());
    }

}
