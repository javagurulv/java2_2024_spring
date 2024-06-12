package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

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
class TravelMedicalRiskPremiumCalculatorTest {

    @Mock
    private DayCountCalculator dayCountCalculatorMock;
    @Mock
    private CountryDefaultDayRateRetriever countryDefaultDayRateRetrieverMock;
    @Mock
    private TMAgeCoefficientRetriever ageCoefficientRetrieverMock;
    @Mock
    private MedicalRiskLimitLevelCoefficientRetriever limitLevelCoefficientRetrieverMock;

    @InjectMocks
    private TravelMedicalRiskPremiumCalculator medicalRiskPremiumCalculator;

    @Test
    void calculateRiskPremium_shouldCalculateCorrectResult() {
        BigDecimal dayCount = BigDecimal.ONE;
        BigDecimal countryDefaultDayRate = BigDecimal.valueOf(2.5);
        BigDecimal ageCoefficient = BigDecimal.valueOf(1.1);
        BigDecimal limitLevelCoefficient = BigDecimal.valueOf(1.2);
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement().build();
        PersonDTO person = PersonDTOBuilder.createPerson().build();

        when(dayCountCalculatorMock.calculateDayCount(agreement)).thenReturn(dayCount);
        when(countryDefaultDayRateRetrieverMock.findCountryDefaultDayRate(agreement))
                .thenReturn(countryDefaultDayRate);
        when(ageCoefficientRetrieverMock.setAgeCoefficient(person)).thenReturn(ageCoefficient);
        when(limitLevelCoefficientRetrieverMock.setLimitLevelCoefficient(person))
                .thenReturn(limitLevelCoefficient);

        BigDecimal expectedPremium = countryDefaultDayRate
                .multiply(dayCount)
                .multiply(ageCoefficient)
                .multiply(limitLevelCoefficient)
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPremium = medicalRiskPremiumCalculator.calculateRiskPremium(agreement, person);

        assertThat(actualPremium).isEqualTo(expectedPremium);
    }

}
