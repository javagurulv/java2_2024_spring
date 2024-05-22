package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.cancellation.TravelCostCoefficient;
import lv.javaguru.travel.insurance.core.repositories.cancellation.TravelCostCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelCostCoefficientRetriever {

    @Autowired
    private TravelCostCoefficientRepository repository;

    BigDecimal findTravelCostPremium(PersonDTO person) {
        BigDecimal travelCost = person.travelCost();
        return repository.findCoefficientByTravelCost(travelCost)
                .map(TravelCostCoefficient::getTravelCostPremium)
                .orElseThrow(() -> new RuntimeException("Premium for travel cost = " + travelCost + " not found!"));
    }

}