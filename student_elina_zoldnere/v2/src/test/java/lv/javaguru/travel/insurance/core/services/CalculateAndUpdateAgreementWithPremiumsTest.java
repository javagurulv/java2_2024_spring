package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
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
public class CalculateAndUpdateAgreementWithPremiumsTest {

    @Mock
    private CalculateAndUpdatePersonsWithRiskPremiums calculateAndUpdatePersonsMock;
    @Mock
    private CalculateTotalAgreementPremium calculateTotalPremiumMock;

    @InjectMocks
    private CalculateAndUpdateAgreementWithPremiums calculateAndUpdateAgreement;
    @InjectMocks
    private SetUpInstancesHelper helper;

    @Test
    void calculateAgreementPremiums_ShouldReturnCorrectResult() {
        AgreementDTO agreement = helper.newAgreementDTO();
        List<PersonDTO> personsWithRiskPremiums = List.of(helper.newPersonWithRisksDTO(), helper.newPersonWithRisksDTO());
        when(calculateAndUpdatePersonsMock.calculateRiskPremiumsForAllPersons(agreement))
                .thenReturn(personsWithRiskPremiums);
        when(calculateTotalPremiumMock.calculateTotalAgreementPremium(personsWithRiskPremiums))
                .thenReturn(BigDecimal.valueOf(100));

        AgreementDTO agreementWithPremium = calculateAndUpdateAgreement.calculateAgreementPremiums(agreement);

        assertEquals(BigDecimal.ZERO, agreement.agreementPremium());
        assertEquals(BigDecimal.valueOf(100), agreementWithPremium.agreementPremium());
    }

}
