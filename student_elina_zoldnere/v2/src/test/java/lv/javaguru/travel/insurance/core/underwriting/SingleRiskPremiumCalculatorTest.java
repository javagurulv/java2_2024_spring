package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SingleRiskPremiumCalculatorTest {

    @Mock
    private TravelRiskPremiumCalculator calculatorMock1;
    @Mock
    private TravelRiskPremiumCalculator calculatorMock2;
    @Mock
    private List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    @InjectMocks
    private SingleRiskPremiumCalculator singleRiskCalculator;

    @Autowired
    @InjectMocks
    private SetUpInstancesHelper helper;

    @BeforeEach
    void setUp() {
        when(riskPremiumCalculators.stream()).thenReturn(Stream.of(calculatorMock1, calculatorMock2));
        when(calculatorMock1.getRiskIc()).thenReturn("TRAVEL_CANCELLATION");
        when(calculatorMock2.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
    }

    @Test
    void calculatePremium_ShouldReturnRiskPremium() {
        AgreementDTO agreement = helper.newAgreementDTO();
        PersonDTO person = helper.newPersonDTO();
        String riskIc = "TRAVEL_MEDICAL";
        when(calculatorMock2.calculateRiskPremium(agreement, person)).thenReturn(BigDecimal.ONE);

        RiskDTO result = singleRiskCalculator.calculatePremium(riskIc, agreement, person);

        assertEquals("TRAVEL_MEDICAL", result.riskIc());
        assertEquals(BigDecimal.ONE, result.premium());
    }

    @Test
    void calculatePremium_ThrowsRuntimeExceptionWhenOnlyNotSupportedRisk() {
        AgreementDTO agreement = helper.newAgreementDTO();
        PersonDTO person = helper.newPersonDTO();
        String riskIc = "NOT_SUPPORTED_RISK";

        assertThrows(RuntimeException.class, () -> singleRiskCalculator.calculatePremium(riskIc, agreement, person));
    }

}
