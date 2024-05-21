package lv.javaguru.travel.insurance.core.repositories.cancellation;

import lv.javaguru.travel.insurance.core.domain.cancellation.TravelCostCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface TravelCostCoefficientRepository extends JpaRepository<TravelCostCoefficient, Long> {

    @Query("SELECT tc from TravelCostCoefficient tc " +
            "where tc.travelCostFrom <= :travel_cost " +
            "and tc.travelCostTo >= :travel_cost")
    Optional<TravelCostCoefficient> findByTravelCost(@Param("travel_cost") BigDecimal travelCost);

}
