package lv.javaguru.travel.insurance.core.repositories.cancellation;

import lv.javaguru.travel.insurance.core.domain.cancellation.TCAgeCoefficient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TCAgeCoefficientRepository extends JpaRepository<TCAgeCoefficient, Long> {

    @Cacheable("tcAgeCoefficientCache")
    @Query("SELECT ac from TCAgeCoefficient ac " +
            "where ac.ageFrom <= :age " +
            "and ac.ageTo >= :age")
    Optional<TCAgeCoefficient> findCoefficientByAge(@Param("age") Integer age);

}
