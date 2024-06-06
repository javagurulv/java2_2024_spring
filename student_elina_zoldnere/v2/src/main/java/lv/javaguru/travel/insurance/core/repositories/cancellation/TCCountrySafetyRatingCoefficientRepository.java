package lv.javaguru.travel.insurance.core.repositories.cancellation;

import lv.javaguru.travel.insurance.core.domain.cancellation.TCCountrySafetyRatingCoefficient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TCCountrySafetyRatingCoefficientRepository extends
        JpaRepository<TCCountrySafetyRatingCoefficient, Long> {

    @Cacheable("tcCountrySafetyRatingCoefficientCache")
    Optional<TCCountrySafetyRatingCoefficient> findCoefficientByCountryIc(String countryIc);

}
