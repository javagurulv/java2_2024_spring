package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
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
class CalculateAndUpdateAgreementWithPremiumsTest {

    @Mock
    private CalculateAndUpdatePersonsWithRiskPremiums calculateAndUpdatePersonsMock;
    @Mock
    private CalculateTotalAgreementPremium calculateTotalPremiumMock;

    @InjectMocks
    private CalculateAndUpdateAgreementWithPremiums calculateAndUpdateAgreement;

    @Test
    void calculateAgreementPremiums_ShouldReturnCorrectResult() {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withPremium(BigDecimal.ZERO)
                .build();

        PersonDTO person1 = PersonDTOBuilder.createPerson().build();
        PersonDTO person2 = PersonDTOBuilder.createPerson().build();
        List<PersonDTO> personsWithRiskPremiums = List.of(person1, person2);

        when(calculateAndUpdatePersonsMock.calculateRiskPremiumsForAllPersons(agreement))
                .thenReturn(personsWithRiskPremiums);
        when(calculateTotalPremiumMock.calculateTotalAgreementPremium(personsWithRiskPremiums))
                .thenReturn(BigDecimal.valueOf(100));

        AgreementDTO agreementWithPremium = calculateAndUpdateAgreement.calculateAgreementPremiums(agreement);

        assertThat(agreement.agreementPremium()).isEqualTo(BigDecimal.ZERO);
        assertThat(agreementWithPremium.agreementPremium()).isEqualTo(BigDecimal.valueOf(100));
    }

}
