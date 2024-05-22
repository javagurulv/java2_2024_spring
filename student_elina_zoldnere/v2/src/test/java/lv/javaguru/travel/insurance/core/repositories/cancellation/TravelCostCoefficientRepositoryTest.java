package lv.javaguru.travel.insurance.core.repositories.cancellation;

import lv.javaguru.travel.insurance.core.domain.cancellation.TravelCostCoefficient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TravelCostCoefficientRepositoryTest {

    @Autowired
    private TravelCostCoefficientRepository repository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertThat(repository).isNotNull();
    }

    @Test
    public void findByTravelCost_ShouldFindExistingCoefficient() {
        Optional<TravelCostCoefficient> travelCostCoefficientOpt =
                repository.findByTravelCost(new BigDecimal("6000"));

        assertThat(travelCostCoefficientOpt).isPresent()
                .map(TravelCostCoefficient::getTravelCostPremium)
                .contains(new BigDecimal("30.00"));
    }

    @Test
    public void findByTravelCost_ShouldNotFindCoefficientWhenTravelCostDoesNotExist() {
        Optional<TravelCostCoefficient> travelCostCoefficientOpt = repository.findByTravelCost(null);

        assertThat(travelCostCoefficientOpt).isEmpty();
    }

    @Test
    public void findByTravelCost_ShouldNotFindCoefficientWhenCoefficientDoesNotExist() {
        Optional<TravelCostCoefficient> travelCostCoefficientOpt =
                repository.findByTravelCost(new BigDecimal("10000000"));

        assertThat(travelCostCoefficientOpt).isEmpty();
    }

}