package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelCalculatePremiumUnderwriting;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculateAndUpdatePersonsWithRiskPremiumsTest {

    @Mock
    private TravelCalculatePremiumUnderwriting calculateUnderwriting;

    @InjectMocks
    private SetUpInstancesHelper helper;
    @InjectMocks
    private CalculateAndUpdatePersonsWithRiskPremiums calculateAndUpdatePersons;

    @Test
    void calculateRiskPremiumsForAllPersons_ShouldReturnCorrectResult() {
        PersonDTO person = helper.newPersonDTO();
        AgreementDTO agreement = helper.newTwoPersonsAgreementDTO();
        List<PersonDTO> persons = agreement.persons();
        when(calculateUnderwriting.calculateAgreementPremium(agreement, person))
                .thenReturn(new TravelPremiumCalculationResult(BigDecimal.TEN, List.of(helper.newRiskDTO())));

        List<PersonDTO> personsWithRisks = calculateAndUpdatePersons.calculateRiskPremiumsForAllPersons(agreement);

        assertEquals(Collections.emptyList(), persons.get(0).personRisks());
        assertEquals(Collections.emptyList(), persons.get(1).personRisks());

        assertEquals(BigDecimal.valueOf(10), personsWithRisks.get(0).personRisks().get(0).premium());
        assertEquals(BigDecimal.valueOf(10), personsWithRisks.get(1).personRisks().get(0).premium());
        assertEquals(persons.get(0).personFirstName(), personsWithRisks.get(0).personFirstName());
        assertEquals(persons.get(1).personFirstName(), personsWithRisks.get(1).personFirstName());
    }

}
