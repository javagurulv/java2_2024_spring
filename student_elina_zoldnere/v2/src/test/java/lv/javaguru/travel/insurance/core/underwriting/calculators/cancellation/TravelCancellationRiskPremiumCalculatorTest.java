package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

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
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCancellationRiskPremiumCalculatorTest {

    @Mock
    private TCAgeCoefficientRetriever ageCoefficientRetrieverMock;
    @Mock
    private TCCountrySafetyRatingCoefficientRetriever safetyRatingCoefficientRetrieverMock;
    @Mock
    private TCTravelCostCoefficientRetriever travelCostCoefficientRetrieverMock;

    @InjectMocks
    private TravelCancellationRiskPremiumCalculator cancellationRiskPremiumCalculator;

    @Test
    void calculateRiskPremium_shouldCalculateCorrectResult() {
        BigDecimal ageCoefficient = new BigDecimal("20.00");
        BigDecimal safetyRatingCoefficient = new BigDecimal("8.00");
        BigDecimal travelCostCoefficient = new BigDecimal("30.00");
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement().build();
        PersonDTO person = PersonDTOBuilder.createPerson().build();

        when(ageCoefficientRetrieverMock.findAgeCoefficient(person))
                .thenReturn(ageCoefficient);
        when(safetyRatingCoefficientRetrieverMock.findCountrySafetyRatingCoefficient(agreement))
                .thenReturn(safetyRatingCoefficient);
        when(travelCostCoefficientRetrieverMock.findTravelCostCoefficient(person))
                .thenReturn(travelCostCoefficient);

        BigDecimal expectedPremium = ageCoefficient
                .add(safetyRatingCoefficient)
                .add(travelCostCoefficient)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal actualPremium = cancellationRiskPremiumCalculator.calculateRiskPremium(agreement, person);

        assertThat(actualPremium).isEqualTo(expectedPremium);
    }

}
