package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CalculateTotalAgreementPremiumTest {

    @InjectMocks
    private SetUpInstancesHelper helper;

    @InjectMocks
    private CalculateTotalAgreementPremium calculate;

    @Test
    void calculateTotalAgreementPremium_ReturnsCorrectResult(){
        List<PersonDTO> personsWithRisks = List.of(helper.newPersonWithRisksDTO(), helper.newPersonWithRisksDTO());

        BigDecimal result = calculate.calculateTotalAgreementPremium(personsWithRisks);

        assertEquals(BigDecimal.valueOf(20), result);
    }

}
