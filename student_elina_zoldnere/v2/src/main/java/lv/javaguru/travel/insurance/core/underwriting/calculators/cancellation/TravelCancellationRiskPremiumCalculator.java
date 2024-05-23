package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
class TravelCancellationRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    @Autowired
    private TCAgeCoefficientRetriever ageCoefficientRetriever;
    @Autowired
    private TCCountrySafetyRatingCoefficientRetriever safetyRatingCoefficientRetriever;
    @Autowired
    private TCTravelCostCoefficientRetriever travelCostCoefficientRetriever;

    @Override
    public BigDecimal calculateRiskPremium(AgreementDTO agreement, PersonDTO person) {
        BigDecimal ageCoefficient = ageCoefficientRetriever.findAgeCoefficient(person);
        BigDecimal safetyRatingCoefficient =
                safetyRatingCoefficientRetriever.findCountrySafetyRatingCoefficient(agreement);
        BigDecimal travelCostCoefficient = travelCostCoefficientRetriever.findTravelCostCoefficient(person);
        return ageCoefficient
                .add(safetyRatingCoefficient)
                .add(travelCostCoefficient)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_CANCELLATION";
    }

}
